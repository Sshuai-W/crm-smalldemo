package com.shuai.cn.controller;

import com.shuai.cn.common.http.AxiosResult;
import com.shuai.cn.controller.base.BaseController;
import com.shuai.cn.utils.upload.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;

@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class CommonController extends BaseController {

    private final UploadUtils uploadUtils;

    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part file){
        String upload = uploadUtils.upload(file);
        return AxiosResult.success(upload);
    }
}
