package com.study.mybatis.entity;

import java.io.Serializable;

/**
 * Created on 2017-08-09 23:43
 *
 * @author liuzhaoyuan
 */
public class Lecture implements Serializable{

    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String lectureName;
    private String note;

    public Lecture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "LectureMapper{" +
            "id=" + id +
            ", lectureName='" + lectureName + '\'' +
            ", note='" + note + '\'' +
            '}';
    }
}
