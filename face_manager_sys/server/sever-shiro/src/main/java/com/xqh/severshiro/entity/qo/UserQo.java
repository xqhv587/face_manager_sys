package com.xqh.severshiro.entity.qo;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
@ApiModel(value="UserQo",description="用户查询对象")
public class UserQo implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value="id",name="id")
        private Long id;

        @ApiModelProperty(value="name",name="用户名")
        private String name;

        @ApiModelProperty(value="age",name="年龄")
        private Integer age;

        @ApiModelProperty(value="address",name="地址")
        private String address;

        @ApiModelProperty(value="password",name="密码")
        private String password;

        @ApiModelProperty(value="roleId",name="角色id")
        private Long roleId;

}