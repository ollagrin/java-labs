package com.example.lab9.service;

import com.example.lab9.domain.Exam;
import com.example.lab9.domain.Grade;
import com.example.lab9.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface DBHandlerInterface {

    int getNewId() throws SQLException;

    int getNewIdGrade() throws SQLException;

    List<Student> getAllStudents();

    List<Grade> getAllGrades();

    List<Exam> getAllExams();

    void addStudent(Student student);

    void addExam(Exam exam);

    void addGrade(Grade grade);

    void deleteStudent(int id);

    List<Student> getLosers() throws SQLException;

    void deleteLosers() throws SQLException;

}
