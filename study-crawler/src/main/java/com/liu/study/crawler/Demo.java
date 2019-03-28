package com.liu.study.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2019-03-26
 *
 * @author liuzhaoyuan
 */
public class Demo {

    private static final Long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws Exception {
        String url = "http://www.lietu.com/";

        logger.info("dsadasdas");

        //解析的结果就是一个文档对象
        Document doc = Jsoup.connect(url).timeout(5000).get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            //得到href属性中的值，也就是URL地址
            String linkHref = link.attr("href");
            //输出URL地址
            System.out.println(linkHref);
        }
//
        System.out.println("---------> ");

        Document document = Jsoup.connect("http://politics.people.com.cn/GB/1024/").timeout(5000).get();
        System.out.println(document.html());//打印网页源代码

        System.out.println("-----------> ");

        Elements elements = document.getElementsByClass("list_16");

        Elements links1 = elements.select("a[href]");
        for (Element link : links1) { //遍历每个链接，集合里面的每一个元素写在前面
            String title = link.text();
            System.out.println(title); //输出标题
            String linkHref = link.attr("href"); //得劉href属性中的值，也就是URL地址
            System.out.println(linkHref); //输出URL地址
        }

        System.out.println("------------> ");
        String url2 = "https://www.hao123.com/";
        Document document2 = Jsoup.connect(url2).timeout(2000).get();
        System.out.println(document2.html());//打印网页源代码
        System.out.println("----------------> ");
//        Element content2 = document2.getElementById("sites2_wrapper");
        Elements es = document2.getElementsByClass("sitelink mainlink js_text sitemainlink");
        for (Element linck : es) { //遍历每个链接，集合里面的每一个元素写在前面
            Element alink = linck.getElementsByTag("a").first();
            if (alink != null) {
                System.out.println(alink.attr("href"));
                System.out.println(alink.text());
            }
        }

    }

}
