package com.xqh.severshiro.exceptions;

import com.xqh.severshiro.enums.CodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gjf6316
 * @date 2019/1/23 14:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -571981181384928225L;
    private String message;
    private int code;

    public BaseException(CodeEnum errorCode)
    {
        super(errorCode.getMsg());
        this.message = errorCode.getMsg();
        this.code = errorCode.getCode();
    }

    public BaseException(int code, String message){
        super(message);
        this.message = message;
        this.code = code;
    }
}
