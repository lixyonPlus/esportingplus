package com.panda.esportingplus.user.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author shusong.liang
 * @Title: ConversionUtil
 * @Description: 进制转换工具类
 * @date 2020/03/27 19:38
 */
public class ConversionUtil {

    private static final String DIGITS = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final long HIX = 36;
    private static final String PADCHAR = "0";

    public static String encode(long num , int length) {

        StringBuilder sb = new StringBuilder();
        int remainder = 0;

        //将num对62取模，从DIGITS匹配出对应的字符
        while (true) {
            remainder = Long.valueOf(num % HIX).intValue();
            sb.append(DIGITS.charAt(remainder));
            num = num / HIX;
            if (num == 0) {
                break;
            }
        }

        //TODO 改为使用随机数
        String value = sb.reverse().toString();
        return StringUtils.rightPad(value, length, PADCHAR);
    }

    public static void main(String[] args) {
        Integer num = 96740;
        for (int i=0;i<100;i++) {
            System.out.println(num+" : " + encode(num++ ,8));
        }
    }
}
