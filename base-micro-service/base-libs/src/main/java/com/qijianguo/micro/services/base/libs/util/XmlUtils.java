package com.qijianguo.micro.services.base.libs.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class XmlUtils {

    private static final String PREFIX_XML = "<xml>";

    private static final String SUFFIX_XML = "</xml>";

    private static final String PREFIX_CDATA = "<![CDATA[";

    private static final String SUFFIX_CDATA = "]]>";

    /**
     * 转化成xml, 单层无嵌套
     *
     * @param parm
     * @param isAddCDATA
     * @return
     */
    public static String xmlFormat(Map<String, String> parm, boolean isAddCDATA) {

        StringBuffer strbuff = new StringBuffer(PREFIX_XML);
        if (!CollectionUtils.isEmpty(parm)) {
            for (Entry<String, String> entry : parm.entrySet()) {
                strbuff.append("<").append(entry.getKey()).append(">");
                if (isAddCDATA) {
                    strbuff.append(PREFIX_CDATA);
                    if (!StringUtils.isEmpty(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                    strbuff.append(SUFFIX_CDATA);
                } else {
                    if (!StringUtils.isEmpty(entry.getValue())) {
                        strbuff.append(entry.getValue());
                    }
                }
                strbuff.append("</").append(entry.getKey()).append(">");
            }
        }
        return strbuff.append(SUFFIX_XML).toString();
    }

    /**
     * 解析xml
     *
     * @param xml
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static Map<String, String> xmlParse(String xml) throws XmlPullParserException, IOException {
        Map<String, String> map = null;
        if (!StringUtils.isEmpty(xml)) {
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            XmlPullParser pullParser = XmlPullParserFactory.newInstance().newPullParser();
            pullParser.setInput(inputStream, "UTF-8");
            int eventType = pullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        map = new HashMap<String, String>();
                        break;
                    case XmlPullParser.START_TAG:
                        String key = pullParser.getName();
                        if (key.equals("xml")) {
                            break;
                        }
                        String value = pullParser.nextText().trim();
                        map.put(key, value);
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = pullParser.next();
            }
        }
        return map;
    }

    /**
     * 转换不带CDDATA的XML
     *
     * @return
     * @
     */
    private static XStream getXStream() {
        // 实例化XStream基本对象
        XStream xstream = new XStream(new DomDriver(StandardCharsets.UTF_8.name(), new NoNameCoder() {
            // 不对特殊字符进行转换，避免出现重命名字段时的“双下划线”
            @Override
            public String encodeNode(String name) {
                return name;
            }
        }));
        // 忽视XML与JAVABEAN转换时，XML中的字段在JAVABEAN中不存在的部分
        xstream.ignoreUnknownElements();
        return xstream;
    }

    /**
     * 转换带CDDATA的XML
     *
     * @return
     * @
     */
    private static XStream getXStreamWithCData() {
        // 实例化XStream扩展对象
        XStream xstream = new XStream(new XppDriver() {
            // 扩展xstream，使其支持CDATA块
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    // 不对特殊字符进行转换，避免出现重命名字段时的“双下划线”
                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    // 对所有xml节点的转换都增加CDATA标记
                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                };
            }
        });
        // 忽视XML与JAVABEAN转换时，XML中的字段在JAVABEAN中不存在的部分
        xstream.ignoreUnknownElements();
        return xstream;
    }

    /**
     * 以压缩的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toCompressXml(Object obj) {
        XStream xstream = getXStream();
        StringWriter sw = new StringWriter();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
        xstream.marshal(obj, new CompactWriter(sw));
        return sw.toString();
    }

    /**
     * 以格式化的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toXml(Object obj) {
        XStream xstream = getXStream();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * 转换成JavaBean
     *
     * @param xmlStr
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        XStream xstream = getXStream();
        // 识别cls类中的注解
        xstream.processAnnotations(cls);
        // 设置JavaBean的类别名
        xstream.aliasType("xml", cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

    /**
     * 以格式化的方式输出XML
     *
     * @param obj
     * @return
     */
    public static String toXmlWithCData(Object obj) {
        XStream xstream = getXStreamWithCData();
        // 识别obj类中的注解
        xstream.processAnnotations(obj.getClass());
        // 设置JavaBean的类别名
        xstream.aliasType("xml", obj.getClass());
        return xstream.toXML(obj);
    }

    /**
     * 转换成JavaBean
     *
     * @param xmlStr
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T toBeanWithCData(String xmlStr, Class<T> cls) {
        XStream xstream = getXStreamWithCData();
        // 识别cls类中的注解
        xstream.processAnnotations(cls);
        // 设置JavaBean的类别名
        xstream.alias("xml", cls);
        T t = (T) xstream.fromXML(xmlStr);
        return t;
    }

}