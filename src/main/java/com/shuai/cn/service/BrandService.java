package com.shuai.cn.service;

import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.BrandQueryCriteria;
import com.shuai.cn.domin.entity.Brand;
import com.shuai.cn.domin.vo.BrandVo;
import com.shuai.cn.service.base.BaseService;

public interface BrandService extends BaseService<Brand> {

    PageResult<BrandVo> searchPage(BrandQueryCriteria criteria);

}
