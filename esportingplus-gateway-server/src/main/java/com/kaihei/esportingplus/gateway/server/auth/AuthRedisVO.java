package com.kaihei.esportingplus.gateway.server.auth;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AuthRedisVO implements Serializable {

    private static final long serialVersionUID = -6905522894377698717L;

    private List<Map> authorities;

    private boolean clientOnly;

    private boolean authenticated;

    private String name;

    public List<Map> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Map> authorities) {
        this.authorities = authorities;
    }

    public boolean isClientOnly() {
        return clientOnly;
    }

    public void setClientOnly(boolean clientOnly) {
        this.clientOnly = clientOnly;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
