package com.study.mybatis.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017-08-09 23:34
 *
 * @author liuzhaoyuan
 */
public class Student implements Serializable {

    private static final Long serialVersionUID = 1L;


    private Integer id;
    private String cnname;
    private Sex sex;
    private StudentSelfCard studentSelfCard;
    private String note;
    private List<StudentLecture> studentLectures;

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public StudentSelfCard getStudentSelfCard() {
        return studentSelfCard;
    }

    public void setStudentSelfCard(StudentSelfCard studentSelfCard) {
        this.studentSelfCard = studentSelfCard;
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + id +
            ", cnname='" + cnname + '\'' +
            ", sex=" + sex +
            ", studentSelfCard=" + studentSelfCard +
            ", note='" + note + '\'' +
            ", studentLectures=" + studentLectures +
            '}';
    }

    public List<StudentLecture> getStudentLectures() {
        return studentLectures;
    }

    public void setStudentLectures(List<StudentLecture> studentLectures) {
        this.studentLectures = studentLectures;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;

        if (getCnname() != null ? !getCnname().equals(student.getCnname()) : student.getCnname() != null) {
            return false;
        }
        return getStudentSelfCard() != null ? getStudentSelfCard().equals(student.getStudentSelfCard())
            : student.getStudentSelfCard() == null;
    }

    @Override
    public int hashCode() {
        int result = getCnname() != null ? getCnname().hashCode() : 0;
        result = 31 * result + (getStudentSelfCard() != null ? getStudentSelfCard().hashCode() : 0);
        return result;
    }
}
