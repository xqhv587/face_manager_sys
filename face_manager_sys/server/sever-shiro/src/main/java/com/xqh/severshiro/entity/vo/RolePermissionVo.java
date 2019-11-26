package com.xqh.severshiro.entity.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@Data
public class RolePermissionVo implements Serializable {

    private static final long serialVersionUID = 1L;


            private Long id;

            private Long roleId;

            private Long permissionId;

            private LocalDateTime createTime;

            private LocalDateTime updateTime;

}