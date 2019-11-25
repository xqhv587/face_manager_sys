package com.xqh.severshiro.entity.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xqh.commoncore.persistence.DataEntity;
import lombok.*;

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

    @TableId(value="id", type= IdType.INPUT)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("address")
    private String address;

    @TableField("password")
    private String password;

    @TableField("role_id")
    private Long roleId;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String ROLE_ID = "role_id";
    public static final String ADDRESS = "address";
    public static final String AGE = "age";



}
