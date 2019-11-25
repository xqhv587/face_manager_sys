package com.xqh.serverfile.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共异常
 *
 * @author ye
 * @date 2019/3/16 20:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MediaTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public MediaTypeException() {

    }

    public MediaTypeException(Integer code) {
        this.code = code;
    }

    public MediaTypeException(String msg) {
        super(msg);
    }

    public MediaTypeException(Throwable throwable) {
        super(throwable);
    }

    public MediaTypeException(Throwable throwable, String msg) {
        super(throwable);
    }
}
