package com.shangma.cn.controller;

import com.shangma.cn.common.http.AxiosResult;
import com.shangma.cn.common.page.PageResult;
import com.shangma.cn.controller.base.BaseController;
import com.shangma.cn.domin.criteria.BrandQueryCriteria;
import com.shangma.cn.domin.entity.Brand;
import com.shangma.cn.domin.vo.BrandVo;
import com.shangma.cn.service.BrandService;
import com.shangma.cn.typeConversion.BrandConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
public class BrandController extends BaseController<Brand> {

    private final BrandService brandService;

    private final BrandConversion conversion;

    @GetMapping
    public AxiosResult<PageResult<BrandVo>> searchPage(BrandQueryCriteria criteria){
        return AxiosResult.success(brandService.searchPage(criteria));
    }

    @PostMapping
    public AxiosResult<Void> add(@RequestBody Brand brand){
        brandService.add(brand);
        return AxiosResult.success();
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id){
        brandService.deleteById(id);
        return AxiosResult.success();
    }

    @PutMapping
    public AxiosResult<Void> update(@RequestBody BrandVo brandVo){
        Brand brand = conversion.voToEntity(brandVo);
        brandService.update(brand);
        return AxiosResult.success();
    }

    @DeleteMapping("/pick/{ids}")
    public AxiosResult<Void> deleteAllPick(@PathVariable List<Long> ids){
        brandService.deleteByIds(ids);
        return AxiosResult.success();
    }

}
