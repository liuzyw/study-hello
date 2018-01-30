package com.study.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created on 2017-08-09 23:51
 *
 * @author liuzhaoyuan
 */
public class StudentHealthCheck implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Integer id;
    private Integer studentId;
    private Date checkDate;
    private String heart;
    private String lung;
    private String kidney;
    private String note;

    public StudentHealthCheck() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getLung() {
        return lung;
    }

    public void setLung(String lung) {
        this.lung = lung;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentHealthCheck{" +
            "id=" + id +
            ", studentId=" + studentId +
            ", checkDate=" + checkDate +
            ", heart='" + heart + '\'' +
            ", lung='" + lung + '\'' +
            ", kidney='" + kidney + '\'' +
            ", note='" + note + '\'' +
            '}';
    }
}
