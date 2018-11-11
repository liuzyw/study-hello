package com.study.web.server.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018-11-10
 *
 * @author liuzhaoyuan
 */
public class WebContext {


    private List<Entity> entities;

    private List<Mapping> mappings;

    //key-->servlet-name  value -->servlet-class
    private Map<String, String> entityMap = new HashMap<String, String>();
    //key -->url-pattern value -->servlet-name
    private Map<String, String> mappingMap = new HashMap<String, String>();

    public WebContext() {
    }

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;

        for (Entity entity : entities) {
            entityMap.put(entity.getName(), entity.getClazz());
        }

        for (Mapping mapping : mappings) {
            for (String s : mapping.getPatterns()) {
                mappingMap.put(s, mapping.getName());
            }
        }

    }

    public String getClazz(String pattern) {

        String s = mappingMap.get(pattern);

        return entityMap.get(s);
    }
}
