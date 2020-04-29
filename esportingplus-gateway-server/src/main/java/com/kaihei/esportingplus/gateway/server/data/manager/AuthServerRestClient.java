package com.kaihei.esportingplus.gateway.server.data.manager;

import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.config.AuthorityropertiesConfig;
import com.kaihei.esportingplus.common.tools.HttpUtils;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthServerRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServerRestClient.class);

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private AuthorityropertiesConfig authorityropertiesConfig;

    private static final String AUTH_BASE_URL = "http://esportingplus-auth-server";
    private static final String AUTH_CHECK_URL = "/oauth/check_token";
    private static final String AUTH_URL = "/oauth/token";

    private static final String AUTH_USER_URL = "http://esportingplus-user-service";
    private static final String AUTH_REFRESH = "/token/refresh-token";

    public ResponseEntity<Map> checkToken(String token) {
        String url = AUTH_BASE_URL + AUTH_CHECK_URL;
        LOGGER.debug("token过期校验：url={},param={}", url, token);
        return restTemplate.getForEntity(url + "?token=" + token, Map.class, token);
    }

    public void accessToken(String tokenOrUid) {
        String url = AUTH_BASE_URL + AUTH_URL
                + "?grant_type=password"
                + "&username=" + tokenOrUid
                + "&password="
                + "&client_id=" + authorityropertiesConfig.getClient_id()
                + "&client_secret=" + authorityropertiesConfig.getClient_secret();

        LOGGER.debug("刷新token剩余时间：url={},userName={}", url, tokenOrUid);
        restTemplate.postForObject(url, HttpUtils.buildParam(tokenOrUid, null), Map.class);
    }

//    public Map<String, String> refreshToken(String tokenOrUid, String refreshToken) {
//        String url = AUTH_BASE_URL + AUTH_URL
//                + "?grant_type=refresh_token&refresh_token="
//                + refreshToken
//                + "&client_id=" + authorityropertiesConfig.getClient_id()
//                + "&client_secret=" + authorityropertiesConfig.getClient_secret();
//
//        LOGGER.debug("token过期了，执行刷新：url={},oleToken={},refreshToken={}", url, tokenOrUid, refreshToken);
//        return restTemplate.postForObject(url, HttpUtils.buildParam(tokenOrUid, null), Map.class);
//    }

    public ResponsePacket<String> refreshToken(String pythonToken, String version) {
        String url = AUTH_USER_URL + AUTH_REFRESH
                + "?pythonToken="
                + pythonToken
                + "&version="
                + version;

        LOGGER.debug("token过期了，执行刷新：url={},pythonToken={},version={}", AUTH_REFRESH, pythonToken,
                version);
        return restTemplate.getForObject(url, ResponsePacket.class);
    }

}