package com.study.cloud.feign;

import com.study.cloud.feign.contract.MyUrl;
import com.study.cloud.feign.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("spring-cloud-feign-producer")
public interface HelloClient {

	@RequestMapping(method = RequestMethod.GET, value="/hello")
    String hello();
	
	
	@RequestMapping(method = RequestMethod.GET, value="/getUserAge/{name}")
	int getUserAge(@PathVariable("name") String name);
	
	@MyUrl(url = "/getRestUser", method = "GET")
	User getRestUser();
}
