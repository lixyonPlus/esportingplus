package com.panda.esportingplus.common.tools;

import java.math.BigDecimal;

public class NumberUtils {
    private final static String[] GROUP_UNITS = {"", "万", "亿", "兆"};
    private final static String[] BASE_UNITS = { "", "十", "百", "千" };
    private final static char[] SIMPLIFIED_NUMBERIC = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
    private final static char[] TRADITIONAL_NUMBERIC = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };

    /**
     * 转为中文数字
     * @param number 数值
     * @param isTraditional 是否转为繁体
     * @return
     */
    public static String toChinese(long number, boolean isTraditional){
        char[] numberic = isTraditional ? TRADITIONAL_NUMBERIC : SIMPLIFIED_NUMBERIC;

        if(number==0){//判断是否为0
            return String.valueOf(numberic[0]);
        }

        String str = String.valueOf(number);
        char[] numbers = str.toCharArray();
        int len = str.length();
        //统计连续出现的0
        int zeroCount = 0;

        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i<len ;i++){
            int value = Integer.parseInt(String.valueOf(numbers[i]));
            int index = len - i - 1;
            //在分组中的下标
            int _index  = index % 4;
            if(value == 0){
                zeroCount++;
                if((_index == 0 && zeroCount < 4 ) || (index % 8 == 0 && zeroCount < 8)){
                    stringBuffer.append(getGroupUnit(index));
                    zeroCount = 0;
                }
            }
            else{
                if(zeroCount > 0){
                    stringBuffer.append(numberic[0]);
                }
                if(_index == 0){
                    stringBuffer.append(numberic[value] + BASE_UNITS[_index] + getGroupUnit(index));
                    zeroCount = 0;
                }
                else{
                    //调整以“一十”开头的不需要出现“一” 如：100000 读“十万”而不是“一十万”
                    if(_index == 1 && i==0 && value== 1){
                        stringBuffer.append(BASE_UNITS[_index]);
                    }
                    else{
                        stringBuffer.append(numberic[value] + BASE_UNITS[_index]);
                    }

                }
                zeroCount = 0;
            }

        }
        return stringBuffer.toString();
    }

    /**
     * 转为中文数字
     * @param number 数值
     * @return
     */
    public static String toChinese(long number){
        return toChinese(number, false);
    }

    /**
     * 转为中文数字
     * @param number 数值
     * @param isTraditional 是否转为繁体
     */
    public static String toChinese(int number, boolean isTraditional){
        return toChinese((long)number, isTraditional);
    }


    /**
     * 转为中文数字
     * @param number 数值
     */
    public static String toChinese(int number){
        return toChinese(number, false);
    }

    /**
     * 获取分组单位
     * @param index
     * @return
     */
    private static String getGroupUnit(int index){
        String groupUnit = GROUP_UNITS[(index / 4) % 2];
        if(index % 8 == 0 && index > 0){
            groupUnit += GROUP_UNITS[(index / 8) +1];
        }
        return groupUnit;
    }

    /**
     * 去除无用的0：01.50=1.5
     * @param number
     * @return 返回BigDecimal
     */
    public static BigDecimal cleanZero(double number){
        return BigDecimal.valueOf(number).stripTrailingZeros();
    }

    /**
     * 去除无用的0：01.50=1.5
     * @param number
     * @return 返回String
     */
    public static String cleanZero2Str(double number){
        return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
    }
}
