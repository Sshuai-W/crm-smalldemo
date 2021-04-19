package com.shangma.cn.service;

import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.service.base.BaseService;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    List<CategoryVo> buildLevelTree();

    void deleteByCategoryVo(CategoryVo categoryVo);
}
