package com.xqh.commoncore.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 用户信息
 *
 * @author ye
 * @date 2019/3/17 12:08
 */
@Data
public class UserInfo {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private Boolean allow;
    private String listName;
    private String lastName;
    private String firstName;
    private String realName;
    private String email;
    private String deptId;
    private String groupTeam;
    private String division;
    private String bu;
    private String projectFlow;
    private Integer admin;
    private String account;
    /**
     * 用户设置的语言
     */
    @TableField("UserLanguage")
    private String UserLanguage;
    /**
     * 员工工作所在地(Location)
     */
    private String groupId;
    private Integer status;
    private String levelId;
    private String userIp;
    private String opType;
    private String directorId;
    private String upperDept;
    private String belongGroup;
    private String branch;
    private String Domain;
}
