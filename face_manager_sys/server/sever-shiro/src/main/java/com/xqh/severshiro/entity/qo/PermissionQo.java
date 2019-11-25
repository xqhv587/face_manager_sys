package com.xqh.severshiro.entity.qo;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author gjf
 * @since 2019-04-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value="PermissionQo",description="权限查询对象")
public class PermissionQo implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;

        private String title;

        private String icon;

        private String spread;

        private String href;

        private Integer parentId;

        private Integer sort;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;

}