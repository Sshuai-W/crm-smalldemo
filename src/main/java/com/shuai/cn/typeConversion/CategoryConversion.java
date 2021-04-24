package com.shuai.cn.typeConversion;

import com.shuai.cn.domin.entity.Category;
import com.shuai.cn.domin.vo.CategoryVo;
import com.shuai.cn.typeConversion.base.BaseTypeConversion;
import org.springframework.stereotype.Component;

@Component
public class CategoryConversion extends BaseTypeConversion<Category, CategoryVo> {
}
