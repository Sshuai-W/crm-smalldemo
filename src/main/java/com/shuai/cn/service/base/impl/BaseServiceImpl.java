package com.shuai.cn.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.shuai.cn.mapper.base.MyBaseMapper;
import com.shuai.cn.service.base.BaseService;
import com.shuai.cn.utils.reflection.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private MyBaseMapper<T> myBaseMapper;

    protected MyBaseMapper<T> getMyBaseMapper(){
        return myBaseMapper;
    }

    @Override
    public List<T> findAll() {
        return myBaseMapper.selectList(null);
    }

    @Override
    public T findById(Long id) {
        return myBaseMapper.selectById(id);
    }

    @Override
    public List<T> search(Wrapper<T> Wrapper) {
        List<T> list = myBaseMapper.selectList(Wrapper);
        return list;
    }

    @Override
    public void add(T t) {
        /**
         * 给属性的修改或添加时间人员操作赋值
         */
        ReflectionUtils.invokeMethod(t,"setData",null,null);
        myBaseMapper.insert(t);
    }

    @Override
    public void update(T t) {
        ReflectionUtils.invokeMethod(t,"setData",null,null);
        myBaseMapper.updateById(t);
    }

    @Override
    public void deleteById(Long id) {
        myBaseMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByIds(List<Long> ids) {
        myBaseMapper.deleteBatchIds(ids);
    }
}
