package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.domin.criteria.BrandQueryCriteria;
import com.shuai.cn.domin.entity.Brand;
import com.shuai.cn.domin.vo.BrandVo;
import com.shuai.cn.service.BrandService;
import com.shuai.cn.typeConversion.BrandConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌管理
 */
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
    public AxiosResult<Void> update(@RequestBody Brand brand){
//        Brand brand = conversion.voToEntity(brandVo);
        brandService.update(brand);
        return AxiosResult.success();
    }

    /**
     * 根据ids批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/pick/{ids}")
    public AxiosResult<Void> deleteAllPick(@PathVariable List<Long> ids){
        brandService.deleteByIds(ids);
        return AxiosResult.success();
    }

}
