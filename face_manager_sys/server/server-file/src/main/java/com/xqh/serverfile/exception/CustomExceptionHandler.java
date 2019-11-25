package com.xqh.serverfile.exception;

import com.xqh.serverfile.model.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

/**
 * 全局异常处理
 *
 * @author ye
 * @since 2019-06-13
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(MediaTypeException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseBean<Boolean> mediaTypeExceptionHandler(MediaTypeException ex) {
        log.error("MediaTypeException",ex);
        return new ResponseBean<>(ex, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseBean<Boolean> multipartExceptionHandler(MaxUploadSizeExceededException ex) {
        log.error("MaxUploadSizeExceededException",ex);
        return new ResponseBean<>(ex, HttpStatus.PAYLOAD_TOO_LARGE.value());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseBean<Boolean> multipartExceptionHandler(MultipartException ex) {
        log.error("MultipartException",ex);
        return new ResponseBean<>(ex, HttpStatus.PAYLOAD_TOO_LARGE.value());
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<Boolean> exceptionHandler(Exception ex) {
        log.error("globalException",ex);
        return new ResponseBean<>(ex,ResponseBean.FAIL);
    }

}
