package com.shuai.cn.service.impl;

import com.shuai.cn.domin.entity.Good;
import com.shuai.cn.service.GoodService;
import com.shuai.cn.service.base.impl.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodServiceImpl extends BaseServiceImpl<Good> implements GoodService {
}
