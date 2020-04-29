package com.panda.esportingplus.common;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * jdk8日期API常用方法
 */
public class JDK8DateAPI {

    public static void main(String[] args) throws ParseException {
        DateAPIUtilities();
        localDateExample();
        DateParseFormatExample();
        localDateTimeExample();
        instantExample();

    }

    /**
     * java.time包
     * 这是新的Java日期/时间API的基础包，所有的主要基础类都是这个包的一部分，如：LocalDate, LocalTime, LocalDateTime, Instant,
     * Period, Duration等等。所有这些类都是不可变的和线程安全的，在绝大多数情况下，这些类能够有效地处理一些公共的需求。
     */
    private static void localDateExample() {
        // 获取当前时期
        LocalDate today = LocalDate.now();
        System.out.println(today);

        // 通过时区ID获取指定时区的日期。相关的ID可以到javaDoc去找时区id
        LocalDate todayKolkata = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(todayKolkata);

        // 通过参数创建日期。
        LocalDate firstDay_2016 = LocalDate.of(2016, Month.JANUARY, 1);
        LocalDate firstDay_2_2016 = LocalDate.of(2016, 2, 1);
        System.out.println("Specific Date=" + firstDay_2016);
        System.out.println("Specific Date=" + firstDay_2_2016);

        // 通过时间戳(Day为单位)创建离epochDay指定日期的日期对象
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= " + dateFromBase);

        // 根据指定年份创建离指定年份日期的日期对象
        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 101);
        System.out.println("100th day of 2014=" + hundredDay2014);

        System.out.println(today.getDayOfWeek().getValue());
        System.out.println(today.getDayOfMonth());
        System.out.println(today.getDayOfYear());

        // 格式化日期对象
        LocalDate date = LocalDate.parse("2016-10-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);



        LocalDate localDate = LocalDate.parse("2016-10-12", DateTimeFormatter.ISO_DATE);
        LocalDateTime localDateTime = localDate.atTime(LocalTime.MIN);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(Date.from(instant));

    }

    /**
     * 测试LocalTIme
     * java.time.LocalTime：LocalTime是一个不可变的类，它的实例代表一个符合人类可读格式的时间，默认格式是hh:mm:ss.zzz。
     * 像LocalDate一样，该类也提供了时区支持，同时也可以传入小时、分钟和秒等输入参数创建实例，我们来看一个简单的程序，演示该类的使用方法。
     */
    private static void localTimeExample() {

        // 获取当前时间
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);


        // 通过时区ID获取指定时区的日期。相关的ID可以到javaDoc去找时区id
        LocalTime todayKolkata = LocalTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(todayKolkata);

        // 通过参数创建日期。
        LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day=" + specificTime);

