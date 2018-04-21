package com.springmvc;

import com.springmvc.annotation.MyAutowrited;
import com.springmvc.annotation.MyController;
import com.springmvc.annotation.MyRequestMapping;
import com.springmvc.annotation.MyService;
import com.springmvc.service.HelloService;
import com.springmvc.service.WordService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController("chaoyue")
public class SpringmvcController {

    @MyAutowrited("helloService")
	private HelloService helloService;

    @MyAutowrited("wordServiceImpl")
	private WordService wordService;

	@MyRequestMapping("/insert")
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		helloService.sayHello();
		wordService.sayHello();
		try {
			response.getWriter().write(" SpringmvcController doTest method success! param: ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ddd";
	}

}
