package com.panda.esportingplus.common.tools;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *@Description: jdk8 日期工具类
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class DateUtil {

    public static final ZoneId chinaZone = ZoneId.systemDefault();
    public static final String SIMPLE_FORMATTER = "yyyyMMddHHmmss";
    public static final String SIMPLE_FORMATTER_NONE_SS = "yyyyMMddHHmm";
    public static final String SIMPLE_FORMATTER_DAY = "yyyyMMdd";
    public static final String FORMATTER = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期对象转换为字符串
     *
     * @param localDate 日期对象
     * @return 日期字符串 yyyy-mm-dd
     */
    private static final DateTimeFormatter ymdformat = DateTimeFormatter.ofPattern("y-M-d");
    private static final DateTimeFormatter ymdhmformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * 获取当天日期(YYYY-MM-DD格式)
     */
    public static LocalDate now() {
        return LocalDate.now();
    }

    /**
     * 按指定格式获取当天日期
     */
    public static String nowDateTime(String formatter) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * 按指定格式获取当天日期
     */
    public static String dateFormatter(Date date, String formatter) {
        return dateToLocalDate(date).format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * 获取当天最大时间
     * @return
     */
    public static LocalDateTime nowMax() {
        return LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取当天最小时间
     * @return
     */
    public static LocalDateTime nowMin() {
        return LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
    }

    /**
     * 按指定格式获取当天日期
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), chinaZone);
    }

    /**
     * 按指定格式获取当天日期
     */
    public static LocalDate dateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), chinaZone).toLocalDate();
    }

    /**
     * 日期时间对象转换为日期对象
     *
     * @param localDateTime 日期时间对象
     * @return 日期对象
     */
    public static LocalDate dateTime2Date(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();

    }

    /**
     * 日期对象转换为日期对象
     *
     * @param localDate 日期对象
     * @return 日期时间对象
     */
    public static LocalDateTime date2DateTIme(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.NOON);
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate 字符串日期
     * @return 日期对象 yyyy-mm-dd
     */
    public static LocalDate str2LocalDate(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
    }

    /**
     * 字符串转换为日期
     *
     * @param strDate 字符串日期
     * @return 日期对象 yyyy-mm-dd
     */
    public static LocalDateTime str2LocalDateTime(String strDate, DateTimeFormatter formatter) {
        return LocalDateTime.parse(strDate, formatter);
    }

    /**
     * yyyyMMddHHmmss时间转LocalDateTime
     *
     * @param strDate
     * @return
     */
    public static LocalDateTime str2LocalDateTime(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SIMPLE_FORMATTER);
        return LocalDateTime.parse(strDate, formatter);
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(chinaZone).toInstant());
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date str2Date(String strDate, String formatter) {
        return localDateTime2Date(str2LocalDateTime(strDate, DateTimeFormatter.ofPattern(formatter)));
    }

    /**
     * yyyyMMddHHmmss时间转Date
     *
     * @param yyyyMMddHHmmssTime
     * @return
     */
    public static Date str2dateWithYMDHMS(String yyyyMMddHHmmssTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SIMPLE_FORMATTER);
        LocalDateTime ldt = LocalDateTime.parse(yyyyMMddHHmmssTime, dtf);
        return localDateTime2Date(ldt);
    }

    /***
     * 将指定格式的时间字符串转为Date
     * @param time
     * @param formatter
     * @return
     */
    public static Date str2date(String time, String formatter) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatter);
        LocalDateTime ldt = LocalDateTime.parse(time, dtf);
        return localDateTime2Date(ldt);
    }

    /**
     * 日期对象转换为字符串
     *
     * @param localDate 日期对象
     * @return 日期字符串 yyyy-mm-dd
     */
    public static String date2Str(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * 格式化日期与时间
     */
    public static String date2YmdStr(Date date) {
        LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ymdformat.format(time);
    }

    public static String date2YmdhmStr(Date date) {
        LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ymdhmformat.format(time);
    }

    /**
     * 日期时间对象转换为字符串
     *
     * @param localDateTime     日期时间对象
     * @param dateTimeFormatter 格式化字符串
     * @return 日期字符串
     */
    public static String dateTime2Str(LocalDateTime localDateTime, String dateTimeFormatter) {
        return localDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormatter));
    }

    /**
     * 日期时间转字符串函数 返回ISO标准的日期字符串
     *
     * @param localDateTime 日期时间对象
     * @return 日期字符串
     */
    public static String dateTime2Str(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * 获取东八时间戳,秒级
     */
    public static long getTimeStrampMiniseconds() {
        //获取秒数
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /***
     * 获取时间戳,毫秒级
     * @return
     */
    public static String getTimeStamp() {
        Date d = new Date();
        //getTime()得到的是微秒， 需要换算成秒
        long timeStamp = d.getTime() / 1000;
        return String.valueOf(timeStamp);
    }

    /**
     * 获取东八时间戳,毫秒级
     */
    public static long getTimeStrampSeconds() {
        //获取秒数
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * date 转string： yyyy-MM-dd HH:mm:ss
     */
    public static String fromDate2Str(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 计算两个时间之间相差的秒数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     */
    public static long secondsBetween(LocalDateTime date1, LocalDateTime date2) {
        Duration duration = Duration.between(date1, date2);
        return duration.getSeconds();
    }

    /**
     * 计算两个时间之间相差的秒数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     */
    public static long secondsBetween(Date date1, Date date2) {
        Instant instantDateTime1 = date1.toInstant();
        Instant instantDateTime2 = date2.toInstant();
        Duration duration = Duration.between(instantDateTime1, instantDateTime2);
        return duration.getSeconds();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int daysBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getDays();
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int monthsBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getMonths();
    }

    /**
     * 计算两个日期之间相差的年数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int yearsBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getYears();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int daysBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(chinaZone).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(chinaZone).toLocalDate();
        instantDate1.atZone(chinaZone);
        Period period = Period.between(localDate1, localDate2);
        return period.getDays();
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int monthsBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(chinaZone).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(chinaZone).toLocalDate();
        instantDate1.atZone(chinaZone);
        Period period = Period.between(localDate1, localDate2);
        return period.getMonths();
    }

    /**
     * 计算两个日期之间相差的年数
     *
     * @param date1 起始日期
     * @param date2 结束日期
     */
    public static int yearsBetween(Date date1, Date date2) {
        Instant instantDate1 = date1.toInstant();
        Instant instantDate2 = date2.toInstant();
        LocalDate localDate1 = instantDate1.atZone(chinaZone).toLocalDate();
        LocalDate localDate2 = instantDate2.atZone(chinaZone).toLocalDate();
        instantDate1.atZone(chinaZone);
        Period period = Period.between(localDate1, localDate2);
        return period.getYears();
    }

    /**
     * 获取指定日期对象当前月的起始日
     *
     * @param localDate 指定日期
     */
    public static int getFirstDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return result.getDayOfMonth();

    }

    /**
     * 获取指定日期对象的当前月的结束日
     *
     * @param localDate 指定日期
     */
    public static int getLastDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return result.getDayOfMonth();
    }

    /**
     * 获取指定日期对象本月的某周某天的日期
     *
     * @param localDate  日期对象
     * @param weekNumber 周
     * @param dayNumber  日
     */
    public static LocalDate getLocalDateBydayAndWeek(LocalDate localDate, int weekNumber, int dayNumber) {
        return localDate.with(TemporalAdjusters.dayOfWeekInMonth(weekNumber, DayOfWeek.of(dayNumber)));
    }

    public static String getHour(LocalDateTime localDate) {
        return dateTime2Str(localDate, SIMPLE_FORMATTER).substring(8, 10);
    }

    public static String getDay(LocalDateTime localDate) {
        return dateTime2Str(localDate, SIMPLE_FORMATTER).substring(6, 8);
    }

    /**
     * 获取指定秒数后的时间戳
     *
     * @param after
     * @return
     */
    public static long getDateTimeAfterSeconds(LocalDateTime dateTime, long after) {
        return dateTime.plusSeconds(after).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取指定秒数后的时间
     *
     * @param after
     * @return
     */
    public static LocalDateTime getLocalDateTimeAfterSeconds(LocalDateTime dateTime, long after) {
        return dateTime.plusSeconds(after);
    }

    /**
     * 获取某天的23点59
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取今天前后天数据
     * 如：明天 day 传 1
     *    昨天 day 传 -1
     * @param day  天数
     * @return
     */
    public static Date getCalendarDate(Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(calendar.DATE,day);
        return calendar.getTime();
    }

    /**
     * 获取某天的0点
     *
     * @param date
     * @return
     */
    public static Date getAM0000(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 取得当前日期是第几周（在日程中的）
     *
     * @param date
     * @return
     */
    public static int[] getWeekOfYearWithCPlan(Date date) {
        int[] result = new int[2];
        Calendar c =  new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);  	//星期第1天是星期一
        c.setMinimalDaysInFirstWeek(6);			//年和月算周，要4天及以上才算是1周

        c.setTime (date);

        int weeknum = c.get(Calendar.WEEK_OF_YEAR);
        int vyear = c.get(Calendar.YEAR);
        int vmonth = c.get(Calendar.MONTH)+1;

        //当1月分首周不满一周时算为去年的周数，故要年号减1
        if(vmonth == 1 && weeknum>6){
            vyear--;
        }

        //当最后一周不满一周算为下一年的周数，故要年号加1
        if(vmonth == 12 && weeknum==1){
            vyear++;
        }
        result[0] = vyear;
        result[1] = weeknum;
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(DateUtil.fromDate2Str(new Date(1548404064262L)));
        System.out.println(DateUtil.getDateTimeAfterSeconds(LocalDateTime.now(), 5 * 60));

        System.out.println(DateUtil
                .str2Date("20180814140659", DateUtil.SIMPLE_FORMATTER));

        System.out.println(DateUtil.nowDateTime(DateUtil.SIMPLE_FORMATTER));

        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(secondsBetween(now, now2));

        System.out.println(Duration.between(now, now2).toMillis() / 1000);

        System.out.println(getDateTimeAfterSeconds(LocalDateTime.now(), 3600));
        LocalDateTime date1 = dateToLocalDateTime(new Date());
        Thread.sleep(3000);
        System.out.println(secondsBetween(date1, LocalDateTime.now()));
    }
}