        // 通过时间戳(Day为单位)创建离epochDay指定日期的日期对象
        LocalTime dateFromBase = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= " + dateFromBase);

    }


    /**
     * 测试localDateTIme
     * <p>
     * java.time.LocalDateTime：LocalDateTime是一个不可变的日期-时间对象，它表示一组日期-时间，
     * 默认格式是yyyy-MM-ddTHH-mm-ss.zzz。
     * 它提供了一个工厂方法，接收LocalDate和LocalTime输入参数，创建LocalDateTime实例。我们来看一个简单的例子。
     */
    private static void localDateTimeExample() {

        //Current Date
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Current DateTime=" + today);

        //根据date与time创建联合时间
        today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("Current DateTime=" + today);

        //根据参数创建localDateTime
        LocalDateTime specificDate = LocalDateTime.of(2014, 10, 1, 10, 10, 30);
        System.out.println("Specific Date=" + specificDate);

        //根据时区创建localDateTime
        LocalDateTime todayKolkata = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println("Current Date in IST=" + todayKolkata);

        //根据参数创建时间戳
        LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
        System.out.println("10000th second time from 01/01/1970= " + dateFromBase);

        LocalDateTime dateTime = LocalDateTime.parse("2016-10-12 10:55:03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime);

        Date dt = new Date();
        Instant instantDate = dt.toInstant();
        LocalDateTime localDate1 = instantDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDate1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //LocalDateTime2LocalDate
        System.out.println(today.toLocalDate());

    }


    /**
     * java.time.Instant：Instant类是用在机器可读的时间格式上的，它以Unix时间戳的形式存储日期时间，我们来看一个简单的程序。
     */
    private static void instantExample() {
        //当前时间戳
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = " + timestamp);

        //Instant from timestamp
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = " + specificTime);

        //Duration example
        Duration thirtyDay = Duration.ofMinutes(2);
        System.out.println(thirtyDay.getSeconds());

        //时间戳对象转换为LocalDateTime
        LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Instant.now().minusMillis(timestamp.toEpochMilli()).getEpochSecond());


    }


    /**
     * 日期工具测试
     */
    private static void DateAPIUtilities() {
        LocalDate today = LocalDate.now();

        //判断是否是闰年
        System.out.println("Year " + today.getYear() + " is Leap Year? " + today.isLeapYear());

        //日期的比较判断
        System.out.println("Today is before 01/01/2015? " + today.isBefore(LocalDate.of(2015, 1, 1)));

        //通过localDate创建LocalDateTIme对象
        System.out.println("Current Time=" + today.atTime(LocalTime.now()));

        //日期运算
        System.out.println("10 days after today will be " + today.plusDays(10));
        System.out.println("3 weeks after today will be " + today.plusWeeks(3));
        System.out.println("20 months after today will be " + today.plusMonths(20));

        System.out.println("10 days before today will be " + today.minusDays(10));
        System.out.println("3 weeks before today will be " + today.minusWeeks(3));
        System.out.println("20 months before today will be " + today.minusMonths(20));

        //获取当前指定时间的指定特殊日期。
        System.out.println("First date of this month= " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= " + lastDayOfYear);


        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= " + period);
        // 还剩下多少月
        System.out.println("Months remaining in the year= " + period.getMonths());

        //根据当前日期,获取本月的第一周的某日的日期
        System.out.println(today.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));
        // 根据当前日期,获取指定日期月份的某一周的某一日的日期。
        System.out.println(today.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.FRIDAY)));
        // 根据指定日期,获取下一个周的日期。
        System.out.println(today.with(TemporalAdjusters.next(DayOfWeek.of(1))));

    }


    /**
     * 解析日期例子
     */
    private static void DateParseFormatExample() {
        //Format examples
        LocalDate date = LocalDate.now();
        //default format
        System.out.println("Default format of LocalDate=" + date);

        //17::十月::2016
        System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
        //20161017
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

        LocalDateTime dateTime = LocalDateTime.now();
        //2016-10-17T01:08:09.850
        System.out.println("Default format of LocalDateTime=" + dateTime);
        //2016-35-18 00:35:13
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss")));
        //20161017
        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));
        //2016-10-18T00:33:03.447
        System.out.println(dateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        //时间戳格式化
        Instant timestamp = Instant.now();
        //默认被重写过的toString方法将会返回2016-10-16T17:08:09.851Z
        System.out.println("Default format of Instant=" + timestamp);

        //Parse examples TODO
        LocalDateTime dt = LocalDateTime.parse("2016-10-18 11:12:13",
                DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));
        System.out.println("Default format after parsing = " + dt);
    }

    /**
     * 新旧日期之间的转换
     */
    private static void dateAPILegacySupport() {

        //将Date对象转换为时间戳,再转换成新日期对象然后格式化后打印
        Instant timeStap = new Date().toInstant();
        LocalDateTime date = LocalDateTime.ofInstant(timeStap, ZoneId.of("Asia/Kolkata"));
        System.out.println(date.format(DateTimeFormatter.ISO_DATE_TIME));

        Instant time = Calendar.getInstance().toInstant();
        System.out.println(time);

        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        System.out.println(defaultZone);

        Date dt = Date.from(Instant.now());
        System.out.println(dt);
    }


}