package com.qijianguo.micro.services.base.libs.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 创建时间：2016年11月9日 下午4:16:32
 *
 * @author andy
 * @version 2.2
 */
@Slf4j
public class HttpUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     *  链接超时时间3秒
     */
    private static final int CONNECT_TIME_OUT = 5000;

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT).build();

    /**
     * @param url     请求地址
     * @param params  参数
     * @param headers headers参数
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {

        if (params != null && !params.isEmpty()) {
            StringBuilder param = new StringBuilder();
            boolean flag = true; // 是否开始
            for (Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    param.append("?");
                    flag = false;
                } else {
                    param.append("&");
                }
                param.append(entry.getKey()).append("=");

                try {
                    param.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    //编码失败
                }
            }
            url += param.toString();
        }

        String body = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .build();
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                headers.keySet().forEach(name -> httpGet.addHeader(name, headers.get(name)));
            }
            response = httpClient.execute(httpGet);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }

    /**
     * @param url 请求地址
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url) {
        return get(url, null);
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @return 请求失败返回null
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @return 请求失败返回null
     * @description 功能描述: post 请求
     */
    public static String post(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .build();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }

    public static String post(String url, Header header, HttpEntity entity) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            httpPost.setHeader(header);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }

    /**
     * @param url 请求地址
     * @param s   参数xml
     * @return 请求失败返回null
     * @description 功能描述: post 请求
     */
    public static String post(String url, String s) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .build();
            httpPost.setEntity(new StringEntity(s, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @return 请求失败返回null
     * @description 功能描述: post https请求，服务器双向证书验证
     */
    public static String posts(String url, Map<String, String> params) {
        return posts(url, params, null);
    }

    /**
     * @param url    请求地址
     * @param params 参数
     * @param ssl 证书
     * @return 请求失败返回null
     * @description 功能描述: post https请求，服务器双向证书验证
     */
    public static String posts(String url, Map<String, String> params, SSLContext ssl) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        String body = null;
        CloseableHttpResponse response = null;
        try {
            HttpClientBuilder builder = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG);
            if (ssl != null) {
                builder.setSSLSocketFactory(getSSLConnectionSocket(ssl));
            }
            httpClient = builder.build();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }

    /**
     * @param url 请求地址
     * @param s   参数xml
     * @return 请求失败返回null
     * @description 功能描述: post https请求，服务器双向证书验证
     */
    public static String posts(String url, String s) {
        return posts(url, s, null);
    }

    /**
     * @param url 请求地址
     * @param s   参数xml
     * @param ssl   ssl证书
     * @return 请求失败返回null
     * @description 功能描述: post https请求，服务器双向证书验证
     */
    public static String posts(String url, String s, SSLContext ssl) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .build();
            HttpClientBuilder builder = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG);
            if (ssl != null) {
                builder.setSSLSocketFactory(getSSLConnectionSocket(ssl));
            }
            builder.build();
            httpPost.setEntity(new StringEntity(s, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            close(response);
            close(httpClient);
        }
        return body;
    }



    /**
     * 获取ssl connection链接
     * @return
     */
    private static SSLConnectionSocketFactory getSSLConnectionSocket(SSLContext sslContext) {
        return new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"}, null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    }

    private static void close(CloseableHttpResponse response) {
        if (response != null) {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private static void close(CloseableHttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}