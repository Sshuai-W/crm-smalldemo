package com.shangma.cn.domin.vo;

import com.shangma.cn.domin.vo.base.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo extends BaseVO {

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

    private String parentName;

    private List<CategoryVo> list;

}
