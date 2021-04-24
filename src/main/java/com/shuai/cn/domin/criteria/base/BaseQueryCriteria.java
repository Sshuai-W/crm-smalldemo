package com.shuai.cn.domin.criteria.base;

import lombok.Data;

/**
 * 封装查询条件基类
 */
@Data
public class BaseQueryCriteria {

    private int currentPage = 1;
    private int pageSize = 5;

}
