package com.xqh.severshiro.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;


            private Integer id;
    /**
     * 规则名
     */

            private String roleName;

            private LocalDateTime createTime;

            private LocalDateTime updateTime;

}