package com.liu.study.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Created on 2019-03-26
 *
 * @author liuzhaoyuan
 */
public class RetrivePage {

    private static final Long serialVersionUID = 1L;


    public static String downloadPage(String path) {
//根据传入的路径构造URL
        try {
            URL pageURL = new URL(path);
            //创建网络流
            BufferedReader reader = new BufferedReader(new InputStreamReader(pageURL.openStream(), "UTF-8"));
            String line;
//读取网页内容
            StringBuilder pageBuffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                pageBuffer.append(line);
            }

            return pageBuffer.toString();
        } catch (Exception e) {

        }

        return "";
    }


    public static String downloadPage2(String path) { //创建一个客户端，类似于打开一个浏览器

        try {

            RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            //创建一个GET方法，类似于在浏览器地址栏中输入一个地址
            HttpGet httpget = new HttpGet(path);
            httpget.setConfig(config);

//类似于在浏览器地址栏中输入回车，获得网页内容
            HttpResponse response = httpclient.execute(httpget);
//查看返回的内容，类似于在浏览器查看网页源代码
            HttpEntity entity = response.getEntity();
            if (entity != null) { //读入内容流，并以字符串形式返回，这里指定网页编码是
                String html = EntityUtils.toString(entity, "UTF-8");//网页的 Meta 标签中指定了编码
                EntityUtils.consume(entity);//关闭内容流
                return html;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(RetrivePage.downloadPage("http://www.baidu.com"));
    }

}
