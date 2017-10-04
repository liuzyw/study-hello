package com.study.spring.service;

/**
 * Created on 2017-10-03
 *
 * @author liuzhaoyuan
 */
public interface MailService {

    /**
     * 发送简单的文本邮件
     * @return
     */
    boolean sendSimpleEmail();

    /**
     * 发送简单的HTML格式邮件
     * @return
     */
    boolean sendHTMLSimpleEmail();

    /**
     * 发送HTML嵌套图片邮件
     * @return
     */
    boolean sendHTMLImageEmail();

    /**
     * 发送带附件的邮件
     * @return
     */
    boolean sendAttachedFileMail();

}
