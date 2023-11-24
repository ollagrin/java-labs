package com.example.lab3.domain;

import java.io.Serializable;


public class Grade implements Serializable {
    private int id;
    private int studentId;
    private int examId;


    private String studentName;
    private String studentSurname;
    private int groupNumber;
    private String exam;
    private int mark;

    public Grade(){}

    public Grade(String studentName, String studentSurname, int groupNumber, String exam, int mark) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.groupNumber = groupNumber;
        this.exam = exam;
        this.mark = mark;
    }

    public Grade(int id, int studentId, int examId, int mark) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.mark = mark;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public int getGroupNumber() {
        return groupNumber;
    }


    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
