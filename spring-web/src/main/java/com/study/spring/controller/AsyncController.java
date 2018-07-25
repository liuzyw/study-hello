package com.study.spring.controller;

import com.study.util.date.DateUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * Created on 2018-07-25
 *
 * @author liuzhaoyuan
 */
@Controller
public class AsyncController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);


    private DeferredResult<String> deferredResult = new DeferredResult<>();

    @RequestMapping("/async1")
    public Callable<String> callableWithView(@RequestParam("time") int time, final Model model) {

        Callable<String> callable = () -> {
            Thread.sleep(time);
            model.addAttribute("name", "aysc1 展示");
            model.addAttribute("desc", "call with view");
            model.addAttribute("time", DateUtils.getCurTimeStr());
            return "async/async";
        };
        LOGGER.info("异步处理请求 ..... ");
        return callable;
    }

    @RequestMapping("/async2")
    public WebAsyncTask<String> callableWithTimeOut(@RequestParam("time") int time, final Model model) {

        Callable<String> callable = () -> {
            Thread.sleep(time);
            model.addAttribute("name", "aysc2 张三");
            model.addAttribute("desc", "call with timeout");
            model.addAttribute("time", DateUtils.getCurTimeStr());
            return "async/async";
        };
        LOGGER.info("异步处理请求 ..... ");

        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(4000, callable);
        webAsyncTask.onCompletion(() -> {
            LOGGER.info("我执行完了 ....");
        });
        webAsyncTask.onTimeout(() -> {
            LOGGER.info("这个请求超时了 。。。");
            return "async/timeout";
        });
        LOGGER.info("异步处理请求结束 ..... ");

        return webAsyncTask;
    }

    @RequestMapping(value = "/async3", produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseBody
    public DeferredResult<String> quotes(@RequestParam("time") int time, HttpServletResponse response) throws Exception {
        TimeUnit.MILLISECONDS.sleep(time);
        deferredResult.onTimeout(() -> {
            LOGGER.info("我超时啦!");
            deferredResult.setErrorResult("time out 超时");
        });
        deferredResult.onCompletion(() -> {
            if (!deferredResult.isSetOrExpired()) {
                LOGGER.info("我执行完了 ....");
                // 这个不会执行
                deferredResult.setResult("hello task");
            }
        });

        return deferredResult;
    }

    @RequestMapping("/async3set")
    public String setDeferredResult(final Model model) {
        deferredResult.setResult("Test result! 成功");
        model.addAttribute("name", "async3set");
        model.addAttribute("desc", "set deferredResult value");
        model.addAttribute("time", DateUtils.getCurTimeStr());
        return "async/async";
    }

}
