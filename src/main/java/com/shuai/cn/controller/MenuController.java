package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.domin.criteria.MenuQueryCriteria;
import com.shuai.cn.domin.entity.Menu;
import com.shuai.cn.domin.vo.MenuVo;
import com.shuai.cn.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController extends BaseController<Menu> {

    private final MenuService menuService;

    @GetMapping
    public AxiosResult<PageResult<MenuVo>> searchPage(MenuQueryCriteria criteria){
        return AxiosResult.success(menuService.searchPage(criteria));
    }


}
