package com.xqh.severshiro.entity.qo;

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
 * @author gjf
 * @since 2019-04-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleQo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    /**
     * 规则名
     */

    private String roleName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}