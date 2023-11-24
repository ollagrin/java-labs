package com.example.lab9.domain;

import java.io.Serializable;
/**
 * Grade class which contains info about
 * <b>id</b>, <b>studentId/b>, <b>examId</b>,
 * <b>studentName</b>, <b>studentSurname</b>, <b>groupNumber</b>, <b>exam</b> and <b>mark</b> properties.
 * @author Volha Hrynko
 * @version 0.1.1
 */

public class Grade implements Serializable {

    /**
     * Field of id in some data base
     */
    private int id;

    /**
     * Field of student id in some data base
     */
    private int studentId;

    /**
     * Field of exam id in some data base
     */
    private int examId;

    /**
     * Field of name of student
     */
    private String studentName;

    /**
     * Field of surname of student
     */
    private String studentSurname;

    /**
     * Field of number of group in which contains student
     */
    private int groupNumber;

    /**
     * Field of exam name
     */
    private String exam;

    /**
     * Field of mark on given exam
     */
    private int mark;

    /**
     * Constructor - creation of a new object
     * @see Grade#Grade(int, int, int, int)
     */
    public Grade(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param studentName - name of student
     * @param studentSurname - surname of student
     * @param groupNumber -  number of group in which contains student
     * @param exam - exam name
     * @param mark - mark on given exam
     */
    public Grade(String studentName, String studentSurname, int groupNumber, String exam, int mark) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.groupNumber = groupNumber;
        this.exam = exam;
        this.mark = mark;
    }

    /**
     * Constructor - creation of a new object with concrete values
     * @param id - id in some data base
     * @param studentId - student id in some data base
     * @param examId - exam id in some data base
     * @param mark - mark on given exam
     */
    public Grade(int id, int studentId, int examId, int mark) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.mark = mark;
    }


    /**
     * Gets value of field {@link Grade#studentName}
     * @return returns name of student
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets {@link Grade#studentName}
     * @param studentName
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Gets value of field {@link Grade#studentSurname}
     * @return returns surname of student
     */
    public String getStudentSurname() {
        return studentSurname;
    }

    /**
     * Sets {@link Grade#studentSurname}
     * @param studentSurname
     */
    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    /**
     * Gets value of field {@link Grade#groupNumber}
     * @return returns number of group in which contains student
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Gets value of field {@link Grade#examId}
     * @return returns exam id in some data base
     */
    public int getExamId() {
        return examId;
    }

    /**
     * Sets {@link Grade#examId}
     * @param examId
     */
    public void setExamId(int examId) {
        this.examId = examId;
    }


    /**
     * Sets {@link Grade#groupNumber}
     * @param groupNumber
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Gets value of field {@link Grade#exam}
     * @return returns name of exam
     */
    public String getExam() {
        return exam;
    }

    /**
     * Sets {@link Grade#exam}
     * @param exam
     */
    public void setExam(String exam) {
        this.exam = exam;
    }


    /**
     * Gets value of field {@link Grade#mark}
     * @return returns mark on given exam
     */
    public int getMark() {
        return mark;
    }

    /**
     * Sets {@link Grade#mark}
     * @param mark
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    /**
     * Gets value of field {@link Grade#id}
     * @return returns id on some data base
     */
    public int getId() {
        return id;
    }

    /**
     * Sets {@link Grade#id}
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets value of field {@link Grade#studentId}
     * @return returns student id in some data base
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets {@link Grade#studentId}
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
