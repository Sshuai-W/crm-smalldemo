package com.shangma.cn.service.impl;

import com.alibaba.fastjson.JSON;
import com.shangma.cn.domin.entity.Category;
import com.shangma.cn.domin.vo.CategoryVo;
import com.shangma.cn.mapper.CategoryMapper;
import com.shangma.cn.service.CategoryService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import com.shangma.cn.typeConversion.CategoryConversion;
import com.shangma.cn.utils.TreeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final CategoryConversion conversion;

    private final TreeUtils treeUtils;

    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 创建树形结构分类等级
     * @return
     */
    @Override
    public List<CategoryVo> buildLevelTree() {
        List<Category> categories = categoryMapper.selectList(null);
        List<CategoryVo> categoryVoList = conversion.entityToVoList(categories);
        String str = stringRedisTemplate.opsForValue().get("categoryListStr");
        if (str == null || str == "") {
            List<CategoryVo> collect = treeUtils.buildLevelTree(categoryVoList);
            String string = JSON.toJSONString(collect);
            stringRedisTemplate.opsForValue().set("categoryListStr", string);
            return collect;
        }
        List<CategoryVo> list = JSON.parseArray(str, CategoryVo.class);
        return list;
    }

    /**
     * 前端传VO参数来进行删除孩子和本身
     * @param categoryVo
     */
    @Override
    public void deleteByCategoryVo(CategoryVo categoryVo) {
        List<Long> ids = new ArrayList();
        Integer categoryLevel = categoryVo.getCategoryLevel();
        if (categoryLevel == 1) {
            List<CategoryVo> list = categoryVo.getList();
            ids.add(categoryVo.getId());
            list.forEach(item -> {
                ids.add(item.getId());
                if (item.getList() != null && item.getList().size() >= 0) {
                    item.getList().forEach(child -> {
                        ids.add(child.getId());
                    });
                }
            });
            System.out.println(ids.toString());
        } else if (categoryLevel == 2) {
            List<CategoryVo> list = categoryVo.getList();
            ids.add(categoryVo.getId());
            list.forEach(item -> {
                ids.add(item.getId());
            });
            System.out.println(ids.toString());
        } else {
            categoryMapper.deleteById(categoryVo.getId());
        }
        categoryMapper.deleteBatchIds(ids);
        stringRedisTemplate.delete("categoryListStr");
    }

    /*public void setChildren(List<CategoryVo> categoryVos, List<CategoryVo> categoryVoList) {
        if (categoryVos == null || categoryVos.size() <= 0) {
            return;
        }
        for (CategoryVo categoryVo : categoryVos) {
            List<CategoryVo> collect = categoryVoList.stream().filter(item -> item.getParentId().longValue() == categoryVo.getId()).collect(Collectors.toList());
            setChildren(collect, categoryVoList);
            if (collect == null || collect.size() <= 0) {
                continue;
            }
            collect.forEach(item->{
                ReflectionUtils.setFieldValue(item,"parentName",categoryVo.getCategoryName());
//                item.setParentName(categoryVo.getCategoryName());
            });
            categoryVo.setList(collect);
        }
    }*/
}
