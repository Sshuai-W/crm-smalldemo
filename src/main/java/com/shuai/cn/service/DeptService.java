package com.shuai.cn.service;

import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.DeptQueryCriteria;
import com.shuai.cn.domin.entity.Dept;
import com.shuai.cn.domin.vo.DeptVo;
import com.shuai.cn.service.base.BaseService;

import java.util.List;

public interface DeptService extends BaseService<Dept> {
    PageResult<DeptVo> searchPage(DeptQueryCriteria criteria);

    List<DeptVo> findChildrenById(long id);

    void deleteDeptAndChildren(long id);

    List<DeptVo> searchAllDeptAndBuildTree();
}
