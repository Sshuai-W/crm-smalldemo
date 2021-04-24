package com.shuai.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.shuai.cn.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_category")
public class Category extends BaseEntity {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类父id 如果是顶级分类 父id 为0
     */
    private Long parentId;

    /**
     * 分类等级 一共三级分类 1 2 3 
     */
    private Integer categoryLevel;



}
