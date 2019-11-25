package com.xqh.serverfile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回数据
 *
 * @author ye
 * @date 2019/3/17 12:08
 */
@Data
@ApiModel(value="ResponseBean«T»",description="在线课堂考试显示对象")
public class ResponseBean<T> implements Serializable {

    public static final long serialVersionUID = 42L;

    public static final int NO_LOGIN = -1;

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 失败
     */
    public static final int FAIL = 500;

    /**
     * 验证码错误
     */
    public static final int INVALID_VALIDATE_CODE_ERROR = 478;

    /**
     * 验证码过期错误
     */
    public static final int VALIDATE_CODE_EXPIRED_ERROR = 479;

    /**
     * 用户名不存在或密码错误
     */
    public static final int USERNAME_NOT_FOUND_OR_PASSWORD_ERROR = 400;

    /**
     * 当前操作没有权限
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 当前操作没有权限
     */
    public static final int NO_PERMISSION = 403;
    /**
     * 数据库unique值重复
     */
    public static final int NOT_UNIQUE = 371;

    public static final int INVALID_PARAM = 375;

    /**
     * 导入错误
     */
    public static final int IMPORT_ERROR = 380;

    public static final int DUPLICATE_SUBMIT_ERROR = 390;

    public static final int DUPLICATE_PASS_ERROR = 391;

    public static final int EXAM_FAIL = 530;

    public static final int BUSY = 540;

    @ApiModelProperty(value="返回信息",name="msg")
    private String msg = "success";

    @ApiModelProperty(value="返回代码",name="code",notes = "200-成功 其他错误码根据功能")
    private int code = SUCCESS;

    /**
     * http 状态码
     */
    @ApiModelProperty(value="http状态代码",name="status",notes = "200-成功 其他错误码根据功能")
    private int status = 200;

    @ApiModelProperty(value="数据",name="data")
    private T data;

    public ResponseBean() {
        super();
    }

    public ResponseBean(T data) {
        super();
        this.data = data;
    }

    public ResponseBean(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public ResponseBean(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public ResponseBean(Throwable e, int code) {
        super();
        this.msg = e.getMessage();
        this.code = code;
    }

    public ResponseBean(int code) {
        super();
        this.code = code;
    }
}
