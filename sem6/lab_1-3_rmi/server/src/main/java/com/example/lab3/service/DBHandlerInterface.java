package com.example.lab3.service;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import com.example.lab3.service.impl.DBHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface to work with database with technology <b>RMI</b> with classes {@link Student}, {@link Exam} and {@link Grade}.
 * <p>This interface has 3 classes that implements it.</p>
 * <p>List of them: </p>
 * <ul>
 *     <li>{@link com.example.lab3.service.impl.DBHandler} which works with data that stores in <strong>PostgreSQL</strong></li>
 *     <li>{@link com.example.lab3.service.impl.StAXHandler} which works with data that stores in <strong>XML files</strong>
 *     <p>using <b>StAX</b> technology to work with them</p></li>
 *     <li>{@link com.example.lab3.service.impl.JAXBHandler} which works with data that stores in <strong>XML files</strong>
 *     <p>using <b>JAXB</b> technology to work with them</p></li>
 *
 * </ul>
 * @see java.rmi.Remote
 * @see java.sql
 * @author Volha Hrynko
 * @version 0.1.1
 */

public interface DBHandlerInterface extends Remote {

    /**
     * Gets available id for new element (<strong>student</strong>) in database
     * @return int
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    int getNewId() throws SQLException, RemoteException;

    /**
     * Gets available id for new element (<strong>grade</strong>) in database
     * @return int
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    int getNewIdGrade() throws SQLException, RemoteException;

    /**
     * Gets list of all students which are contained in database
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    List<Student> getAllStudents() throws RemoteException;

    /**
     * Gets list of all grades which are contained in database
     * @return {@link List} of {@link Grade}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    List<Grade> getAllGrades() throws RemoteException;

    /**
     * Gets list of all exams which are contained in database
     * @return {@link List} of {@link Exam}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    List<Exam> getAllExams() throws RemoteException;

    /**
     * Adds new student in database
     * @param student
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Student
     */
    void addStudent(Student student) throws RemoteException;

    /**
     * Adds new exam to database
     * @param exam
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Exam
     */
    void addExam(Exam exam) throws RemoteException;

    /**
     * Adds new grade to database
     * @param grade
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Grade
     */
    void addGrade(Grade grade) throws RemoteException;

    /**
     * Deletes student from database by its id
     * @param id
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    void deleteStudent(int id) throws RemoteException;

    /**
     * Gets list of all students which are contained in database that have failed their exams
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    List<Student> getLosers() throws SQLException, RemoteException;

    /**
     * Deletes students from database that have failed their exams
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    void deleteLosers() throws SQLException, RemoteException;

}
