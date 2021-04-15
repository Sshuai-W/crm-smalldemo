package com.shangma.cn.service.impl;

import com.shangma.cn.domin.entity.Admin;
import com.shangma.cn.service.AdminService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
}
