package com.kaihei.esportingplus.gateway.server.controller;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.constant.RedisKey;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackList")
public class BlackListController {

    private CacheManager cacheManager = CacheManagerFactory.create();

    @PostMapping("/add")
    public ResponsePacket addblackList(@RequestBody List<String> blackList) {
        cacheManager.sAdd(RedisKey.RATELIMITE_BLACKLIST,blackList.toArray(new String[blackList.size()]));
        return ResponsePacket.onSuccess();
    }

}