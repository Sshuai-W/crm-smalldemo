package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.domin.criteria.DeptQueryCriteria;
import com.shuai.cn.domin.entity.Dept;
import com.shuai.cn.domin.vo.DeptVo;
import com.shuai.cn.service.DeptService;
import com.shuai.cn.typeConversion.DeptConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dept")
@RequiredArgsConstructor
public class DeptController extends BaseController<Dept> {

    private final DeptService deptService;

    private final DeptConversion conversion;

    @GetMapping
    private AxiosResult<PageResult<DeptVo>> searchByParams(DeptQueryCriteria criteria){
        return AxiosResult.success(deptService.searchPage(criteria));
    }

    @GetMapping("{id}/findChildren")
    private AxiosResult<List<DeptVo>> findChildrenById(@PathVariable long id){
        return AxiosResult.success(deptService.findChildrenById(id));
    }

    @PostMapping
    private AxiosResult<Void> addDept(@RequestBody DeptVo deptVo){
        deptService.add(conversion.voToEntity(deptVo));
        return AxiosResult.success();
    }

    @PutMapping
    private AxiosResult<Void> update(@RequestBody DeptVo deptVo){
        deptService.update(conversion.voToEntity(deptVo));
        return AxiosResult.success();
    }

    @DeleteMapping("{id}")
    private AxiosResult<Void> deleteById(@PathVariable long id){
        deptService.deleteDeptAndChildren(id);
        return AxiosResult.success();
    }

    @GetMapping("tree")
    private AxiosResult<List<DeptVo>> searchAllDeptAndBuildTree(){
        return AxiosResult.success(deptService.searchAllDeptAndBuildTree());
    }
}
