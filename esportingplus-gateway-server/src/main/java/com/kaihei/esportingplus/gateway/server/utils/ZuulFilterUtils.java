package com.kaihei.esportingplus.gateway.server.utils;

import com.kaihei.esportingplus.gateway.server.config.FilterIgnorePropertiesConfig;
import org.springframework.util.AntPathMatcher;

public class ZuulFilterUtils {

    /**
     *@Description: 检查忽略的接口
     *@param: [resUrl, filterIgnorePropertiesConfig]
     *@return: boolean
     *@throws:
     *
     *@author  Orochi-Yzh
     *@dateTime  2018/10/9 10:50
    */
    public static boolean checkIgnore(String resUrl, FilterIgnorePropertiesConfig filterIgnorePropertiesConfig) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        //1.过虑不需要限流的接口路径
        return  filterIgnorePropertiesConfig.getUrls()
                .stream()
                .anyMatch(url -> antPathMatcher.match(url, resUrl));
    }
}
