package com.xqh.commoncore.constant;

/**
 * 公用常量
 *
 * @author ye
 * @date 2019/3/17 12:08
 */
public class CommonConstant {

    /**
     * 默认系统编号
     */
    public static final String SYS_CODE = "EXAM";

    /**
     * 默认系统编号
     */
    public static final String VISITOR_SYS_CODE = "EXAM";

    /**
     * 默认系统编号
     */
    public static final String MANAGE_CODE = "MANAGE";

    /**
     * 默认租户编号
     */
    public static final String TENANT_CODE = "GITHUB";

    /**
     * JSON 资源
     */
    public static final String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 正常
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 异常
     */
    public static final String STATUS_EXCEPTION = "1";

    /**
     * 锁定
     */
    public static final String STATUS_LOCK = "9";

    /**
     * jwt签名
     */
    public static final String SIGN_KEY = "EXAM";

    /**
     * 页码
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 分页大小
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序
     */
    public static final String SORT = "sort";

    /**
     * 排序方向
     */
    public static final String ORDER = "order";

    /**
     * 默认页数
     */
    public static final String PAGE_NUM_DEFAULT = "1";

    /**
     * 默认分页大小
     */
    public static final String PAGE_SIZE_DEFAULT = "10";

    /**
     * 默认排序
     */
    public static final String PAGE_SORT_DEFAULT = "create_time";

    /**
     * 默认排序方向
     */
    public static final String PAGE_ORDER_DEFAULT = "false";

    /**
     * 正常状态
     */
    public static final Integer DEL_FLAG_NORMAL = 0;

    /**
     * 删除状态
     */
    public static final Integer DEL_FLAG_DEL = 1;

    /**
     * 路由配置在Redis中的key
     */
    public static final String ROUTE_KEY = "_ROUTE_KEY";

    /**
     * 菜单标识
     */
    public static final String MENU = "0";

    /**
     * token请求头名称
     */
    public static final String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    public static final String TOKEN_SPLIT = "Bearer ";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 默认排序
     */
    public static final String DEFAULT_SORT = "30";

    /**
     * utf-8
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 阿里
     */
    public static final String ALIYUN_SMS = "aliyun_sms";

    /**
     * 参数校验失败
     */
    public static final String IllEGAL_ARGUMENT = "参数校验失败！";

    /**
     * 保存code的前缀
     */
    public static final String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    /**
     * Bearer
     */
    public static final String AUTHORIZATION_BEARER = "Bearer ";

    /**
     * 默认用户
     */
    public static final String CURRENT_USER_DEFAULT ="system";

    /**
     * 通用字段creator
     */
    public static final String COMMON_FIELD_CREATOR="creator";

    public static final String COMMON_FIELD_UPDATER="updater";

    public static final String COMMON_FIELD_CREATE_TIME="create_time";

    public static final String COMMON_FIELD_UPDATE_TIME="update_time";

    public static final String COMMON_FIELD_ID="id";

    public static final String COMMON_FIELD_DEL_FLAG="del_flag";

    public static final String DATE_FORMAT="yyyy-MM-dd";

    public static final String DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";

    public static final String TIMEZONE_SHANGHAI ="Asia/Shanghai";

    public final static String TIME_ZONE_EAST_EIGHT = "GMT+8";

    public static final String DOT =".";

    public static final String SLASH ="/";

    public static final String SEMICOLON =";";

}

