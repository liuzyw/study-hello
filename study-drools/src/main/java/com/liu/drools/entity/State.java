package com.liu.drools.entity;

import java.beans.PropertyChangeSupport;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class State {

    public static final int NOTRUN = 0;
    public static final int FINISHED = 1;
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String name;
    private int state;

    public State(String name) {
        this.name = name;
    }
}