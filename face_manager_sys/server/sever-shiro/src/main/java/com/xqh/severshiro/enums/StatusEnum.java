package com.xqh.severshiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: gjf
 * @Date: 2019/3/18 14:07
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    /**
     *
     */
    NORMAL(1,"正常"),
    DELETE(-1,"删除");

    private int status;
    private String msg;
}
