package com.study.spring.controller;

import com.alibaba.fastjson.JSON;
import com.study.spring.entity.Bus;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created on 2018-07-01
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml",
    "classpath:spring/dispatcher-servlet.xml"})
@WebAppConfiguration
public class AjaxControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private String url;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }


    @Test
    public void testAjaxGet() throws Exception {
        url = "/getBuss1";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).
            accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        System.out.println(new String(result.getResponse().getContentAsByteArray(), "UTF-8"));

    }

    @Test
    public void testAjaxPostParams() throws Exception {
        url = "/getWeatherByCityName";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).param("name", "上海").
            accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        System.out.println(new String(result.getResponse().getContentAsByteArray(), "UTF-8"));

    }

    @Test
    public void testAjaxPostBody() throws Exception {
        url = "/getAjaxMessage";
        Bus bus = new Bus();
        bus.setDate(new Date());
        bus.setName("奔驰");
        bus.setColor("紫黑色");

        String json = JSON.toJSONString(bus);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(url).content(json).
            accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        System.out.println(new String(result.getResponse().getContentAsByteArray(), "UTF-8"));

    }

    @Test
    public void testAjaxGetBody() throws Exception {
        url = "/getAjaxMessage1";
        Bus bus = new Bus();
        bus.setDate(new Date());
        bus.setName("奔驰");
        bus.setColor("紫黑色");

        String json = JSON.toJSONString(bus);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).content(json).
            accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        System.out.println(new String(result.getResponse().getContentAsByteArray(), "UTF-8"));

    }

    @Test
    public void testView() throws Exception {
        url = "/showBook/10";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.view().name("book/showBook"))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        System.out.println(result.getResponse().getStatus());

        System.out.println(result.getModelAndView().getModel().get("book"));
    }

    @Test
    public void testViewParam() throws Exception {
        url = "/showFruit?fruitId=8";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andExpect(MockMvcResultMatchers.view().name("fruit/showFruit"))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        System.out.println(result.getResponse().getStatus());

        System.out.println(result.getModelAndView().getModel().get("fruit"));
    }


}
