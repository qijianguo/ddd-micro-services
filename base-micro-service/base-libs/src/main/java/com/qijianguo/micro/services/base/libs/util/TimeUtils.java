package com.qijianguo.micro.services.base.libs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author qijianguo
 */
public class TimeUtils {

    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYHHMMHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_HH_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_HH_MM_HH_MM_00 = "yyyy-MM-dd HH:mm:00";
    public static final String YYYY_HH_MM_HH_00_00 = "yyyy-MM-dd HH:00:00";
    public static final String YYYY_HH_MM_00_00_00 = "yyyy-MM-dd 00:00:00";

    public static final String YYYY_HH_MM = "yyyy-MM-dd";
    public static final String YYYY_HH = "yyyy-MM";
    public static final String YYYYHHMM = "yyyyMMdd";
    public static final String YYYYHH = "yyyyMM";


    public static LocalDateTime date2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date string2Date(String text, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(text);
    }

    public static Date dataFormat(Date date, String pattern) throws ParseException {
        String time = new SimpleDateFormat(pattern).format(date);
        return string2Date(time, pattern);
    }

    public static String date2String(Object o) {
        return new SimpleDateFormat(YYYY_HH_MM_HH_MM_SS).format(o);
    }

    public static String date2String(Object o, String pattern) {
        return new SimpleDateFormat(pattern).format(o);
    }

