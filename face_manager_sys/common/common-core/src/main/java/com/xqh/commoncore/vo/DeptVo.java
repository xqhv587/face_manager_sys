package com.xqh.commoncore.vo;

import com.xqh.commoncore.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门vo
 *
 * @author tangyi
 * @date 2018/12/31 22:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends BaseEntity<DeptVo> {

    private String deptName;
}
