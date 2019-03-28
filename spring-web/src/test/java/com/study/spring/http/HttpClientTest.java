package com.study.spring.http;

import java.io.File;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019-03-27
 *
 * @author liuzhaoyuan
 */
public class HttpClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);

    private static final String UTF = "UTF-8";

    private static final String agent = "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36";

    private CloseableHttpClient httpClient;

    private HttpGet httpGet;

    private CloseableHttpResponse response;


    @After
    public void close() throws Exception {
        if (response != null) {
            LOGGER.info("response close ... ");
            response.close();
        }

        if (httpClient != null) {
            LOGGER.info("httpClient close ... ");

            httpClient.close();
        }


    }

    /**
     * 简单的  get 请求
     *
     * @throws Exception
     */
    @Test
    public void demo1() throws Exception {
        httpClient = HttpClients.createDefault();

        httpGet = new HttpGet("https://www.tuicool.com/");

        response = httpClient.execute(httpGet);

        System.out.println(EntityUtils.toString(response.getEntity(), UTF));

    }


    /**
     * 模拟浏览器
     *
     * @throws Exception
     */
    @Test
    public void demo2() throws Exception {
        httpClient = HttpClients.createDefault();

        httpGet = new HttpGet("https://www.tuicool.com/");

        // 这里模拟浏览器
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

        response = httpClient.execute(httpGet);

        printResponseInfo(response);

        System.out.println(EntityUtils.toString(response.getEntity(), UTF));

    }


    /**
     * 获取图片
     *
     * @throws Exception
     */
    @Test
    public void demo3() throws Exception {
        httpClient = HttpClients.createDefault();

        httpGet = new HttpGet("http://www.java1234.com/uploads/allimg/181123/1-1Q1231J945B8.png");

        // 这里模拟浏览器
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

        response = httpClient.execute(httpGet);

        printResponseInfo(response);

        InputStream inputStream = response.getEntity().getContent();

        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/liuzhaoyuan/Desktop/dsad.png"));

    }


    /**
     * 使用代理 Ip
     *
     * @throws Exception
     */
    @Test
    public void demo4() throws Exception {

        httpClient = HttpClientBuilder.create().build();

        HttpHost httpHost = new HttpHost("192.168.1.69", 80);

        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setSocketTimeout(5000)
            .setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();


        httpGet = new HttpGet("https://www.tuicool.com/");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

        httpGet.setConfig(requestConfig);

        response = httpClient.execute(httpGet);

        printResponseInfo(response);

    }


    private void printResponseInfo(CloseableHttpResponse response) {

        System.out.println("Status : " + response.getStatusLine());

        System.out.println(response.getEntity().getContentType().getName() + " : " + response.getEntity().getContentType().getValue());

//        System.out.println("Content-Encoding : " + response.getEntity().getContentEncoding().getValue());

        System.out.println("ContentLength : " + response.getEntity().getContentLength());

        System.out.println("------------------->   ");
    }

}
