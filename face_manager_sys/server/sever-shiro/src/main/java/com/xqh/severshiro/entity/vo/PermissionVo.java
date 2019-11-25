package com.xqh.severshiro.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="PermissionVo",description="权限视图对象")
public class PermissionVo implements Serializable {

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