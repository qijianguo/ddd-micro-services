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

/**
 * @author qijianguo
 */
public class TimeUtils {

    public static final String YYYY_HH_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYHHMMHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_HH_MM_00_00_00 = "yyyy-MM-dd 00:00:00";
    public static final String TIME_STAMP_PATTERN = "yyyyMMddHHmmssSSS";
    public static final String YYYY_HH_MM = "yyyy-MM-dd";
    public static final String YYYY_HH = "yyyy-MM";
    public static final String YYYYHHMM = "yyyyMMdd";
    public static final String YYYYHH = "yyyyMM";

    public static LocalDateTime convertDate2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertLocalDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertString2Date(String text, String pattern) throws ParseException {
        if ("1970-01-01 08:00:00".equals(text)) {
            text = "1970-01-01 08:00:01";
        }
        return new SimpleDateFormat(pattern).parse(text);
    }

    public static Date convertDate2Date(Date date, String pattern) throws ParseException {
        String time = new SimpleDateFormat(pattern).format(date);
        return convertString2Date(time, pattern);
    }

    public static String convertDate2DateString(Object o) {
        return new SimpleDateFormat(YYYY_HH_MM_HH_MM_SS).format(o);
    }

    public static String convertDate2String(Object o, String pattern) {
        return new SimpleDateFormat(pattern).format(o);
    }

    public static long convertDate2long(Date date) {
        LocalDateTime localDateTime = convertDate2LocalDateTime(date);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public static String convertLocalDateTime2String(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_HH_MM_HH_MM_SS));
    }

    public static String convertLocalDateTime2String(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String convertLocalDate2String(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(YYYYHHMM));
    }

    public static String convertLocalDate2String(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getLatestDayOfMonth(String date) {
        LocalDate localDate = LocalDate.parse(date); //本月的第一天
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return convertLocalDate2String(lastDay);
    }

    public static String getNextMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        ; //本月的第一天
        return convertLocalDate2String(localDate.plusMonths(1));
    }

    public static LocalDate convertDate2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String time = df.format(date);
        return time;
    }

    /**
     * 获取当前日期（yyyy-MM-dd）
     */
    public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_HH_MM);
        Date date = new Date();
        String currentDate = format.format(date);
        return currentDate;
    }

    public static String minusMinutes(Integer minute, String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(YYYY_HH_MM_HH_MM_SS);
        LocalDateTime ldt = LocalDateTime.parse(time, df).minusMinutes(minute);
        return df.format(ldt);
    }

    public static long getDateDayPoor(Date startTime, Date endDate) {
        long nd = 1000 * 24 * 60 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        return day;
    }

    private static final long nd = 1000 * 24 * 60 * 60;
    private static final long nh = 1000 * 60 * 60;
    private static final long nm = 1000 * 60;
    private static final long ns = 1000;

    public static String getDatePoor(Date startTime, Date endDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    public static long getDifferSec(Date startTime, Date endDate) {
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        long sec = diff / ns;
        return sec;
    }

    public static long getDifferMin(Date startTime, Date endDate) {
        long diff = endDate.getTime() - startTime.getTime();
        long sec = diff / nm;
        return sec;
    }

    public static long getDifferHour(Date startTime, Date endDate) {
        long diff = endDate.getTime() - startTime.getTime();
        long sec = diff / nh;
        return sec;
    }

    public static long getDifferDay(Date startTime, Date endDate) {
        long diff = endDate.getTime() - startTime.getTime();
        long sec = diff / nd;
        return sec;
    }

    public static Date long2DateStr(long timestemp) {
        return new Date(timestemp / 1000 * 1000);
    }

    public static String long2Date(long timestemp) {
        return convertDate2DateString(new Date(timestemp / 1000 * 1000));
    }

    /**
     * x天x时x分
     *
     * @param secDiff
     * @return
     */
    public static String sec2String(long secDiff) {
        secDiff = secDiff * 1000;
        // 计算差多少天
        long day = secDiff / nd;
        // 计算差多少小时
        long hour = secDiff % nd / nh;
        // 计算差多少分钟
        long min = secDiff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = secDiff % nd % nh % nm / ns;
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

    public static String sec2HourMin(long secDiff) {
        secDiff = secDiff * 1000;
        // 计算差多少小时
        long hour = secDiff / nh;
        // 计算差多少分钟
        long min = secDiff % nh / nm;
        // 计算差多少秒//输出结果
        long sec = secDiff % nh % nm / ns;
        StringBuilder time = new StringBuilder();
        if (hour > 0) {
            time.append(hour + "小时");
        }
        if (min > 0) {
            time.append(min + "分钟");
        }
        if (time.length() == 0) {
            if (secDiff > 0) {
                time.append("1分钟");
            } else {
                time.append("0分钟");
            }
        }
        return time.toString();
    }

    public static String sec2Min(long secDiff) {
        secDiff = secDiff * 1000;
        // 计算差多少分钟
        long min = secDiff / nm;
        if (min == 0L && secDiff > 0) {
            return "1";
        } else {
            return String.valueOf(min);
        }

    }

    public static List<LocalDate> getLatestWeek() {
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

    public static String time(Date startTime, Date endTime) {
        // 获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - startTime.getTime();
        StringBuilder sb = new StringBuilder();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;


        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    public static String mill2HourMin(long millDiff) {
        // 计算差多少小时
        long hour = millDiff / nh;
        // 计算差多少分钟
        long min = millDiff % nh / nm;
        // 计算差多少秒//输出结果
        long sec = millDiff % nh % nm / ns;
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
}
