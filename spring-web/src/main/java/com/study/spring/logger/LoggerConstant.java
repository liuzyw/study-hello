package com.study.spring.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2020-03-13
 *
 * @author liuzhaoyuan
 */
public class LoggerConstant {

    public static final Logger APP_DEFAULT = LoggerFactory.getLogger("APP-DEFAULT");

    public static final Logger APP_ALERT = LoggerFactory.getLogger("APP-ALERT");


    public static final Logger APP_ERROR = LoggerFactory.getLogger("APP-ERROR");


    public static final Logger SERVICE_DIGEST_LOG = LoggerFactory.getLogger("SERVICE-DIGEST-LOG");


    public static final Logger DAO_DIGEST_LOG = LoggerFactory.getLogger("DAO-DIGEST-LOG");


}
