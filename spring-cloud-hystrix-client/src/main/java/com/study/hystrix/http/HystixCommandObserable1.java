package com.study.hystrix.http;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import rx.Observable;
import rx.Observer;

/**
 * Created on 2018-05-16
 *
 * @author liuzhaoyuan
 */
public class HystixCommandObserable1 extends HystrixObservableCommand<String> {

    public HystixCommandObserable1() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")) // 同一个线程池
            .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorld"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(observer -> {
            try {

                    String hello = "http://localhost:8112/hello";
                    // 处理批量
                    for (int i = 0; i < 8; i++) {
                        HttpGet httpGet = new HttpGet(hello);
                        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
                        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);

                        observer.onNext(EntityUtils.toString(httpResponse.getEntity()));
                    }
                    observer.onCompleted();

            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        return super.resumeWithFallback();
    }

    public static void main(String[] args) {
        HystixCommandObserable1 comd = new HystixCommandObserable1();

        Observable<String> observable = comd.observe();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("批量获取完成 。。。");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("每次结果： " + s);
            }
        });
    }
}
