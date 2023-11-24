package com.example.lab9.domain;

import java.io.Serializable;

/**
 * Exam class which contains <b>id</b> and <b>examName</b> properties.
 * @author Volha Hrynko
 * @version 0.1.1
 */
public class Exam implements Serializable {

    /**
     * Field of id in some data base
     */
    private int id;

    /**
     * Field of exam name
     */
    private String examName;

    /**
     * Constructor - creation of a new object
     * @see Exam#Exam(int, String)
     */
    public Exam(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param id - id in some data base
     * @param examName - name of exam
     */
    public Exam(int id, String examName) {
        this.id = id;
        this.examName = examName;
    }

    /**
     * Gets value of field {@link Exam#id}
     * @return returns id in some data base
     */
    public int getId() {
        return id;
    }

    /**
     * Sets {@link Exam#id}
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets value of field {@link Exam#examName}
     * @return returns name of exam
     */
    public String getExamName() {
        return examName;
    }

    /**
     * Sets {@link Exam#examName}
     * @param examName
     */
    public void setExamName(String examName) {
        this.examName = examName;
    }
}
