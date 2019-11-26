package com.xqh.severshiro.entity.Model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xqh.commoncore.persistence.DataEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("role_permission")
public class RolePermission extends DataEntity<RolePermission> {

private static final long serialVersionUID = 12L;

//    @TableId(value="id", type= IdType.INPUT)
//    private Long id;

    private Long roleId;

    private Long permissionId;

//    private LocalDateTime createTime;
//
//    private LocalDateTime updateTime;

    public static final String ID = "id";
    public static final String ROLE_ID = "role_id";
    public static final String PERMISSION_ID = "permission_id";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
            return this.id;
        }

}