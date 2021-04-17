package com.shangma.cn.service;

import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.domin.criteria.BrandQueryCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.service.base.BaseService;

public interface BrandService extends BaseService<Brand> {

    PageResult<BrandVo> searchPage(BrandQueryCriteria criteria);

}
