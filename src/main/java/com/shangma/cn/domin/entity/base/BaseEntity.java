package com.shangma.cn.domin.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    private Long updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    private void setData(){
        if (id == null){
            createBy = 1l;
            createTime = LocalDateTime.now();
        }else{
            updateBy = 1l;
            updateTime = LocalDateTime.now();
        }
    }

}
