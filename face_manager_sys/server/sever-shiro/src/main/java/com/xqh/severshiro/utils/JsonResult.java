package com.xqh.severshiro.utils;

import com.xqh.severshiro.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gjf
 * @date 2019/4/4 13:46
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class JsonResult<T> {

    private Integer code;

    private String msg;

    private T data;

    private JsonResult(){
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getMsg();
    }

    private JsonResult(T data){
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private JsonResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private JsonResult(CodeEnum codeEnum){
        if (codeEnum == null){
            return;
        }
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public static <T> JsonResult<T> success(){
        return new JsonResult<>();
    }

    public static <T> JsonResult<T> success(T data){
        return new JsonResult<>(data);
    }

    public static <T> JsonResult<T> error(CodeEnum codeEnum){
        return new JsonResult<>(codeEnum);
    }

    public static <T> JsonResult<T> error(Integer code, String msg) {
        return new JsonResult<>(code, msg);
    }
}