package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.domin.entity.Category;
import com.shuai.cn.domin.vo.CategoryVo;
import com.shuai.cn.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController extends BaseController<Category> {

    private final CategoryService categoryService;

    @GetMapping
    public AxiosResult<List<CategoryVo>> findAll(){
        List<CategoryVo> list = categoryService.buildLevelTree();
        return AxiosResult.success(list);
    }

    @PostMapping("delete")
    public AxiosResult<Void> delete(@RequestBody CategoryVo deleteCategory){
        System.out.println(deleteCategory);
        System.out.println(deleteCategory.getId());
        categoryService.deleteByCategoryVo(deleteCategory);
        return AxiosResult.success();
    }


}
