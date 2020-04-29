package com.panda.esportingplus.common.constant;

/**
 * 常量信息
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class CommonConstants {


    /**
     * 通用成功标识
     */
    public static final int SUCCESS = 0;

    /**
     * 通用失败标识
     */
    public static final int FAIL = 1;

    /**
     * 通用启用
     */
    public static final int ACTIVE = 1;

    /**
     * 通用禁用
     */
    public static final int DISABLE = 0;


    /**
     * 微信支付成功标识
     */
    public static final String WEIXIN_PAY_SUCCESS = "SUCCESS";

    /**
     * 微信支付失败标识
     */
    public static final String WEIXIN_PAY_FAIL = "FAIL";


    public static final String USER_INFO = "userInfo";

    /**
     * 一天的秒数 86400
     */
    public static final int ONE_DAY_SECONDS = 24 * 60 * 60;

    /**
     * 一小时的秒数 3600
     */
    public static final int ONE_HOUR_SECONDS = 60 * 60;

    /**
     * 项目环境
     */
    public static final String PROJECT_ENV="project.env";

    public static final int MQ_RETRY_COUNT=5;

}
