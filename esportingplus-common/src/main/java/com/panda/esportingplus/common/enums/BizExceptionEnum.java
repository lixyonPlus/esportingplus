package com.panda.esportingplus.common.enums;

/**
 * 业务异常类型 范围定义: 5001-5999 公共错误码 6001-6999 待定 7001-7999 订单错误码 8001-8999 用户错误码
 *
 * @author shusong.liang
 */
public enum BizExceptionEnum {

    // 公共错误码
    /**
     * 返回成功
     */
    SUCCESS(0, "success"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-1, "未知错误"),

    DB_FAIL(-2, "数据库异常"),
    /**
     * 服务器内部异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器正在打排位赛"),
    URL_NOT_FOUND(404, "访问地址不存在"),
    INVALID_TOKEN(401, "token非法"),
    INVALID_REFRESH_TOKEN(402, "refresh_token非法"),
    PERMISSIONS_ACCESS_DENIED(403, "权限不足，访问拒绝"),
    INVALID_PY_TOKEN(4001, "token校验失败"),
    BAD_REQUEST(400, "非法请求"),
    BLACK_LIST(423, "当前请求IP已被拉入黑名单"),
    TOO_MANY_REQUEST(429, "官人!用力太猛了，系统开启大招，排会儿队歇息一下吧"),
    READ_LIMIT_LUA_ERROR(600, "读取限流lua脚本配置异常"),
    REDIS_LOCK_PARAM_ERR(601, "Redis分布式锁参数有误"),
    /**
     * 参数验证失败
     */
    PARAM_VALID_FAIL(5001, "参数验证失败 [%s]"),
    /**
     * 文件上传
     */
    FILE_UPLOAD_FAIL(5002, "文件[%s]上传失败"),
    /**
     * 生成二维码
     */
    CREATE_TWO_CODE_FAIL(5003, "内容[%s]生成二维码失败"),
    CREATE_ELASTIC_JOB_FAIL(5004, "添加任务[%s]失败"),
    FILE_DOWNLOAD_FAIL(5006, "文件下载失败"),
    DICT_NOT_FOUND(5100, "未找到数据字典的数据"),
    DICT_EVALUATE_STAR_TIPS_NOT_FOUND(5101, "未找到星级评价提示文案配置"),
    DICT_EVALUATE_STAR_TAG_NOT_FOUND(5102, "未找到星级评价标签配置"),
    DICT_USER_HOME_TAG_SHOW_COUNT_NOT_FOUND(5103, "未找用户主页形象标签展示数量配置"),
    DICT_USER_HOME_TAG_SHOW_COUNT_ILLEGAL(5104, "用户主页形象标签展示数量不能小于0"),
    DICT_EVALUATE_TAG_NOT_FOUND(5106, "未找到评价标签"),
    DICT_EVALUATE_TAG_EXISTED(5107, "标签名重复"),
    DICT_EVALUATE_TIPS_ILLEGAL(5108, "星级评价提示文案不能为空"),
    /**
     * 服务熔断
     */
    HYSTRIX_SERVER(5555, "服务熔断"),
    GATEWAY_FALLBACK(5556, "系统繁忙, 请稍后再试!"),
    APP_VERSION_CONFIG_NOT_FOUND(5557, "[%s]版本信息未配置, 请稍后再试!"),

    /**
     * 开车到解散/结束操作太快(30s内)
     */
    TEAM_OPERATE_TOO_FAST(6031, "您的操作太快了, 请稍后再试"),
    /**
     * 操作频繁(其实是 mq 消息发送失败了...)
     */
    TEAM_MQ_SEND_FAIL(6032, "您的操作太快了, 请稍后再试!"),
    /**
     * 入参缺失
     */
    PARAM_ENTRY_ERROR(8889, "参数错误！"),


    /**
     * 用户头像不存在
     */
    USER_AVATAR_NOT_EXIST(8006, "用户头像不存在"),
    USER_SYSTEM_MAINTAINING(8007, "服务器维护中, 请稍后再试~"),
    USER_SYSTEM_THIRDPART_VALID_FAIL(8009, "第三方登录验证失败"),
    USER_MAX_REGIST_PER_DEVICE_LIMIT(8010, "该设备注册账户次数已超过上限，请使用其他设备注册。"),
    USER_SYSTEM_LOGIN_LOCK(8011, "此账号登录功能已被限制，请联系客服qq:XXXXXXXXX"),
    USER_MAX_LOGIN_PER_DEVICE_LIMIT(8012, "该设备登录账户次数已超过上限，请使用其他设备登录。"),
    USER_SYSTEM_PHONE_REGIST_FAIL(8013, "手机号注册失败"),
    USER_VALIDATE_SENSITIVE_WORD(8014, "用户名含有敏感词，请修改后重新提交"),
    USER_GENERATE_UID_FAIL(8015, "用户分配uid失败"),
    USER_VALIDATE_DESC_SENSITIVE_WORD(8016, "个人简介含有敏感词，请修改后重新提交"),
    USER_AVATAR_VERIFY(8017, "你的头像正在审核中，请稍后查看"),
    USER_AVATAR_VERIFY_FAIL(8018, "你的头像不符合规范，请重新上传"),

    IM_MSG_SEND_ERROR(200001, "im消息发送失败"),
    IM_MSG_STATE_ENUM_ERROR(200002, "im消息状态枚举错误"),


    /**
     * API鉴权
     */
    TOKEN_EXPIRED(11000, "Token过期"),
    AUTH_REVOKE_FAIL(11002, "Token注销失败,已经被注销"),
    EMPTY_TOKEN(11003, "Authorization 参数为空"),
    DECODE_TOKEN_FAIL(11004, "解析BASIC TOKEN失败"),
    INVALID_BASIC_TOKEN(11005, "错误的BASIC TOKEN"),
    INVALID_CLIENT(11005, "错误的ClientId或ClientSecret"),
    INIT_AUTHURL_ERROR(11007, "初始化权限异常"),
    LOAD_AUTHURL_ERROR(11008, "获取权限异常"),
    REFRESH_TOKEN_ERROR(11009, "token 刷新失败"),
    UID_INFO_EMPTY(11010, "数据异常，找不到用户信息，需重新登陆。Token:[{0}]"),
    INIT_CLIENT_ERROR(110011, "预热客户端信息异常"),
    ERROR_TOKEN(110012, "Authorization 参数错误");

    private int errCode;
    private String errMsg;

    BizExceptionEnum(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static BizExceptionEnum fromCode(int code) {
        for (BizExceptionEnum c : BizExceptionEnum.values()) {
            if (c.errCode == code) {
                return c;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    public static BizExceptionEnum fromDesc(String desc) {
        for (BizExceptionEnum c : BizExceptionEnum.values()) {
            if (c.errMsg.equals(desc)) {
                return c;
            }
        }
        return UNKNOWN_ERROR;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
