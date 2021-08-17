package com.study.model;

import com.study.base.ToString;

public class EventAction extends ToString {


    private Long Id;
    private String EventType;

    private String EventName;

    private String EventHandler;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getEventHandler() {
        return EventHandler;
    }

    public void setEventHandler(String eventHandler) {
        EventHandler = eventHandler;
    }
}
