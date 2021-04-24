package com.shuai.cn.common.http;

import lombok.Data;

import java.util.List;

@Data
public class AreaResult {

    private String reason;

    private List<AreaItem> result;

}
