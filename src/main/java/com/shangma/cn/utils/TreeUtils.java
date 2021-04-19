package com.shangma.cn.utils;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 制作树形结构工具类
 */
@Component
public class TreeUtils {
private static int j;
    public <T> List<T> buildLevelTree(List<T> list) {
        List<T> collect = list.stream().
                filter(item -> (long)ReflectionUtils.getFieldValue(item,"parentId")==0)
                .collect(Collectors.toList());
        setChildren(collect,list);
        return collect;
    }

    public <T> void setChildren(List<T> collect, List<T> list) {
        for (T t : collect) {
            List<T> list1 = list.stream().filter(item -> (long)ReflectionUtils.getFieldValue(item,"parentId") == (long)ReflectionUtils.getFieldValue(t,"id")).collect(Collectors.toList());
            if (list1 == null || list1.size() <= 0) {
                return;
            }
            list1.forEach(item->{
                ReflectionUtils.setFieldValue(item,"parentName",ReflectionUtils.getFieldValue(t,"categoryName"));
            });
            ReflectionUtils.setFieldValue(t,"list",list1);
            setChildren(list1, list);
        }
    }

}
