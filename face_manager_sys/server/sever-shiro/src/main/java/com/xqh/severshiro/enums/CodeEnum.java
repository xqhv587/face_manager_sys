package com.xqh.severshiro.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 *
 * @author gjf
 * @since 2019-04-04
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {
    /**
     * 错误码
     */
    SUCCESS(0, "success"),
    NOT_EMPTY(50009,"不能为空"),
    FAILED(50010,"fail"),
    NOT_FIND(50011,"数据不存在"),
    DELETE_FAILED(50012,"删除失败"),
    NOT_FIND_PROJECT(50013,"不存在该Component"),
    UPDATE_FAILED(50014,"更新失败"),
    EXIST_SAME_NAME(50015,"已存在同名"),
    USERNAME_OR_PASSWORD_NOT_EMPTY(50016,"用户名密码不能为空"),
    LOCKED_ACCOUNT(50017,"用户已经被锁定不能登录，请与管理员联系！"),
    ERROR_PASSWORD(50018,"用户名或密码错误"),
    PERMISSION_DENIED(50019,"无权限"),
    ERROR_PARAMETER(50020,"格式错误"),
    ERROR_DELETE(50021, "正在使用，暂不能删除！"),
    ADMIN_NOT_DELETE(50022, "管理员不能删除"),
    CREATE_EXCEL_FAILED(50023, "创建excel失败"),
    EXIST_SAME_LEVEL(50024,"已存在同等级"),
    ADD_FAILED(50025,"添加失败"),
    ISSUE_TYPE_NOT_EXITS(50026,"issueType不存在"),
    ERROR_PRODUCT(50027,"该component存在不同名的product"),
    CHANGE_PRODUCT(50028,"请绑定产品"),
    NEED_RE_LOGIN(50029,"登录超时，请重新登录");

    private Integer code;
    private String msg;
}
