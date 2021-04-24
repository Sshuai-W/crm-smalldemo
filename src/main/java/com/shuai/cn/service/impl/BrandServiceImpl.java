package com.shuai.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.BrandQueryCriteria;
import com.shuai.cn.domin.entity.Brand;
import com.shuai.cn.domin.vo.BrandVo;
import com.shuai.cn.mapper.BrandMapper;
import com.shuai.cn.service.BrandService;
import com.shuai.cn.service.base.impl.BaseServiceImpl;
import com.shuai.cn.typeConversion.BrandConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl extends BaseServiceImpl<Brand> implements BrandService {

    private final BrandConversion conversion;

    private final BrandMapper brandMapper;

    @Override
    public PageResult<BrandVo> searchPage(BrandQueryCriteria criteria) {
        PageHelper.startPage(criteria.getCurrentPage(), criteria.getPageSize());
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        if (criteria.getSearchStr() != null) {
            wrapper.lambda().like(Brand::getBrandName,criteria.getSearchStr());
        }
        if (criteria.getStartTime() != null && criteria.getEndTime() != null){
            wrapper.lambda().between(Brand::getCreateTime,criteria.getStartTime(),criteria.getEndTime());
        }
        List<Brand> brands = brandMapper.selectList(wrapper);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        List<BrandVo> brandVos = conversion.entityToVoList(brands);
        return new PageResult<BrandVo>(brandPageInfo.getTotal(), brandVos);
    }

}
