package com.shuai.cn.service.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.List;

public interface BaseService<T> {

    List<T> findAll();

    T findById(Long id);

    List<T> search(Wrapper<T> Wrapper);

    void add(T t);

    void update(T t);

    void deleteById(Long id);

    void deleteByIds(List<Long> ids);

}
