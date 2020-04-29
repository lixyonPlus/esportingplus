package com.panda.esportingplus.common.tools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * MD5Util
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class MD5Util {
    static final String keyValue = "URKEJ4YYFCIEFLHC";

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //将字节数组转为16进制字符串
    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexDigits[(b[i] & 0xf0) >>> 4]);
            sb.append(hexDigits[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String generateKey(Map<String, String> values) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.toString().getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException var4) {
            throw new IllegalStateException(
                    "MD5 algorithm not available.  Fatal (should be in the JDK).", var4);
        } catch (UnsupportedEncodingException var5) {
            throw new IllegalStateException(
                    "UTF-8 encoding not available.  Fatal (should be in the JDK).", var5);
        }
    }

    public static String knightsMD5(Map<String, String> values) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //排序 拼接
            List<String> keys = new ArrayList<String>(values.keySet());
            Collections.sort(keys);
            int index = 0;
            StringBuilder MD5Str = new StringBuilder();
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = values.get(key);
                if (StringUtils.isNotBlank(key)) {
                    MD5Str.append((index == 0 ? "" : "&") + key + "=" + URLEncoder.encode(value, "utf-8"));
                    index++;
                }
            }
            byte[] btInput = MD5Str.toString().getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // 使用指定的字节更新摘要
            mdInst.update(btInput);

            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String knightsMD5(String key, String value) {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("key", keyValue);
        map.put(key, value);
        return knightsMD5(map);
    }

    public static void main(String[] args) {
        System.out.println(knightsMD5("userId", "123"));
    }



}