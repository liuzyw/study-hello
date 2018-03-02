package com.study.hystrix.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created on 2018-02-21
 *
 * @author liuzhaoyuan
 */
public class HystixDemo {

    public static void main(String[] args) throws Exception {
        String errorHello = "http://localhost:8112/errorHello";
        String hello = "http://localhost:8112/hello";

        HttpGet httpGet = new HttpGet(hello);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        System.out.println(" get ----- " + EntityUtils.toString(httpResponse.getEntity()));

    }

}
