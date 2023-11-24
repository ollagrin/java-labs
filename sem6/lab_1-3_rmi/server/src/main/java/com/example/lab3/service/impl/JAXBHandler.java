package com.example.lab3.service.impl;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;
import com.example.lab3.service.impl.lists.ExamList;
import com.example.lab3.service.impl.lists.GradeList;
import com.example.lab3.service.impl.lists.StudentList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class which works with database with technology <b>RMI</b> with classes {@link Student}, {@link Exam} and {@link Grade}.
 * <p>Class works with <strong>XML</strong> using <strong>JAXB</strong> technology as database.</p>
 * @see com.example.lab3.service.DBHandlerInterface
 * @see com.example.lab3.service.impl.DBHandler
 * @see com.example.lab3.service.impl.StAXHandler
 * @author Volha Hrynko
 * @version 0.1.1
 */
public class JAXBHandler extends UnicastRemoteObject implements DBHandlerInterface {

    private static final String XML_PATH_EXAMS = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\JAXB\\exams.xml";
    private static final String XML_PATH_GRADES = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\JAXB\\grades.xml";
    private static final String XML_PATH_STUDENTS = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\JAXB\\students.xml";

    /**
     * Constructor - creation of a new object
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    public JAXBHandler() throws RemoteException {
    }

    /**
     * Converts Java Object to XML
     * @param fileName
     * @param obj
     * @throws JAXBException
     * @throws IOException
     */
    private void marshal(String fileName, Object obj) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(obj, new File(fileName));
    }

    /**
     * Converts XML to Java Object
     * @param fileName
     * @param currClass
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    private Object unmarshal(String fileName, Class<?> currClass) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(currClass);
        return context.createUnmarshaller()
                .unmarshal(new File(fileName));
    }

    /**
     * Gets available id for new element (<strong>student</strong>) in database
     * @return int
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public int getNewId() throws SQLException, RemoteException {
        int i = 1;
        try {
            StudentList studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
            int j = 0;
            while (true) {
                int curr_i = i;
                j = 0;
                for (Student student : studentList.getStudents()) {
                    j++;
                    if (student.getId() == i) {
                        i++;
                        break;
                    }
                }
                if (curr_i == i && j == studentList.getStudents().size())
                    break;
            }
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    /**
     * Gets available id for new element (<strong>grade</strong>) in database
     * @return int
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public int getNewIdGrade() throws SQLException, RemoteException {
        int i = 1;
        try {
            GradeList gradeList = (GradeList) unmarshal(XML_PATH_GRADES, GradeList.class);
            int j = 0;
            while (true) {
                int curr_i = i;
                j = 0;
                for (Grade grade : gradeList.getGrades()) {
                    j++;
                    if (grade.getId() == i) {
                        i++;
                        break;
                    }
                }
                if (curr_i == i && j == gradeList.getGrades().size())
                    break;
            }
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    /**
     * Gets list of all students which are contained in database
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Student> getAllStudents() throws RemoteException {
        StudentList studentList;
        try {
            studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return studentList.getStudents();
    }

    /**
     * Gets list of all grades which are contained in database
     * @return {@link List} of {@link Grade}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Grade> getAllGrades() throws RemoteException {
        GradeList gradeList = null;
        ExamList examList = null;
        StudentList studentList = null;
        try {
            gradeList = (GradeList) unmarshal(XML_PATH_GRADES, GradeList.class);
            examList = (ExamList) unmarshal(XML_PATH_EXAMS, ExamList.class);
            studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        List<Grade> grades = new ArrayList<>();

        for (Grade grade : gradeList.getGrades()) {
            Grade newGrade = new Grade("", "", 0, "", 0);
            newGrade.setStudentName(
                    studentList.getStudents().stream().
                            filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getName());
            newGrade.setStudentSurname(
                    studentList.getStudents().stream().
                            filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getSurname());
            newGrade.setGroupNumber(studentList.getStudents().stream().
                    filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getGroupNumber());
            newGrade.setExam(examList.getExams().stream().
                    filter(exam -> (exam.getId() == grade.getExamId())).toList().get(0).getExamName());
            newGrade.setMark(grade.getMark());
            grades.add(newGrade);
        }

        return grades;
    }

    /**
     * Gets list of all exams which are contained in database
     * @return {@link List} of {@link Exam}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Exam> getAllExams() throws RemoteException {
        ExamList examList = null;
        try {
            examList = (ExamList) unmarshal(XML_PATH_EXAMS, ExamList.class);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return examList.getExams();
    }

    /**
     * Adds new student in database
     * @param student
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Student
     */
    @Override
    public void addStudent(Student student) throws RemoteException {
        try {
            StudentList studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
            studentList.getStudents().add(student);
            marshal(XML_PATH_STUDENTS, studentList);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Adds new exam to database
     * @param exam
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Exam
     */
    @Override
    public void addExam(Exam exam) throws RemoteException {
    }

    /**
     * Adds new grade to database
     * @param grade
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Grade
     */
    @Override
    public void addGrade(Grade grade) throws RemoteException {
        try {
            GradeList gradeList = (GradeList) unmarshal(XML_PATH_GRADES, GradeList.class);
            List<Grade> tempGrade = gradeList.
                    getGrades().
                    stream().
                    filter(gr -> (grade.getStudentId() == gr.getStudentId() && grade.getExamId() == gr.getExamId())).
                    toList();
            List<Grade> list = gradeList.getGrades();
            if(!tempGrade.isEmpty()){
                list.remove(tempGrade.get(0));
            }
            list.add(grade);
            gradeList.setGrades(list);
            marshal(XML_PATH_GRADES, gradeList);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes student from database by its id
     * @param id
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public void deleteStudent(int id) throws RemoteException {
        try {
            StudentList studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
            studentList.setStudents(
                    studentList.getStudents().
                            stream().
                            filter(student -> student.getId() != id).
                            toList());
            deleteGradeByStudentId(id);
            marshal(XML_PATH_STUDENTS, studentList);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Deletes grade from database by its student id
     * @param id
     */
    private void deleteGradeByStudentId(int id) {
        GradeList gradesList = null;
        try {
            gradesList = (GradeList) unmarshal(XML_PATH_GRADES, GradeList.class);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }

        if (gradesList.getGrades().stream().filter(gr -> gr.getStudentId() == id).toList().isEmpty()) {
            return;
        }
        gradesList.setGrades(
                gradesList.getGrades().
                        stream().
                        filter(gr -> gr.getStudentId() != id).
                        toList());
        try {
            marshal(XML_PATH_GRADES, gradesList);
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets list of all students which are contained in database that have failed their exams
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Student> getLosers() throws SQLException, RemoteException {
        ArrayList<Student> losers;
        try {
            StudentList studentList = (StudentList) unmarshal(XML_PATH_STUDENTS, StudentList.class);
            GradeList gradeList = (GradeList) unmarshal(XML_PATH_GRADES, GradeList.class);
            ArrayList<Integer> ides = new ArrayList<>();
            for (Grade gr : gradeList.getGrades()) {
                if (!studentList.getStudents().
                        stream().
                        filter(student -> student.getId() == gr.getStudentId()).
                        toList().
                        isEmpty()
                        && gr.getMark() < 4) {
                    ides.add(gr.getStudentId());
                }
            }

            losers = new ArrayList<>();
            for (Integer id : ides) {
                for (Student stud : studentList.getStudents()) {
                    if (stud.getId() == id) {
                        losers.add(stud);
                    }
                }
            }
            System.out.println(losers);

        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
        return losers;
    }

    /**
     * Deletes students from database that have failed their exams
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public void deleteLosers() throws SQLException, RemoteException {
        List<Student> losers = getLosers();
        for (Student student : losers) {
            deleteStudent(student.getId());
        }
    }
}
