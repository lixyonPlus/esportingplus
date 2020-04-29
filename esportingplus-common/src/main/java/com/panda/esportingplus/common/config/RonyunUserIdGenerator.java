package com.panda.esportingplus.common.config;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @author shusong.liang
 **/
@Configuration
public class RonyunUserIdGenerator {

    @Value("${message.ronyun.imSecretKey}")
    private String imSecretKey;

    public List<String> encodeIMUser(List<String> uids) {
        if (CollectionUtils.isEmpty(uids)){
            return new ArrayList<>();
        }

        return uids.stream().map(this::encode).collect(Collectors.toList());
    }

    public String encodeIMUser(String uid) {
        if (StringUtils.isEmpty(uid)){
            return null;
        }

        return encode(uid);
    }


    //将userid编码成对应的IM uid   对应关系： userid - uid + '_' + md5(User'id + SECRET_KEY)[:6]
    private String encode(String uid) {
        String re_md5 = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest
                    .digest((uid + imSecretKey).getBytes(Charset.forName("UTF-8")));
            StringBuffer buf = new StringBuffer("");
            int i;
            for (int offset = 0; offset < bytes.length; offset++) {
                i = bytes[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (Exception e) {
//            logger.error("RongYun Error:{}, encodeIMUserId failed, uid ={}", e.getContent(), uid);
        }
        return uid + "_" + re_md5.substring(0, 6);
    }

    public String getImSecretKey() {
        return imSecretKey;
    }

    public void setImSecretKey(String imSecretKey) {
        this.imSecretKey = imSecretKey;
    }

    public static void main(String[] args) {
        RonyunUserIdGenerator ronyunUserIdGenerator = new RonyunUserIdGenerator();
        Stream.of("5anmv000", "b6217755", "Base0000")
        .forEach(d -> System.out
                .println(d + "的融云uid -> " + ronyunUserIdGenerator.encodeIMUser(d)));

    }
}
