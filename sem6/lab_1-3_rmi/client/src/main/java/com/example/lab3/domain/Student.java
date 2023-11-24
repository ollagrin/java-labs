package com.example.lab3.domain;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String surname;
    private int groupNumber;

    public Student(){}
    public Student(int id, String name, String surname, int groupNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.groupNumber = groupNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

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
