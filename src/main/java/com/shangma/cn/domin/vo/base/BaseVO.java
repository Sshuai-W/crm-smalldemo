package com.shangma.cn.domin.vo.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseVO implements Serializable {

    private Long id;

    /**
     * 创建者
     */
    @JsonIgnore
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    @JsonIgnore
    private Long updateBy;

    /**
     * 修改时间
     */
    @JsonIgnore
    private LocalDateTime updateTime;

}
