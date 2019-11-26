package com.xqh.severshiro.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="UserVo",description="用户视图对象")
public class UserVo implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value="id",name="id")
        private Long id;

        @ApiModelProperty(value="name",name="用户名")
        private String name;

        @ApiModelProperty(value="age",name="年龄")
        private Integer age;

        @ApiModelProperty(value="password",name="密码")
        private String password;

        @ApiModelProperty(value="roleId",name="角色id")
        private Long roleId;


}