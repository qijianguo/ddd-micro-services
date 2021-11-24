package com.qijianguo.micro.services.base.libs.util;

/**
 * @author qijianguo
 * SOLR 查询的工具类
 */
public class SolrUtils {

    /**
     * 查询条件特殊字符处理
     *
     * @param keyword 查询字段
     * @return
     */
    public static String escapeQueryChars(String keyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':'
                    || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                    || c == '*' || c == '?' || c == '|' || c == '&' || c == ';' || c == '/'
                    || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
