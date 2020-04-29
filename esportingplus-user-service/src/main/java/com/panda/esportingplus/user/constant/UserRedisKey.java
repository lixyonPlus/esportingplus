package com.panda.esportingplus.user.constant;

/**
 * @author shusong.liang
 * @Title: UserRedisKey
 * @Description: 用户服务redis key 常量
 * @date 2020/03/25 20:11
 */
public class UserRedisKey {

    /**
     * 应用命名
     */
    public static final String KEY_APP = "user";

    /**
     * key 分隔符
     */
    public static final String KEY_SEPARATOR = ":";

    /**
     * 用户基础信息
     */
    public static final String USER_BASE_INFO = KEY_APP + KEY_SEPARATOR + "base";

    /**
     * 用户绑定信息
     */
    public static final String USER_BIND_MAPPING_USERID =
            KEY_APP + KEY_SEPARATOR + "bind" + KEY_SEPARATOR + "mapping";



    /**
     * 用户注册锁key
     */
    public static final String USER_REGIST_LOCK_KEY = "members.register3.union_id.%s";

    /**
     * 用户登录token key
     */
    public static final String PYTHON_LOGIN_TOKEN_KEY = "%s:%s";

    /**
     * 用户登录信息 key
     */
    public static final String USER_LOGIN_KEY = "user:%s";

    /**
     * 用户访问token key
     */
    public static final String ACCESS_TOKEN_KEY = "access:%s";

    /**
     * 刷新token key
     */
    public static final String ACCESS_TO_REFRESH_KEY = "access_to_refresh:%s";


    /**
     * 用户id自增
     */
    public static final String USER_UID_INCR_KEY =
            KEY_APP + KEY_SEPARATOR + "uid" + KEY_SEPARATOR + "incr";

    /**
     * 用户id自增锁
     */
    public static final String USER_UID_INCR_LOCK_KEY =
            USER_UID_INCR_KEY + KEY_SEPARATOR + "lock";

    /**
     * 用户白名单
     */
    public static final String USER_WHITELIST =
            KEY_APP + KEY_SEPARATOR + "whitelist";

    /**
     * 用户白名单
     */
    public static final String PHONECODE = "phone_code%s";

    /**
     * 用户初始化邀请记录redis锁key
     */
    public static final String STATISTICS_INIT_REDIS_LOCK =
            KEY_APP + KEY_SEPARATOR + "statistics_init_lock_userid" + KEY_SEPARATOR + "%s";












    /**
     * 订单统计 防重key
     */
    public static final String ORDER_STATISTICS_KEY = KEY_APP + KEY_SEPARATOR + "order:statistics:%s";

}
