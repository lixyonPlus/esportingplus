package com.panda.esportingplus.user.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 根据生日计算年龄
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay){
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            }else{
                age--;
            }
        }
        return age;
    }

    /**
     * 根据月份和天数获取星座
     * @param month
     * @param date
     * @return
     */
    public static String getConstellationByMonthAndDay(int month, int date){
        int[] days = {21, 20, 21, 21, 22, 22, 23, 24, 24, 24, 23, 22};
        String[] constellations = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
                "天蝎座", "射手座", "摩羯座"};
        return date < days[month - 1] ? constellations[month - 1] : constellations[month];
    }
}
