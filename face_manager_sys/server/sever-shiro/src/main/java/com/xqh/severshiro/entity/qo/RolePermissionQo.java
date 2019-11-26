package com.xqh.severshiro.entity.qo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author xqh
 * @since 2019-04-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionQo implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;

        private Long roleId;

        private Long permissionId;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

}