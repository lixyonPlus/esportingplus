package com.panda.esportingplus.common.constant;

/**
 * 描述
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @updatetor
 */
public class SecurityConstants {

    /**
     * 默认保存验证码code的前缀
     */
    public static final String LOG_OUT = "/oauth/logout";

    /**
     * 默认保存验证码code的前缀
     */
    public static final String CODE_KEY = "CODE_KEY_";
    /**
     * 前缀
     */
    public static final String ESPORT_PREFIX = "esport_";
    /**
     * 项目的license
     */
    public static final String PIG_LICENSE = "made by esport";
    /**
     * 授权码模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 密码模式
     */
    public static final String PASSWORD = "password";
    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    /**
     * 登陆token
     */
    public static final String ACCESS_TOKEN = "access_token";

    /**
     * Authorization
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * oauth token
     */
    public static final String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * /oauth/authorize
     */
    public static final String OAUTH_AUTHORIZE_URL = "/oauth/authorize";

    /**
     * oauth/check_token
     */
    public static final String OAUTH_CHECK_TOKEN_URL = "/oauth/check_token";

    /**
     * 手机登录URL
     */
    public static final String MOBILE_TOKEN_URL = "/mobile/token";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    public static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    public static final String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS
            + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    public static final String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    public static final String DEFAULT_SELECT_STATEMENT =
            BASE_FIND_STATEMENT + " where client_id = ?";

}