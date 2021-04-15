package com.shangma.cn.service.impl;

import com.shangma.cn.domin.entity.Good;
import com.shangma.cn.service.GoodService;
import com.shangma.cn.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {
}
