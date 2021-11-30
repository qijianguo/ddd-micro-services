package com.qijianguo.micro.services.base.libs.util;

import com.vdurmont.emoji.EmojiParser;

/**
 * @author qijianguo
 */
public class EmojiUtils {

    public EmojiUtils() {
    }

    public static String parseToHtmlHexadecimal(String emoji_str) {
        return EmojiParser.parseToHtmlHexadecimal(emoji_str);
    }

    public static String parseToHtmlTag(String emoji_str) {
        if (emoji_str != null) {
            String str = EmojiParser.parseToHtmlHexadecimal(emoji_str);
            return htmlHexadecimalToHtmlTag(str);
        } else {
            return null;
        }
    }

    public static String parseToAliases(String emoji_str) {
        return EmojiParser.parseToAliases(emoji_str);
    }

    public static String parseToHtmlDecimal(String emoji_str) {
        return EmojiParser.parseToHtmlDecimal(emoji_str);
    }

    public static String removeAllEmojis(String emoji_str) {
        return EmojiParser.removeAllEmojis(emoji_str);
    }

    public static String htmlHexadecimalToHtmlTag(String emoji_str) {
        return emoji_str != null ? emoji_str.replaceAll("&#x([^;]*);", "<span class='emoji emoji$1'></span>") : null;
    }

    public static String parse(String emoji_str, int type) {
        switch(type) {
        case 1:
            return parseToHtmlHexadecimal(emoji_str);
        case 2:
            return parseToHtmlTag(emoji_str);
        case 3:
            return parseToAliases(emoji_str);
        case 4:
            return parseToHtmlDecimal(emoji_str);
        case 5:
            return removeAllEmojis(emoji_str);
        default:
            return null;
        }
    }
}
