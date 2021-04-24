package com.shuai.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shuai.cn.common.page.PageResult;
import com.shuai.cn.domin.criteria.AdminQueryCriteria;
import com.shuai.cn.domin.entity.Admin;
import com.shuai.cn.domin.vo.AdminVo;
import com.shuai.cn.mapper.AdminMapper;
import com.shuai.cn.mapper.DeptMapper;
import com.shuai.cn.service.AdminService;
import com.shuai.cn.service.base.impl.BaseServiceImpl;
import com.shuai.cn.typeConversion.AdminConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    private final AdminMapper adminMapper;

    private final DeptMapper deptMapper;

    private final AdminConversion conversion;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PageResult<AdminVo> searchPage(AdminQueryCriteria criteria) {
        PageHelper.startPage(criteria.getCurrentPage(),criteria.getPageSize());
        LambdaQueryWrapper<Admin> lambda = new QueryWrapper<Admin>().lambda();
        if (!StringUtils.isEmpty(criteria.getSearchStr())){
            lambda.like(Admin::getAdminName,criteria.getSearchStr()).or().like(Admin::getAdminAddress,criteria.getSearchStr());
            if (criteria.getSearchStr().equals("男")){
                lambda.or().eq(Admin::getGender,0);
            }else if (criteria.getSearchStr().equals("女")){
                lambda.or().eq(Admin::getGender,1);
            }
        }
        if (!StringUtils.isEmpty(criteria.getStartTime()) && !StringUtils.isEmpty(criteria.getEndTime())){
            lambda.between(Admin::getUpdateTime,criteria.getStartTime(),criteria.getEndTime());
        }
        List<Admin> admins = adminMapper.selectList(lambda);
        List<AdminVo> list = conversion.entityToVoList(admins);
        list.forEach(adminVo -> {
            adminVo.setDeptName(deptMapper.selectById(adminVo.getDeptId()).getDeptName());
        });
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        return new PageResult<AdminVo>(adminPageInfo.getTotal(),list);
    }

    @Override
    public int addAdmin(Admin admin) {
        String password = bCryptPasswordEncoder.encode("123456");
        admin.setAdminPwd(password);
        admin.setIsAdmin(false);
        int row = adminMapper.insert(admin);
        return row;
    }
}
