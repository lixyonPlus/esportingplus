package com.panda.esportingplus.user.constant;

/**
 * @author shusong.liang
 * @Title: MembersAuthConstants
 * @Description: TODO
 * @date 2020/3/27 17:51
 */
public class MembersAuthConstants {

    public final static String
        /** 用户游戏统计接单数*/
        USER_GAME_STASTICS_TYPE_RECEIVEORDER = "1",

        SENSORS_REGISTLOGIN_TYPE_UNKNOW = "0",

        SENSORS_REGISTLOGIN_TYPE_WX = "1",

        SENSORS_REGISTLOGIN_TYPE_QQ = "2",

        SENSORS_REGISTLOGIN_TYPE_PHONE = "3",

        SENSORS_REGISTLOGIN_TYPE_H5WX = "4",

        SENSORS_EVENT_NAME_REGIST = "RegisterSuccess",

        SENSORS_EVENT_NAME_LOGIN = "Login",

        SENSORS_EVENT_NAME_WPA_LOGIN = "WechatPALogin",

        SENSORS_EVENT_NAME_MARGIN_PAY = "PayInsurance",

        SENSORS_EVENT_NAME_LEVELCHANGE = "BaojiLevelChange",

        PLATFORM_QQ = "QQ",

        PLATFORM_WX = "WX",

        PLATFORM_PHONE = "PH",

        PACKAGE_NAME_MP = "miniprogram",

        PACKAGE_NAME_KH = "kaihei",

        PACKAGE_NAME_BAOBAO = "baobao",

        APP_TOKEN = "app_token",

        PC_TOKEN = "pc_token",

        MP_TOKEN = "mp_token",

        WPA_TOKEN = "wpa_token",

        UNKOWN_TOKEN = "unkown_token",

        REGISTER_WAY_WX = "WX",

        REGISTER_WAY_QQ = "QQ",

        REGISTER_WAY_MP = "MP",

        REGISTER_WAY_PHONE = "phone",

        REGISTER_WAY_H5WX = "H5WX",

        REGISTER_WAY_H5QQ = "H5QQ",

        USER_EVENT_NAME_REGISTER_SUCCESS = "RegisterSuccess",

        USER_EVENT_NAME_LOGIN = "Login",

        USER_EVENT_NAME_ACCOUNT_BINDING = "AccountBinding",

        /** 奖励订单类型 */
        BANK_BILL_TYPE_BONUS = "4",

        /** 用户模块注册登录生产组mq key*/
        USER_MQ_REGIST_LOGIN_PRODUCER_GROUP_KEY = "user_regist_login_producer_group",

        /** 用户模块注册登录greeting消费组mq key*/
        USER_MQ_REGIST_LOGIN_GREETING_CONSUMER_GROUP_KEY = "user_regist_login_greeting_consumer_group",

        /** 用户模块注册登录regist消费组mq key*/
        USER_MQ_REGIST_LOGIN_REGIST_CONSUMER_GROUP_KEY = "user_regist_login_regist_consumer_group",

        /** 用户模块注册登录salogin消费组mq key*/
        USER_MQ_REGIST_LOGIN_SALOGIN_CONSUMER_GROUP_KEY = "user_regist_login_salogin_consumer_group",

        /** 用户模块注册登录tracksignup消费组mq key*/
        USER_MQ_REGIST_LOGIN_TRACKSIGNUP_CONSUMER_GROUP_KEY = "user_regist_login_tracksignup_consumer_group",

        /** 用户绑定第三方账号satrack消费组mq key*/
        USER_MQ_AUTH3_BIND_SATRACK_CONSUMER_GROUP_KEY = "user_auth3_bind_satrack_consumer_group",

        USER_MQ_PICTURE_VERIFY_CONSUMER_GROUP_KEY = "user_picture_verify_consumer_group",

        /** 用户模块注册登录mq主体key*/
        USER_MQ_REGIST_LOGIN_TOPIC_KEY = "user_regist_login_topic",

        /** 用户模块注册登录主体mq salogin tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_SALOGIN_KEY = "user_regist_login_tag_salogin",

        /** 用户模块注册登录主体mq greeting tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_GREETING_KEY = "user_regist_login_tag_greeting",

        /** 用户模块注册登录主体mq sasignup tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_SASIGNUP_KEY = "user_regist_login_tag_sasignup",

        /** 用户模块注册登录主体mq profileset tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_PRFILESET_KEY = "user_regist_login_tag_profileset",

        /** 用户模块注册登录主体mq createbank tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_CREATEBANK_KEY = "user_regist_login_tag_createbank",

        /** 用户模块注册登录主体mq update_rtoken tag*/
        USER_MQ_REGIST_LOGIN_TOPIC_TAG_UPDATERTOKEN_KEY = "user_regist_login_tag_update_rtoken",

        /** 用户绑定第三方账号mq satrack tag*/
        USER_MQ_AUTH3_BIND_TOPIC_TAG_SATRACK_KEY = "user_auth3_bind_tag_satrack",

        /** 用户照片审核mq*/
        USER_MQ_PICTURE_VERIFY_TOPIC_TAG_KEY = "user_picture_verify_topic_tag_key";

    public final static int SYSTEM_CLOSE = 1,

        SYSTEM_WHITE_LIST = 2,

        /** 用户注册福利*/
        BILL_TYPE_REDISTER_BONUS = 9;

}
