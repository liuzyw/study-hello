package com.study.kafka;

import java.io.Serializable;

/**
 * Created on 2018-04-10
 *
 * @author liuzhaoyuan
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 4009862699662785133L;

    private Long id;
    private String name;
    private String content;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
