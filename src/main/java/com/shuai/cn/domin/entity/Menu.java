package com.shuai.cn.domin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shuai.cn.domin.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    /**
     * 标题
     */
    private String menuTitle;

    /**
     * 上级菜单id
     */
    private Long parentId;

    /**
     * 菜单分类
     * 0：目录  1：菜单  2：按钮
     */
    private Integer menuType;

    /**
     * 排序
     */
    private Integer menuSort;

    /**
     * 路由地址
     */
    private String menuPath;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 是否隐藏
     */
    private boolean isHidden;

    /**
     * 权限标识
     */
    private String permission;

}
