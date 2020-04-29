/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.panda.esportingplus.common.config;

import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Configuration
@ConditionalOnExpression("!'${auth}'.isEmpty()")
@ConfigurationProperties(prefix = "auth")
public class AuthorityropertiesConfig {

    private int access_token_expire;
    private int refresh_token_expire;
    /**
     * 客户端id
     */
    private String	client_id;

    /**
     * 客户端密码
     */
    private String	client_secret;

    /**
     * scope
     */
    private String	scope;

    /**
     * RSA private 密匙
     */
    private String	private_key;

    /**
     * RSA public 公匙
     */
    private String	public_key;
    /**
     * // true 直接跳转到客户端页面，false 跳转到用户确认授权页面
     */
    private boolean auto_approve;

    private List<String> grant_types;

    public int getAccess_token_expire() {
        return access_token_expire;
    }

    public void setAccess_token_expire(int access_token_expire) {
        this.access_token_expire = access_token_expire;
    }

    public int getRefresh_token_expire() {
        return refresh_token_expire;
    }

    public void setRefresh_token_expire(int refresh_token_expire) {
        this.refresh_token_expire = refresh_token_expire;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public boolean isAuto_approve() {
        return auto_approve;
    }

    public void setAuto_approve(boolean auto_approve) {
        this.auto_approve = auto_approve;
    }

    public List<String> getGrant_types() {
        return grant_types;
    }

    public void setGrant_types(List<String> grant_types) {
        this.grant_types = grant_types;
    }
}