    public static long date2long(Date date) {
        LocalDateTime localDateTime = date2LocalDateTime(date);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public static String localDateTime2String(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_HH_MM_HH_MM_SS));
    }

    public static String localDateTime2String(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDate2String(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(YYYYHHMM));
    }

    public static String localDate2String(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String lastDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date); //本月的第一天
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return localDate2String(lastDay);
    }

    public static String nextMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        ; //本月的第一天
        return localDate2String(localDate.plusMonths(1));
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private static final long MILL_OF_DAY = 86400000;// 1000 * 24 * 60 * 60;
    private static final long MILL_OF_HOUR = 3600000; // 1000 * 60 * 60;
    private static final long MILL_OF_MINS = 60000; // 1000 * 60;
    private static final long MILL_OF_SECS = 1000;

    /**
     * 获得两个时间的时间差
     * @param startTime
     * @param endDate
     * @return
     */
    public static String differFormat(Date startTime, Date endDate) {

        long differ = endDate.getTime() - startTime.getTime();
        return differString(differ);
    }

    /**
     * 相差秒数
     * @param startTime
     * @param endDate
     * @return 秒
     */
    public static long differSec(Date startTime, Date endDate) {
        long ns = 1000;
        long differ = endDate.getTime() - startTime.getTime();
        return differ / ns;
    }

    /**
     * 相差分钟数
     * @param startTime
     * @param endDate
     * @return
     */
    public static long differMin(Date startTime, Date endDate) {
        long differ = endDate.getTime() - startTime.getTime();
        long sec = differ / MILL_OF_MINS;
        return sec;
    }

    /**
     * 相差小时数
     * @param startTime
     * @param endDate
     * @return
     */
    public static long differHour(Date startTime, Date endDate) {
        long differ = endDate.getTime() - startTime.getTime();
        long sec = differ / MILL_OF_HOUR;
        return sec;
    }

    /**
     * 相差天数
     * @param startTime
     * @param endDate
     * @return
     */
    public static long differDay(Date startTime, Date endDate) {
        long differ = endDate.getTime() - startTime.getTime();
        long sec = differ / MILL_OF_DAY;
        return sec;
    }

    /**
     * 时间戳转日期
     * @param timestemp
     * @return
     */
    public static Date long2Date(long timestemp) {
        return new Date(timestemp / 1000 * 1000);
    }

    /**
     * 时间戳转日期字符串
     * @param timestemp
     * @return
     */
    public static String long2String(long timestemp) {
        return date2String(new Date(timestemp / 1000 * 1000));
    }

    /**
     * x天x时x分
     *
     * @param secdiffer
     * @return
     */
    public static String sec2String(long secdiffer) {
        secdiffer = secdiffer * 1000;
        long day = secdiffer / MILL_OF_DAY;
        long hour = secdiffer % MILL_OF_DAY / MILL_OF_HOUR;
        long min = secdiffer % MILL_OF_DAY % MILL_OF_HOUR / MILL_OF_MINS;
        long sec = secdiffer % MILL_OF_DAY % MILL_OF_HOUR % MILL_OF_MINS / MILL_OF_SECS;
        StringBuilder time = new StringBuilder();
        if (day > 0) {
            time.append(day + "天");
        }
        if (hour > 0) {
            time.append(hour + "时");
        }
        if (min > 0) {
            time.append(min + "分");
        }
        if (sec > 0) {
            time.append(sec + "秒");
        }
        if (time.length() == 0) {
            time.append("-");
        }
        return time.toString();
    }

    public static String sec2HourMin(long secdiffer) {
        secdiffer = secdiffer * 1000;
        long hour = secdiffer / MILL_OF_HOUR;
        long min = secdiffer % MILL_OF_HOUR / MILL_OF_MINS;
        StringBuilder time = new StringBuilder();
        if (hour > 0) {
            time.append(hour + "小时");
        }
        if (min > 0) {
            time.append(min + "分钟");
        }
        if (time.length() == 0) {
            if (secdiffer > 0) {
                time.append("1分钟");
            } else {
                time.append("0分钟");
            }
        }
        return time.toString();
    }

    /**
     *
     * @param secdiffer
     * @return
     */
    public static String sec2Min(long secdiffer) {
        secdiffer = secdiffer * 1000;
        // 计算差多少分钟
        long min = secdiffer / MILL_OF_MINS;
        if (min == 0L && secdiffer > 0) {
            return "1";
        } else {
            return String.valueOf(min);
        }

    }

    public static List<LocalDate> latestWeek() {
        List<LocalDate> calendars = new ArrayList<>();
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(7 - now.getDayOfWeek().getValue());
        for (int i = 13; i >= 0; i--) {
            calendars.add(localDate.minusDays(i));
        }
        return calendars;
    }

    public static boolean nowBetweenTime(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            long now = System.currentTimeMillis();
            return startTime.getTime() < now && endTime.getTime() > now;
        }
        return false;
    }

    public static String differ(Date startTime, Date endTime) {
        // 获得两个时间的毫秒时间差异
        long differ = endTime.getTime() - startTime.getTime();
        StringBuilder sb = new StringBuilder();
        return differString(differ);
    }

    private static String differString(long differ) {
        long day = differ / MILL_OF_DAY;
        long hour = differ % MILL_OF_DAY / MILL_OF_HOUR;
        long min = differ % MILL_OF_DAY % MILL_OF_HOUR / MILL_OF_MINS;
        long sec = differ % MILL_OF_DAY % MILL_OF_HOUR % MILL_OF_MINS / MILL_OF_SECS;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    public static String mill2HourMin(long milldiffer) {
        // 计算差多少小时
        long hour = milldiffer / MILL_OF_HOUR;
        // 计算差多少分钟
        long min = milldiffer % MILL_OF_HOUR / MILL_OF_MINS;
        // 计算差多少秒//输出结果
        long sec = milldiffer % MILL_OF_HOUR % MILL_OF_MINS / MILL_OF_SECS;
        if (hour > 0) {
            return hour + "Hour";
        } else {
            if (min > 30) {
                return "30Min";
            } else if (min > 15) {
                return "15Min";
            } else if (min > 5) {
                return "5Min";
            } else {
                return "now";
            }
        }
    }

    /**
     * 指定{@param date} 前
     * @param date
     * @param time
     * @param timeUnit
     * @return
     */
    public static Date before(Date date, long time, TimeUnit timeUnit) {
        long value = milliseconds(time, timeUnit);
        return new Date(date.getTime() - value);
    }
    /**
     * 指定{@param date} 前
     * @param date
     * @param time
     * @param timeUnit
     * @return
     */
    public static Date after(Date date, long time, TimeUnit timeUnit) {
        long value = milliseconds(time, timeUnit);
        return new Date(date.getTime() + value);
    }

    public static long milliseconds(long time, TimeUnit timeUnit) {
        long value;
        switch (timeUnit) {
            case DAYS:
                value = time * 86400000;
                break;
            case HOURS:
                value = time * 3600000;
                break;
            case MINUTES:
                value = time * 60000;
                break;
            case SECONDS:
                value = time * 60;
                break;
            case MILLISECONDS:
                value = time;
                break;
            default:
                throw new IllegalArgumentException("TimeUnit type not support! TimeUnit is : " + timeUnit);
        }
        return value;
    }

}
