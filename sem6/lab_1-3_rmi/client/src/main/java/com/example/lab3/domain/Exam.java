package com.example.lab3.domain;

import java.io.Serializable;

/**
 * 
 */

public class Exam implements Serializable {
    private int id;
    private String examName;

    public Exam(){}

    public Exam(int id, String examName) {
        this.id = id;
        this.examName = examName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
