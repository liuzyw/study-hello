package com.study.spring.http;

import com.alibaba.fastjson.JSON;
import com.study.spring.entity.Bus;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/**
 * Created on 2018-07-01
 *
 * @author liuzhaoyuan
 */
public class StockTest {


    @Test
    public void get() throws Exception {
        String hello = "http://localhost:8080/getBuss1";

        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个GET对象
        HttpGet get = new HttpGet(hello);

        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //取响应的结果
        System.out.println("-------------------------");
        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");
        System.out.println(string);

        //关闭httpclient
        response.close();
        httpClient.close();
    }

    @Test

    public void doGetWithParam() throws Exception {

        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/getAjaxMessage1");
        uriBuilder.addParameter("color", "getColor");
        uriBuilder.addParameter("name", "getName");

        HttpGet get = new HttpGet(uriBuilder.build());

        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);

        //取响应的结果
        System.out.println("-------------------------");

        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity entity = response.getEntity();
        String string = EntityUtils.toString(entity, "utf-8");

        System.out.println(string);

        //关闭httpclient
        response.close();
        httpClient.close();

    }

    @Test
    public void post() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个post对象

        HttpPost post = new HttpPost("http://localhost:8080/getBuss");

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());

        String string = EntityUtils.toString(response.getEntity());
        System.out.println("-------------------------");
        System.out.println(string);

        response.close();
        httpClient.close();
    }

    /**
     * 分两种情况，一种是不是靠Spring mvc解析，从request中取
     */
    @Test
    public void doPostWithParam() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8080/postOne");

        //创建一个Entity。模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("color", "black"));
        kvList.add(new BasicNameValuePair("name", "QQ"));

        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");

        //设置请求的内容
        post.setEntity(entity);

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        String string = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("-------------------------");

        System.out.println(string);

        response.close();
        httpClient.close();

    }


    /**
     * 参数直接转成对象
     */
    @Test
    public void doPostWithParam2() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8080/getAjaxMessage");

        //创建一个Entity。模拟一个表单
        Bus bus1 = new Bus();
        bus1.setColor("red3123123123");
        bus1.setName("BWM");
        bus1.setDate(new Date());

        //包装成一个Entity对象
        // 构建消息实体
        StringEntity entity = new StringEntity(JSON.toJSONString(bus1), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");

        //设置请求的内容
        post.setEntity(entity);

        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        String string = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println("-------------------------");

        System.out.println(string);

        response.close();
        httpClient.close();

    }

}
