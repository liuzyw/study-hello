package com.study.web.server;

import com.study.web.server.servlet.Servlet;
import com.study.web.server.servlet.WebContext;
import com.study.web.server.servlet.WebHandler;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * Created on 2018-11-11
 *
 * @author liuzhaoyuan
 */
public class WebApp {

    private static WebContext webContext;


    static {

        try {
            //SAX解析
            //1、获取解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //2、从解析工厂获取解析器
            SAXParser parse = factory.newSAXParser();
            //3、编写处理器
            WebHandler handler = new WebHandler();
            //4、加载文档 Document 注册处理器
            //5、解析
            parse.parse(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("web.xml")
                , handler);

            //6 获取数据
            System.out.println(handler.getEntities());
            System.out.println(handler.getMappings());

            webContext = new WebContext(handler.getEntities(), handler.getMappings());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Servlet getServletFromUrl(String url) {
        //假设你输入了 /login
        String className = webContext.getClazz("/" + url);
        Class clz;
        try {
            System.out.println(url + "-->" + className + "-->");
            clz = Class.forName(className);
            Servlet servlet = (Servlet) clz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {

        }

        return null;

    }

}
