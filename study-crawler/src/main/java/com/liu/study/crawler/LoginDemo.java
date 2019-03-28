package com.liu.study.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

/**
 * Created on 2019-03-26
 *
 * @author liuzhaoyuan
 */
public class LoginDemo {


    /**
     * {country: 86, cellphone: "18612256271", password: "mit12345", captcha: "", remember: 1, platform: 3,remember: 1}
     */

    private static String userName = "18210016864";
    private static String password = "mit12345";
    private static String redirectURL = "https://time.geekbang.org/column/article/87365";
    private static String renRenLoginURL = "https://account.geekbang.org/login";

    private static CloseableHttpClient httpClient;

    private CloseableHttpResponse response;

    static {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

        httpClient = HttpClientBuilder.create().setRetryHandler(new StandardHttpRequestRetryHandler(5, true))
            .setDefaultRequestConfig(config).build();
    }

    private boolean login() {
        HttpPost httpost = new HttpPost(renRenLoginURL); //POST给网站的所有参数
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("redirect", redirectURL));
        nvps.add(new BasicNameValuePair("domain", "geekbang.org"));
        nvps.add(new BasicNameValuePair(" country", " 86 "));
        nvps.add(new BasicNameValuePair(" cellphone ", "18612256271"));
        nvps.add(new BasicNameValuePair("password", "mit12345"));
        nvps.add(new BasicNameValuePair("captcha", ""));
        nvps.add(new BasicNameValuePair("remember", "1"));
        nvps.add(new BasicNameValuePair("platform", "3"));
        try {
            httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            response = httpClient.execute(httpost);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            httpost.abort();
        }
        return true;
    }

    //取得重定向地址
    private String getRedirectLocation() {
        Header locationHeader = response.getFirstHeader("Location");
        if (locationHeader == null) {
            return null;
        }
        return locationHeader.getValue();
    }


    //根据重定向地址返回内容
    private String getText(String redirectLocation) {
        HttpGet httpget = new HttpGet(redirectLocation);
//创建一个响应处理器
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try {
//取得网页内容
            responseBody = httpClient.execute(httpget, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            responseBody = null;
        } finally {
            httpget.abort();
            httpClient.getConnectionManager().shutdown();//关闭连接
        }
        return responseBody;
    }

    public void printText() {
        if (login()) {
            System.out.println("-->");
            String redirectLocation = getRedirectLocation();
            if (redirectLocation != null) {
                System.out.println(getText(redirectLocation));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LoginDemo renRen = new LoginDemo();
        renRen.printText();
    }

}
