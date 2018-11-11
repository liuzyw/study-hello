package com.study.web.server.servlet;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created on 2018-11-11
 *
 * @author liuzhaoyuan
 */
public class WebHandler extends DefaultHandler {

    private List<Entity> entities;

    private List<Mapping> mappings;

    private Entity entity;

    private Mapping mapping;

    private String tag;

    private boolean isEntity = false;

    private boolean isMapping = false;


    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<>();
        mappings = new ArrayList<>();

        System.out.println("----解析文档开始----");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "-->解析开始");
        tag = qName;
        if ("servlet".equals(qName)) {
            entity = new Entity();
            isEntity = true;
        }

        if ("servlet-mapping".equals(qName)) {
            mapping = new Mapping();
            isMapping = true;
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch, start, length).trim();
        if (contents.length() > 0) {
            System.out.println("内容为->" + contents);

            if (isMapping) {
                if ("servlet-name".equals(tag)) {
                    mapping.setName(contents);
                }
                if ("url-pattern".equals(tag)) {
                    mapping.add(contents);
                }
            }

            if (isEntity) {
                if ("servlet-name".equals(tag)) {
                    entity.setName(contents);
                }

                if ("servlet-class".equals(tag)) {
                    entity.setClazz(contents);
                }
            }
        } else {
            System.out.println("内容为->" + "空");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "-->解析结束开始");
        if (null != qName) {
            if (qName.equals("servlet")) {
                entities.add(entity);
                isEntity = false;
            }

            if (qName.equals("servlet-mapping")) {
                mappings.add(mapping);
                isMapping = false;
            }
        }
        tag = null; //tag丢弃了
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("----解析文档结束----");

    }
}
