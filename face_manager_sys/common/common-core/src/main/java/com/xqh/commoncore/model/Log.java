package com.xqh.commoncore.model;

import com.xqh.commoncore.persistence.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日志
 *
 * @author ye
 * @date 2018/10/31 20:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Log extends BaseEntity<Log> {

    /**
     * 日志类型
     */
    private String type;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 操作用户的IP地址
     */
    private String ip;

    /**
     * 操作用户代理信息
     */
    private String userAgent;

    /**
     * 操作的URI
     */
    private String requestUri;

    /**
     * 操作的方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 耗时
     */
    private String time;
}
