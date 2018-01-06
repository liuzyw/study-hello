package com.study.spring.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017-08-09 23:39
 *
 * @author liuzhaoyuan
 */
public class StudentSelfCard implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Integer id;
    private Date birthday;
    private Integer studentId;
    private String adress;
    private String note;

    public StudentSelfCard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentSelfCard{" +
            "id=" + id +
            ", birthday=" + birthday +
            ", studentId=" + studentId +
            ", adress='" + adress + '\'' +
            ", note='" + note + '\'' +
            '}';
    }
}
