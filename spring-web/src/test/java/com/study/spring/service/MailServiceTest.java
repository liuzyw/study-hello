package com.study.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2017-10-03
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendSimpleMail() {
//        mailService.sendSimpleEmail();
//        mailService.sendHTMLSimpleEmail();
        mailService.sendHTMLImageEmail();
//        mailService.sendAttachedFileMail();
        System.out.println("-------------");

        System.out.println("success");
    }
}
