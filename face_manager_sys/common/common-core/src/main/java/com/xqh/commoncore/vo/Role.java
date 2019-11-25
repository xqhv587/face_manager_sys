package com.xqh.commoncore.vo;

import com.xqh.commoncore.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 *
 * @author tangyi
 * @date 2018-08-25 13:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity<Role> {

    private String roleName;

    private String roleCode;

    private String roleDesc;

}
