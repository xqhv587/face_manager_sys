package com.xqh.severshiro.exceptions;

import com.xqh.severshiro.enums.CodeEnum;
import com.xqh.severshiro.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

/**
 * @author gjf6316
 * @date 2019/1/23 14:20
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {
    /**
     * 自定义失败异常
     */
    @ExceptionHandler(value = BaseException.class)
    public JsonResult businessException(BaseException e) {
        log.error(e.getMessage(), e);
        return JsonResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public JsonResult bindException(BindException e){
        log.error(e.getMessage(), e);
        String result = e.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));

        return JsonResult.error(CodeEnum.ERROR_PARAMETER.getCode(), result);
    }

    @ExceptionHandler(value = Exception.class)
    public JsonResult myExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return JsonResult.error(CodeEnum.FAILED);
    }

}
