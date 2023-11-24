package com.example.lab3.domain;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * Student class which contains info about student
 * @author Volha Hrynko
 * @version 0.1.1
 */
@XmlRootElement(name = "student")
@XmlType(propOrder = {"id", "name", "surname", "groupNumber"})
public class Student implements Serializable {

    /**
     * Field of id in some data base
     */
    private int id;

    /**
     * Field of name of student
     */
    private String name;

    /**
     * Field of surname of student
     */
    private String surname;

    /**
     * Field of number of group in which contains student
     */
    private int groupNumber;


    /**
     * Constructor - creation of a new object
     * @see Student#Student(int, String, String, int)
     */
    public Student(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param id - id in some data base
     * @param name - name of student
     * @param surname - surname of student
     * @param groupNumber - number of group in which contains student
     */
    public Student(int id, String name, String surname, int groupNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.groupNumber = groupNumber;
    }

    /**
     * Gets value of field {@link Student#id}
     * @return returns id on some data base
     */
    public int getId() {
        return id;
    }

    /**
     * Sets {@link Student#id}
     * @param id
     */
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets value of field {@link Student#name}
     * @return returns name of student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets {@link Student#name}
     * @param name
     */
    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value of field {@link Student#surname}
     * @return returns surname of student
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets {@link Student#surname}
     * @param surname
     */
    @XmlElement(name = "surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets value of field {@link Student#groupNumber}
     * @return returns number of group in which contains student
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Sets {@link Student#groupNumber}
     * @param groupNumber
     */
    @XmlElement(name = "groupNumber")
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder().
                append(name).
                append(" ").
                append(surname).
                append(" group ").
                append(groupNumber).toString();
    }

}
