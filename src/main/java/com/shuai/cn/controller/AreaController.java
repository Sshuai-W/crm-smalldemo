package com.shuai.cn.controller;

import com.shuai.cn.common.http.AreaItem;
import com.shuai.cn.common.http.AreaResult;
import com.shuai.cn.common.http.AxiosResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("area")
@RequiredArgsConstructor
public class AreaController {

    private final RestTemplate restTemplate;

    @GetMapping("{id}/children")
    public AxiosResult<List<AreaItem>> getChildren(@PathVariable long id){
        String path = "http://apis.juhe.cn/xzqh/query?key=737c5a53b09d242c42cfc48aaf1c4e07&fid="+id;
        AreaResult forObject = restTemplate.getForObject(path, AreaResult.class);
        return AxiosResult.success(forObject.getResult());
    }

}
