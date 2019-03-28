package com.liu.study.crawler;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Created on 2019-03-26
 *
 * @author liuzhaoyuan
 */
public class Demo2 {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
        //指定本地IP地址192 • 168 • 103 • 15
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();

        HttpGet httpGet = new HttpGet("https://time.geekbang.org/column/article/86695");
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie = new BasicClientCookie("SERVERID", "3431a294a18c59fc8f5805662e2bd51e|1553593801|1553589365");
        cookie.setVersion(0);
        cookie.setDomain("time.geekbang.org");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

//
//        BasicClientCookie cookie2 = new BasicClientCookie("SERVERID", "3431a294a18c59fc8f5805662e2bd51e|1553593801|1553589365");
//        cookie.setVersion(0);
//        cookie.setDomain("geekbang.org");
//        cookie.setPath("/");
//        cookieStore.addCookie(cookie2);
//
//
//        BasicClientCookie cookie3 = new BasicClientCookie("Hm_lvt_022f847c4e3acd44d4a2481d9187f1e6", "1553591613,1553592299,1553593246,1553593801");
//        cookie.setVersion(0);
//        cookie.setDomain("geekbang.org");
//        cookie.setPath("/");
//        cookieStore.addCookie(cookie3);
//
//
//        BasicClientCookie cookie4 = new BasicClientCookie("Hm_lpvt_022f847c4e3acd44d4a2481d9187f1e6", "1553593801");
//        cookie.setVersion(0);
//        cookie.setDomain("geekbang.org");
//        cookie.setPath("/");
//        cookieStore.addCookie(cookie4);

        httpGet.setHeader("User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");


        CloseableHttpClient httpClient = HttpClientBuilder.create().setRetryHandler(new StandardHttpRequestRetryHandler(5, true))
            .setDefaultRequestConfig(config).setDefaultCookieStore(cookieStore).addInterceptorFirst(new HttpRequestInterceptor() {
                @Override
                public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                    if (!httpRequest.containsHeader("Accept-Encoding")) {
                        httpRequest.addHeader("Accept-Encoding", "gzip");
                    }
                }
            }).addInterceptorFirst(new HttpResponseInterceptor() {
                @Override
                public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                    HttpEntity entity = httpResponse.getEntity();
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();

                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
                                return;
                            }
                        }

                    }
                }
            }).build();

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(response.getLastHeader("Content-Encoding"));
            System.out.println(response.getLastHeader("Content-Length"));
            System.out.println("--------> ");

            System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));

//            System.out.println("Uncompressed size: ");

            EntityUtils.consume(response.getEntity());

            System.out.println("--------> ");

            response.close();
        } finally {
            httpClient.close();
        }
    }

}
