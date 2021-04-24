package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.domin.criteria.AdminQueryCriteria;
import com.shuai.cn.domin.entity.Admin;
import com.shuai.cn.domin.vo.AdminVo;
import com.shuai.cn.service.AdminService;
import com.shuai.cn.typeConversion.AdminConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController extends BaseController<Admin> {

    private final AdminService adminService;

    private final AdminConversion conversion;

    @GetMapping
    public AxiosResult<PageResult<AdminVo>> searchPage(AdminQueryCriteria criteria){
        return AxiosResult.success(adminService.searchPage(criteria));
    }

    @DeleteMapping("/pick/{ids}")
    public AxiosResult<Void> deleteAllPick(@PathVariable List<Long> ids){
        adminService.deleteByIds(ids);
        return AxiosResult.success();
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id){
        adminService.deleteById(id);
        return AxiosResult.success();
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return AxiosResult.success();
    }

    @PutMapping
    public AxiosResult<Void> update(@RequestBody Admin admin){
        adminService.update(admin);
        return AxiosResult.success();
    }

}
