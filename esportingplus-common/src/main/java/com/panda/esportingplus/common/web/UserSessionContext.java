package com.panda.esportingplus.common.web;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.common.config.CommonApplicationContext;
import com.panda.esportingplus.common.data.JsonSerializable;
import com.panda.esportingplus.common.tools.TokenParseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @Description:  用户信息
 */
@Slf4j
public class UserSessionContext implements JsonSerializable {

    private static ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> userUidThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Map<String, UserSessionContext>> userSessionContextThreadLocal = new ThreadLocal<>();
    protected static CacheManager cacheManager = CacheManagerFactory.create();

    private static final long serialVersionUID = 1495413060843290738L;
    private int id;
    private String username;
    private String uid;
    private String avatar;
    private int sex;
    private String chickenId;


    public UserSessionContext() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getChickenId() {
        return chickenId;
    }

    public void setChickenId(String chickenId) {
        this.chickenId = chickenId;
    }


    /**
     * 默认的头像链接, 临时需求
     */
    public static final String DEFAULT_AVATAR
            = "https://qn-bn-pub.kaiheikeji.com/baobao/defalutavatar/baobaodefalutavatar.png";

    public static void setToken(String attributes) {
        tokenThreadLocal.set(attributes);
    }

    public static String getToken() {
        return tokenThreadLocal.get();
    }

    public static void remove() {
        tokenThreadLocal.remove();
        userUidThreadLocal.remove();
        userSessionContextThreadLocal.remove();
    }

    private static void setValue(UserSessionContext context) {
        if (context == null) {
            return;
        }

        Map<String, UserSessionContext> map = userSessionContextThreadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            userSessionContextThreadLocal.set(map);
        }

        map.put(context.getUid(), context);
    }

    private static UserSessionContext getContextFromLocal(String uid) {
        Map<String, UserSessionContext> map = userSessionContextThreadLocal.get();
        if (map == null) {
            return null;
        }
        return map.get(uid);
    }

    public static UserSessionContext getUser(String uid) {
        UserSessionContext userSessionContext = getContextFromLocal(uid);
        if (userSessionContext != null) {
            return userSessionContext;
        }

        HashMap userVo = cacheManager.hget("user:info:" + uid, "userVo", HashMap.class);
        if (userVo == null) {
            log.warn(">> 从redis {}  获取不到用户信息, 调用user服务获取……", "user:info:" + uid);
            UserSessionContext fromUserService = getFromUserService(uid);
            setValue(fromUserService);
            return fromUserService;
        }

        UserSessionContext context = new UserSessionContext();
        context.setId((Integer) userVo.get("id"));
        context.setUid(userVo.get("uid").toString());
        context.setUsername(userVo.get("username").toString());
        context.setAvatar(StringUtils.isBlank(String.valueOf(userVo.get("thumbnail")))
                ? DEFAULT_AVATAR : userVo.get("thumbnail").toString());
        context.setSex(Integer.valueOf(userVo.get("sex").toString()));
        context.setChickenId(userVo.get("chickenId").toString());
        setValue(context);
        return context;
    }

    private static UserSessionContext getFromUserService(String uid) {
        RestTemplate restTemplate = (RestTemplate) CommonApplicationContext.getContext().getBean("restTemplate");
        String host = "http://esportingplus-user-service";
        String url = host + "/userinfo/user/get/uid?uid=" + uid;
        ResponseEntity<ResponsePacket> response = restTemplate.getForEntity(url, ResponsePacket.class);
        if (response.getStatusCodeValue() != HttpStatus.OK.value()) {
            log.error(">> 从user服务获取UserSessionContext失败");
            return null;
        }

        ResponsePacket<HashMap> body = response.getBody();
        if (!body.responseSuccess()) {
            log.error(">> 从user服务获取UserSessionContext失败！" + body.getMsg());
            return null;
        }

        HashMap data = body.getData();
        if (data == null || data.isEmpty()) {
            log.error(">> 从user服务获取UserSessionContext为null");
            return null;
        } else {
            log.debug(">> 从user服务获取{}的用户信息{}", uid, data);
            UserSessionContext context = new UserSessionContext();
            context.setId((Integer) data.get("id"));
            context.setUid(data.get("uid").toString());
            context.setUsername(data.get("username").toString());
            context.setAvatar(StringUtils.isBlank(String.valueOf(data.get("thumbnail")))
                    ? DEFAULT_AVATAR : data.get("thumbnail").toString());
            context.setSex(Integer.valueOf(data.get("sex").toString()));
            context.setChickenId(data.get("chicken_id").toString());
            log.debug(">> 根据uid -> {}取得user信息 -> {}", uid, context);
            return context;
        }
    }


    /**
     * 从上下文中获取当前登录的用户信息
     *
     * @return
     */
    public static UserSessionContext getUser() {
        return getUser(getUserUid());
    }

    public static String getUserUid() {
        String uid = userUidThreadLocal.get();
        if (StringUtils.isBlank(uid)) {
            String token = tokenThreadLocal.get();
            if (StringUtils.isBlank(token)) {
                log.error(">> 没有token参数,不能通过token来获取用户信息");
                throw new IllegalArgumentException("token 参数错误");
            }
            uid = TokenParseUtils.parseUid(token);
            if (StringUtils.isBlank(uid)) {
                throw new IllegalArgumentException("token 参数错误,找不到对应的user信息");
            }
            userUidThreadLocal.set(uid);
        }
        return uid;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

