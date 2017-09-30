package com.study.spring.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created on 2017-08-09 23:45
 *
 * @author liuzhaoyuan
 */
public class StudentLecture implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Integer id;
    private Integer studentId;
    private Lecture lecture;
    private BigDecimal grade;

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    private String note;

    public StudentLecture() {
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


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentLecture{" +
            "id=" + id +
            ", studentId=" + studentId +
            ", lectureId=" + lecture +
            ", grade=" + grade +
            ", note='" + note + '\'' +
            '}';
    }
}
