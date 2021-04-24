package com.shuai.cn.domin.criteria;

import com.shuai.cn.domin.criteria.base.BaseQueryCriteria;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AdminQueryCriteria extends BaseQueryCriteria {

    private String searchStr;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
