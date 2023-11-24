package com.example.lab3.service;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface DBHandlerInterface extends Remote {
    int getNewId() throws SQLException, RemoteException;

    int getNewIdGrade() throws SQLException, RemoteException;

    List<Student> getAllStudents() throws RemoteException;

    List<Grade> getAllGrades() throws RemoteException;

    void addStudent(Student var1) throws RemoteException;

    void addExam(Exam var1) throws RemoteException;

    void addGrade(Grade var1) throws RemoteException;

    void deleteStudent(int var1) throws RemoteException;

    List<Student> getLosers() throws SQLException, RemoteException;

    void deleteLosers() throws SQLException, RemoteException;

    List<Exam> getAllExams() throws RemoteException;
}
