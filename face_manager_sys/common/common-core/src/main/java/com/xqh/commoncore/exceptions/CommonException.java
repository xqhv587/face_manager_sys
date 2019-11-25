package com.xqh.commoncore.exceptions;

import lombok.Data;

/**
 * 公共异常
 *
 * @author ye
 * @date 2019/3/16 20:28
 */
@Data
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public CommonException() {

    }

    public CommonException(Integer code) {
        this.code = code;
    }

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(Throwable throwable) {
        super(throwable);
    }

    public CommonException(Throwable throwable, String msg) {
        super(throwable);
    }
}
