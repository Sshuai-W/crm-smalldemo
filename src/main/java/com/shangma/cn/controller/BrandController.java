package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController extends BaseController<Brand> {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public AxiosResult<List<Brand>> findAll(){
        return AxiosResult.success(brandService.findAll());
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Brand brand){
        brandService.add(brand);
        return AxiosResult.success();
    }

}
