package com.shuai.cn.service;

import com.shuai.cn.domin.entity.Category;
import com.shuai.cn.domin.vo.CategoryVo;
import com.shuai.cn.service.base.BaseService;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    List<CategoryVo> buildLevelTree();

    void deleteByCategoryVo(CategoryVo categoryVo);
}
