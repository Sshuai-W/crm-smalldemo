package com.shuai.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.DeptQueryCriteria;
import com.shuai.cn.domin.entity.Dept;
import com.shuai.cn.domin.vo.DeptVo;
import com.shuai.cn.mapper.DeptMapper;
import com.shuai.cn.service.DeptService;
import com.shuai.cn.service.base.impl.BaseServiceImpl;
import com.shuai.cn.typeConversion.DeptConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {

    private final DeptMapper deptMapper;

    private final DeptConversion conversion;

    @Override
    public PageResult<DeptVo> searchPage(DeptQueryCriteria criteria) {
        if ((criteria.getSearchStr() != null && criteria.getSearchStr() != "") || criteria.getStartTime() != null || criteria.getEndTime() != null) {
            return searchByCondition(criteria);
        } else {
            return searchAndSetChildren(criteria);
        }
    }


    @Override
    public List<DeptVo> findChildrenById(long id) {
        List<Dept> list = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, id));
        List<DeptVo> deptVos = conversion.entityToVoList(list);
        deptVos.forEach(deptVo -> {
            List<Dept> deptList = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, deptVo.getId()));
            if (deptList.size() > 0) {
                deptVo.setChildren(conversion.entityToVoList(deptList));
                deptVo.setHasChildren(true);
            }
        });
        return deptVos;
    }

    @Override
    public void deleteDeptAndChildren(long id) {
        List<Dept> list = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, id));
        deptMapper.deleteById(id);
        if (list != null && list.size() > 0){
            list.forEach(dept -> {
                deleteDeptAndChildren(dept.getId());
            });
        }
    }

    @Override
    public List<DeptVo> searchAllDeptAndBuildTree() {
        List<Dept> list = deptMapper.selectList(null);
        List<DeptVo> deptVos = conversion.entityToVoList(list);
        List<DeptVo> deptVoList = buildLevelTree(0, deptVos);
        return deptVoList;
    }

    public List<DeptVo> buildLevelTree(long parentId, List<DeptVo> list){
        List<DeptVo> collect = list.stream().filter(item -> item.getParentId() == parentId).collect(Collectors.toList());
        if (collect == null || collect.size() <= 0){
            return null;
        }
        collect.forEach(deptVo -> {
            List<DeptVo> deptVos = buildLevelTree(deptVo.getId(), list);
            deptVo.setChildren(deptVos);
            if (deptVos != null && deptVos.size() > 0){
                deptVo.setHasChildren(true);
            }
        });
        return collect;
    }

    private PageResult<DeptVo> searchByCondition(DeptQueryCriteria criteria) {
        LambdaQueryWrapper<Dept> lambda = new QueryWrapper<Dept>().lambda();
        lambda.orderByAsc(Dept::getDeptSort);
        if (criteria.getSearchStr() != null) {
            lambda.like(Dept::getDeptName, criteria.getSearchStr());
        }
        if (criteria.getStartTime() != null && criteria.getEndTime() != null) {
            lambda.between(Dept::getCreateTime, criteria.getStartTime(), criteria.getEndTime());
        }
        PageHelper.startPage(criteria.getCurrentPage(), criteria.getPageSize());
        List<Dept> list = deptMapper.selectList(lambda);
        PageInfo pageInfo = new PageInfo(list);
        List<DeptVo> deptVos = conversion.entityToVoList(list);
        return new PageResult<DeptVo>(pageInfo.getTotal(), deptVos);
    }

    private PageResult<DeptVo> searchAndSetChildren(DeptQueryCriteria criteria) {
        LambdaQueryWrapper<Dept> lambda = new QueryWrapper<Dept>().lambda();
        lambda.eq(Dept::getParentId, 0L);
        lambda.orderByAsc(Dept::getDeptSort);
        PageHelper.startPage(criteria.getCurrentPage(), criteria.getPageSize());
        List<Dept> list = deptMapper.selectList(lambda);
        PageInfo pageInfo = new PageInfo(list);
        List<DeptVo> deptVos = conversion.entityToVoList(list);
        deptVos.forEach(deptVo -> {
            List<Dept> deptList = deptMapper.selectList(new QueryWrapper<Dept>().lambda().eq(Dept::getParentId, deptVo.getId()));
            if (deptList.size() > 0) {
                deptVo.setChildren(conversion.entityToVoList(deptList));
                deptVo.setHasChildren(true);
            }
        });
        return new PageResult<DeptVo>(pageInfo.getTotal(), deptVos);
    }


}
