package com.shuai.cn.service;

import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.AdminQueryCriteria;
import com.shuai.cn.domin.entity.Admin;
import com.shuai.cn.domin.vo.AdminVo;
import com.shuai.cn.service.base.BaseService;

public interface AdminService extends BaseService<Admin> {
    PageResult<AdminVo> searchPage(AdminQueryCriteria criteria);

    int addAdmin(Admin admin);
}
