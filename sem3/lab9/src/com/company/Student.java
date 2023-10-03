package com.company;

import java.util.Map;

public class Student {
    private int cardNumber;
    private String surname;
    private Map<String, Integer> grades;

    Student(int cardNumber, String surname, Map<String, Integer> grades) {
        this.cardNumber = cardNumber;
        this.surname = surname;
        this.grades = grades;
    }

    public Student(Student stud) {
        this.cardNumber = stud.cardNumber;
        this.surname = stud.surname;
        this.grades = stud.grades;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" + "cardNumber=" + cardNumber +
                ", surname='" + surname + '\'' +
                ", grades=" + grades +
                '}';
    }
}