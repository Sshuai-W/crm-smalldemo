package com.shuai.cn.domin.vo;

import com.shuai.cn.domin.vo.base.BaseVo;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo extends BaseVo {

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

    /**
     * 子菜单
     */
    private List<MenuVo> children;

    /**
     * 是否有孩子
     */
    private boolean hasChildren;

}
