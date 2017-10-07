package com.study.spring.service.impl;

import com.study.spring.service.MailService;
import java.io.File;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-10-03
 *
 * @author user
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    private static final String MAIL_TO = "123456@qq.com";

    private static final String MAIL_FROM = "user@126.com";

    private static final String MAIL_SUBJECT = "hello";

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(MAIL_FROM);
            message.setTo(MAIL_TO);
            message.setSubject(MAIL_SUBJECT);

            message.setText("this is test for mail send 中国");

            javaMailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("send simpleMail fail " + e);
            return false;
        }

        return true;
    }


    @Override
    public boolean sendHTMLSimpleEmail() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

            messageHelper.setTo(MAIL_TO);
            messageHelper.setFrom(MAIL_FROM);
            messageHelper.setSubject("测试HTML邮件");
            //true 表示启动HTML格式的邮件
            messageHelper.setText("<html><head></head><body><h1>hello spring html Mail中文不乱码</h1></body></html>", true);
            javaMailSender.send(mimeMessage);

            return true;
        } catch (Exception e) {
            LOGGER.error("send simpleMail fail " + e);
        }

        return false;

    }

    /**
     * 发送HTML嵌套图片邮件
     *
     * @return
     */
    @Override
    public boolean sendHTMLImageEmail() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            //multipart模式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            messageHelper.setTo(MAIL_TO);
            messageHelper.setFrom(MAIL_FROM);
            messageHelper.setSubject("八里小学");
            //true 表示启动HTML格式的邮件
            messageHelper.setText("<html><head></head><body><h1>石门思</h1>"
                + "<br/><img src=\"cid:aaa\"/></body></html>", true);

            FileSystemResource img = new FileSystemResource(
                new File("/Users/user/gitwork/study-hello/spring-web/upload/psb.jpg"));

            messageHelper.addInline("aaa", img);

            javaMailSender.send(mimeMessage);

            return true;
        } catch (Exception e) {
            LOGGER.error("send simpleMail fail " + e);
        }
        return false;

    }

    /**
     * 发送带附件的邮件
     *
     * @return
     */
    @Override
    public boolean sendAttachedFileMail() {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            //multipart模式 为true时发送附件 可以设置html格式
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            messageHelper.setTo(MAIL_TO);
            messageHelper.setFrom(MAIL_FROM);
            messageHelper.setSubject("测试附件邮件");
            //true 表示启动HTML格式的邮件
            messageHelper.setText("<html><head></head><body><h1>hello spring html Mail 要有编码</h1>"
                + "有附件</body></html>", true);

            FileSystemResource img = new FileSystemResource(
                new File("/Users/user/gitwork/study-hello/spring-web/upload/psb.jpg"));

            messageHelper.addAttachment("psb.jpg", img);

            javaMailSender.send(mimeMessage);

            return true;

        } catch (Exception e) {
            LOGGER.error("send simpleMail fail " + e);
        }
        return false;
    }
}
