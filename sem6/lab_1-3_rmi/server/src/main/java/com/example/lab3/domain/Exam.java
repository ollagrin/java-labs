package com.example.lab3.domain;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;

/**
 * Exam class which contains <b>id</b> and <b>examName</b> properties.
 * @author Volha Hrynko
 * @version 0.1.1
 */

@XmlRootElement(name = "exam")
@XmlType(propOrder = {"id", "examName"})
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
    @XmlAttribute
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
    @XmlElement(name = "examName")
    public void setExamName(String examName) {
        this.examName = examName;
    }
}
