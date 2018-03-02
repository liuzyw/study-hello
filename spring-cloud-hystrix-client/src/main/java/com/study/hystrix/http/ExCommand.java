package com.study.hystrix.http;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
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
public class ExCommand extends HystrixCommand<String> {

    public ExCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("TestGroup"));
    }

    @Override
    protected String run() throws Exception {
        String errorHello = "http://localhost:8112/errorHello";

        HttpGet httpGet = new HttpGet(errorHello);

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
        return EntityUtils.toString(httpResponse.getEntity());
    }

    @Override
    protected String getFallback() {
        System.out.println("roll back");
        return "fail back";
    }
}
