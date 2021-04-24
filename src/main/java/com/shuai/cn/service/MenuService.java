package com.shuai.cn.service;

import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.MenuQueryCriteria;
import com.shuai.cn.domin.entity.Menu;
import com.shuai.cn.domin.vo.MenuVo;
import com.shuai.cn.service.base.BaseService;

import java.util.List;


public interface MenuService extends BaseService<Menu> {

    PageResult<MenuVo> searchPage(MenuQueryCriteria criteria);

    void setChildren(List<MenuVo> menuVos, List<MenuVo> list);
}
