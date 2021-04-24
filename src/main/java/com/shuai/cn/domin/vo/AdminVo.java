package com.shuai.cn.domin.vo;

import com.shuai.cn.domin.vo.base.BaseVo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminVo extends BaseVo {

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 管理员昵称
     */
    private String nickName;

    /**
     * 管理员性别 0 : 男   1：女     2： 表示未知
     */
    private Integer gender;

    /**
     * 员工性别：男 or 女
     */
    private String sex;

    /**
     * 管理员手机
     */
    private String adminPhone;

    /**
     * 管理员邮箱
     */
    private String adminEmail;

    /**
     * 管理员家住地址
     */
    private String adminAddress;

    /**
     * 管理员密码
     */
    private String adminPwd;

    /**
     * 管理员头像
     */
    private String adminAvatar;

    /**
     * 账户是否可用
     */
    private Boolean isEnable;

    /**
     * 是否是超级管理员
     */
    private Boolean isAdmin;

    /**
     * 所在部门
     */
    private Long deptId;

    /**
     * 所在部门名称
     */
    private String deptName;

    /**
     * 重置密码时间
     */
    private LocalDateTime pwdResetTime;



}
