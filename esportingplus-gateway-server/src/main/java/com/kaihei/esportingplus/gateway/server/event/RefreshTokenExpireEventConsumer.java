package com.kaihei.esportingplus.gateway.server.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.kaihei.esportingplus.common.event.EventConsumer;
import com.kaihei.esportingplus.gateway.server.data.manager.AuthServerRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenExpireEventConsumer extends EventConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthServerRestClient authServerRestClient;

    /**
     * 刷新token剩余时间
     * @param event
     */
    @Subscribe
    @AllowConcurrentEvents //开启线程安全
    public void accessToken(RefreshTokenExpireEvent event) {
        authServerRestClient.accessToken(event.getUserName());
    }

}
