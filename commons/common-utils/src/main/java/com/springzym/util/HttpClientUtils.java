package com.springzym.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * 发送 http 请求工具类
 * 依赖 commons-httpclient3.1 和 httpclient4.5.13 包
 * @author zym
 */
public class HttpClientUtils {

    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 发起post请求 没有任何body参数的情况
     * @param url  请求的目标url地址
     * @param data 请求数据
     * @return 将响应结果转换成string返回
     * @throws IOException 可能出现的异常
     */
    public static String postMsg(String url, String data) throws IOException {
        // 根据url地址发起post请求
        HttpPost httppost = new HttpPost(url);

        StringEntity stEntity;

        // 获取到httpclient客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 设置请求的一些头部信息
            httppost.addHeader("Content-Type", "application/json");
            httppost.addHeader("procode", "test");
            stEntity = new StringEntity(data, "UTF-8");
            httppost.setEntity(stEntity);
            // 设置请求的一些配置设置，主要设置请求超时，连接超时等参数
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000)
                    .build();
            httppost.setConfig(requestConfig);
            // 执行请求
            CloseableHttpResponse response = httpclient.execute(httppost);
            // 请求结果
            String resultString = "";
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                log.info("请求状态：{}", response.getStatusLine().getStatusCode());
                // 获取请求响应结果
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 将响应内容转换为指定编码的字符串
                    resultString = EntityUtils.toString(entity, "UTF-8");
                    log.info("Response content:{}", resultString);
                    return resultString;
                }
            } else {
                log.info("请求失败！");
                return resultString;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
        }
        return null;
    }

    /**
     * body有内容的post请求方法 controller可以用bean直接接收参数
     * @param url      目标地址
     * @param params   封装的参数
     * @param codePage 字符编码
     * @return 将响应结果转换成string返回
     * @throws Exception 可能出现的异常
     */
    public synchronized static String postData(String url, Map<String, String> params, String codePage) throws Exception {
        HttpClient httpClient = new HttpClient();
        // 请求相关超时时间设置
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(20 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(20 * 1000);

        PostMethod method = new PostMethod(url);
        if (params != null) {
            // 内容编码设置
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
            // 请求体信息设置
            method.setRequestBody(assembleRequestParams(params));
            // 请求头设置
            method.setRequestHeader("procode", "test");
        }

        // 响应结果信息处理
        String result = "";
        try {
            httpClient.executeMethod(method);
            result = new String(method.getResponseBody(), codePage);
        } catch (Exception e) {
            throw e;
        } finally {
            // 释放连接
            method.releaseConnection();
        }
        return result;
    }

    /**
     * 组装http请求参数
     * @param data 键值对
     * @return 返回一个名称-值的键值对
     */
    private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
        List<NameValuePair> nameValueList = new ArrayList<>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            nameValueList.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }

//        log.info("键值对参数：{}", ArrayUtils.toString(nameValueList.toArray(new NameValuePair[nameValueList.size()])));

        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
    }

    /**
     * get请求
     * @param url 目标请求地址
     * @param headers 请求头信息
     * @return 将响应结果转换成string返回
     */
    public static String get(String url,HashMap<String, String> headers) {
        String result = "";
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);
            if (!headers.isEmpty()){
                headers.keySet().forEach(key -> request.setHeader(key, headers.get(key)));
            }
            // 获取当前客户端对
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 通过请求对象获取响应对象
            HttpResponse response = httpclient.execute(request);
            // 判断请求结果状态码
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        String url = "http://localhost:8080/sales-webapp/admin/report/smslist";
//        // 发起post请求
//        String resultPost = postMsg(url, "1");
//        log.info("post返回结果：{}", resultPost);
//
//        // 发起get请求
//        String resultGet = get(url);
//        log.info("get返回结果：{}", resultGet);
//
//        // 测试body有参数的post方法
//        Map<String, String> map = new HashMap<>(16);
//        map.put("age", "13");
//        map.put("name", "jason");
//        String resultPostHaveBody = postData(url, map, "UTF-8");
//        log.info("post带有body请求返回结果：{}", resultPostHaveBody);
//    }
}
