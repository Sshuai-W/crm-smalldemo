package com.shangma.cn.service.impl;

import com.shangma.cn.domin.entity.Dept;
import com.shangma.cn.service.DeptService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {
}
