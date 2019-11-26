package com.xqh.severshiro.entity.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xqh.commoncore.persistence.DataEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author xqh3622
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class User extends DataEntity<User> {
    private static final long serialVersionUID = 1L;

//    @TableId(value="id", type= IdType.INPUT)
//    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("password")
    private String password;

    @TableField("role_id")
    private Long roleId;

    @TableField("job_number")
    private Integer jobNumber;

    @TableField("gender")
    private String gender;

    @TableField("grade")
    private String grade;

    @TableField("major")
    private String major;

    @TableField("phone")
    private String phone;

    @TableField("status")
    private Integer status;

    @TableField("punch_status")
    private Integer punchStatus;

    @TableField("punch_time")
    private LocalDateTime punchTime;

//    @TableField("create_time")
//    private LocalDateTime createTime;
//
//    @TableField("update_time")
//    private LocalDateTime updateTime;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
    public static final String AGE = "age";
    public static final String JOBNUMBER = "job_number";
    public static final String GENDER = "gender";
    public static final String GRADE = "grade";
    public static final String MAJOR = "major";
    public static final String PHONE = "phone";
    public static final String STATUS = "status";
    public static final String PUNCHSTATUS = "punch_status";



}
