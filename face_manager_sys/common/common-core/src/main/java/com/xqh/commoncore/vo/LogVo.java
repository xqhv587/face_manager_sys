package com.xqh.commoncore.vo;

import com.xqh.commoncore.model.Log;
import com.xqh.commoncore.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * logVo
 *
 * @author tangyi
 * @date 2019-01-05 17:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogVo extends BaseEntity<LogVo> {

    private Log log;

    private String username;
}
