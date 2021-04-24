package com.shuai.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.MenuQueryCriteria;
import com.shuai.cn.domin.entity.Menu;
import com.shuai.cn.domin.vo.MenuVo;
import com.shuai.cn.mapper.MenuMapper;
import com.shuai.cn.service.MenuService;
import com.shuai.cn.service.base.impl.BaseServiceImpl;
import com.shuai.cn.typeConversion.MenuConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private final MenuMapper mapper;

    private final MenuConversion conversion;

    @Override
    public PageResult<MenuVo> searchPage(MenuQueryCriteria criteria) {
        if ((criteria.getSearchStr() != null && criteria.getSearchStr() != "") || criteria.getStartTime() != null || criteria.getEndTime() != null) {
            return searchByCondition(criteria);
        } else {
            return searchAndSetChildren(criteria);
        }
    }

    @Override
    public void setChildren(List<MenuVo> menuVos, List<MenuVo> list) {
        if (menuVos == null || menuVos.size() <= 0) {
            return;
        }
        menuVos.forEach(menuVo -> {
            List<MenuVo> menuVoStream = list.stream().filter(menuVo1 -> menuVo1.getParentId().longValue() == menuVo.getId().longValue()).collect(Collectors.toList());
            if (menuVoStream != null && menuVoStream.size() > 0){
                setChildren(menuVoStream,list);
                menuVo.setChildren(menuVoStream);
                menuVo.setHasChildren(true);
            }else {
                menuVo.setChildren(null);
            }
        });
    }

    private PageResult<MenuVo> searchAndSetChildren(MenuQueryCriteria criteria) {
        PageHelper.startPage(criteria.getCurrentPage(), criteria.getPageSize());
        List<Menu> menus = mapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0));
        List<Menu> list = mapper.selectList(null);
        List<MenuVo> menuVos = conversion.entityToVoList(menus);
        List<MenuVo> menuVoList = conversion.entityToVoList(list);
        setChildren(menuVos,menuVoList);
        PageInfo<Menu> pageInfo = new PageInfo<Menu>(menus);
        return new PageResult<>(pageInfo.getTotal(),menuVos);
    }

    private PageResult<MenuVo> searchByCondition(MenuQueryCriteria criteria) {
        return null;
    }


}
