package com.shuai.cn.typeConversion.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseTypeConversion<T, VO> {

    private Class<T> tClass;
    private Class<VO> voClass;

    public BaseTypeConversion() {
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        tClass = (Class<T>) params[0];
        voClass = (Class<VO>) params[1];
    }

    /**
     * t转VO
     *
     * @param t
     * @return
     */
    public VO entityToVo(T t) {
        try {
            VO vo = voClass.newInstance();
            BeanUtils.copyProperties(t, vo);
            return vo;
        } catch (Exception e) {
            log.error("类型转换错误", e);
        }
        return null;
    }

    /**
     * vo转T
     *
     * @param vo
     * @return
     */
    public T voToEntity(VO vo) {
        try {
            T t = tClass.newInstance();
            BeanUtils.copyProperties(vo, t);
            return t;
        } catch (Exception e) {
            log.error("类型转换错误", e);
        }
        return null;
    }

    /**
     * t集合转VO集合
     * @param list
     * @return
     */
    public List<VO> entityToVoList(List<T> list) {
        List<VO> voList = new ArrayList<>();
        list.forEach(t -> voList.add(entityToVo(t)));
        return voList;
    }

    public List<T> voToEntityList(List<VO> list){
        List<T> tList = new ArrayList<>();
        list.forEach(vo -> tList.add(voToEntity(vo)));
        return tList;
    }

}
