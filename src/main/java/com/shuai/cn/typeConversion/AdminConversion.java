package com.shuai.cn.typeConversion;

import com.shuai.cn.domin.entity.Admin;
import com.shuai.cn.domin.vo.AdminVo;
import com.shuai.cn.typeConversion.base.BaseTypeConversion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminConversion extends BaseTypeConversion<Admin, AdminVo> {

    @Override
    public AdminVo entityToVo(Admin admin) {
        AdminVo adminVo = super.entityToVo(admin);
        adminVo.setSex(admin.getGender() == 0 ? "男" : "女");
        return adminVo;
    }

    @Override
    public List<AdminVo> entityToVoList(List<Admin> list) {
        List<AdminVo> resList = new ArrayList<>();
        for (Admin admin : list) {
            resList.add(entityToVo(admin));
        }
        return resList;
    }
}
