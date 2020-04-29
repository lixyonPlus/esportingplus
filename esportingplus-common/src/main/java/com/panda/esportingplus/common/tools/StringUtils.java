package com.panda.esportingplus.common.tools;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static Pattern pattern = Pattern.compile("\\{(\\d)\\}");
    public static final String EMPTY = "";

    /**
     * 通配符的替换
     */
    public static String fillStringByArgs(String str, String[] arr) {
        Matcher m = pattern.matcher(str);
        while (m.find()) {
            str = str.replace(m.group(), arr[Integer.parseInt(m.group(1))]);
        }
        return str;
    }

    public static String formatTemplate(String template, String... args) {
        return MessageFormat.format(template,args);
    }

    public static String trimAll(CharSequence s) {
        if (s == null || s.length() == 0) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0, l = s.length(); i < l; i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static int getBytesLength(String str) {
        return str.getBytes(StandardCharsets.UTF_8).length;
    }

    public static boolean containsAnyIgnoreCase(CharSequence str, CharSequence... testStrs) {
        return null != getContainsStrIgnoreCase(str, testStrs);
    }

    public static String getContainsStrIgnoreCase(CharSequence str, CharSequence... testStrs) {
        if (!isEmpty(str) && !ObjectTools.isEmpty(testStrs)) {
            CharSequence[] arr$ = testStrs;
            int len$ = testStrs.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                CharSequence testStr = arr$[i$];
                if (containsIgnoreCase(str, testStr)) {
                    return testStr.toString();
                }
            }

            return null;
        } else {
            return null;
        }
    }

}
