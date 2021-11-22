package com.qijianguo.micro.services.base.libs.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qijianguo
 */
public class RegexUtils {

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    @Deprecated
    public static boolean isMobile(String str) {
        Pattern p;
        Matcher m;
        boolean b = false;
        String s2 = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        if (StringUtils.isEmpty(str)) {
            p = Pattern.compile(s2);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }

    /**
     * 验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean isMobile2(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[1|8|9]))\\d{8}$";
        if (phone == null || phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }

    public static String replaceWithStar(String name) {
        if (name == null || name.length() <= 1) {

        } else if (name.length() == 2) {
            name = name.substring(0, 1) + "*";
        } else if (name.length() == 3) {
            String sub = name.substring(1, 2);
            name = name.replace(sub, "*");
        } else if (name.length() == 11) {
            String sub = name.substring(3, name.length() - 4);
            name = name.replace(sub, "****");
        } else {
            String sub = name.substring(2, name.length() - 1);
            name = name.replace(sub, "**");
        }
        return name;
    }

    public static String emojiFilter(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
        String reStr = "";
        Pattern emoji = Pattern.compile(pattern);
        Matcher emojiMatcher = emoji.matcher(str);
        str = emojiMatcher.replaceAll(reStr);
        return str;
    }

    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumberic(String str) {
        String pattern = "[0-9]*";
        Pattern number = Pattern.compile(pattern);
        return number.matcher(str).matches();
    }

    /**
     * 判断一个字符串是否为字母
     * @param fstrData
     * @return
     */
    public static boolean check(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    // 纯数字
    private static String DIGIT_REGEX = "[0-9]+";
    // 含有数字
    private static String CONTAIN_DIGIT_REGEX = ".*[0-9].*";
    // 纯字母
    private static String LETTER_REGEX = "[a-zA-Z]+";
    // 包含字母
    private static String CONTAIN_LETTER_REGEX = ".*[a-zA-z].*";
    // 纯中文
    private static String CHINESE_REGEX = "[\u4e00-\u9fa5]";
    // 仅仅包含字母和数字
    private static String LETTER_DIGIT_REGEX = "^[a-z0-9A-Z]+$";
    private static String CHINESE_LETTER_REGEX = "([\u4e00-\u9fa5]+|[a-zA-Z]+)";
    private static String CHINESE_LETTER_DIGIT_REGEX = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";

    /**
     * 判断字符串是否仅含有数字和字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        return str.matches(LETTER_DIGIT_REGEX);
    }

    /**
     * 是否为汉字，不包括标点符号
     *
     * @param con
     * @return true 是汉字
     */
    public static boolean isChinese(String con) {
        Pattern pattern = Pattern.compile(CHINESE_REGEX);
        for (int i = 0; i < con.length(); i = i + 1) {
            if (!pattern.matcher(
                    String.valueOf(con.charAt(i))).find()) {
                return false;
            }
        }
        return true;
    }
    /**
     * 判断字符串中是否仅包含英文字母、数字和汉字
     * @param str
     * @return true 仅包含英文字母、数字和汉字
     */
    public static boolean isLetterDigitOrChinese(String str) {
        return str.matches(CHINESE_LETTER_DIGIT_REGEX);
    }

    /**
     * 判断是否包含汉字和字母，无其它字符
     *
     * @param str
     * @return true 不包含特殊字符, false 包含了特殊字符
     */
    public static boolean checkChineseLetter(String str) {
        Pattern pattern = Pattern.compile(CHINESE_LETTER_REGEX);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    /**
     * 判断一个字符串是否包含标点符号（中文或者英文标点符号），true 包含。<br/>
     * 原理：对原字符串做一次清洗，清洗掉所有标点符号。<br/>
     * 此时，如果入参 ret 包含标点符号，那么清洗前后字符串长度不同，返回true；否则，长度相等，返回false。<br/>
     *
     * @param ret
     * @return true 包含中英文标点符号
     */
    public static boolean checkPunctuation(String ret) {
        boolean b = false;
        String tmp = ret;
//        replaceAll里面的正则匹配可以清空字符串中的中英文标点符号，只保留数字、英文和中文。
        tmp = tmp.replaceAll("\\p{P}", "");
        if (ret.length() != tmp.length()) {
            b = true;
        }
        return b;
    }

    /**
     * 判断是否是纯数字
     * @param ret
     * @return
     */
    public static boolean isDigit(String ret) {
        return ret.matches(DIGIT_REGEX);
    }

    /**
     * 判断是否是纯字母
     * @param ret
     * @return
     */
    public static boolean isLetter(String ret) {
        return ret.matches(LETTER_REGEX);
    }

    /**
     * 判断是否包含数字
     * @param ret
     * @return
     */
    public static boolean hasDigit(String ret) {
        return ret.matches(CONTAIN_DIGIT_REGEX);
    }

    /**
     * 判断是否包含字母
     * @param ret
     * @return
     */
    public static boolean hasLetter(String ret) {
        return ret.matches(CONTAIN_LETTER_REGEX);
    }

    /**
     * 修剪简介
     * @param summary
     * @return
     */
    public static String replace(String summary) {
        if (summary != null) {
            summary = summary.replace(" ", "").replace("　", "").replace("\r\n", "   ").replace("\n", "   ");
            if (summary.length() > 50) {
                summary = summary.substring(0, 50);
                int length = summary.length();
                for (int i = length - 1; i > 0; i--) {
                    char c = summary.charAt(i);
                    if (!checkPunctuation(String.valueOf(c))) {
                        summary = summary.substring(0, i + 1) + "...";
                        break;
                    }
                }
            }
        }
        return summary;
    }

}
