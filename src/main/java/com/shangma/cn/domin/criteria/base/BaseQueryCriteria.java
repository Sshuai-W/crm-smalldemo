package com.shangma.cn.domin.criteria.base;

import lombok.Data;

@Data
public class BaseQueryCriteria {

    private int currentPage = 1;
    private int pageSize = 5;

}
