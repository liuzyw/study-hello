package com.study.spring.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created on 2018-07-01
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
@WebAppConfiguration
public class WeatherControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc

        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        // 利用contextholder将被测试文件的request锁定到测试文件mock的request
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        response = new MockHttpServletResponse();

    }


    @Test
    public void toPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/goWeather"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
//            .andExpect(jsonPath("$.code").value(20000))
//            .andExpect(jsonPath("$.demoList").exists())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

    }


}
