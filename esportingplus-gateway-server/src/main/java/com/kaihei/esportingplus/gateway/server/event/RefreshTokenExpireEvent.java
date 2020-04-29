package com.kaihei.esportingplus.gateway.server.event;

import com.kaihei.esportingplus.common.event.Event;

public class RefreshTokenExpireEvent implements Event {

    private String userName;

    public RefreshTokenExpireEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
