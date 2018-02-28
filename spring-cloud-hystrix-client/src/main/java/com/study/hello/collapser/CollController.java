package com.study.hello.collapser;

import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollController {

    @Autowired
    private CollService collService;

    @RequestMapping(value = "/coll", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCollapse() throws Exception {
        // 连续执行3次请求
        Future<String> f1 = collService.getMember(1);
        Future<String> f2 = collService.getMember(2);
        Future<String> f3 = collService.getMember(3);
        String p1 = f1.get();
        String p2 = f2.get();
        String p3 = f3.get();
        return p1 + "-" + p2 + "-" + p3;
    }
}
