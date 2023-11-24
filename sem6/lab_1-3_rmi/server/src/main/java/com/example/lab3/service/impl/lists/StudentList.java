package com.example.lab3.service.impl.lists;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Student;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Class which contains {@link List} of <b>students</b>.
 * @see List
 * @see ExamList
 * @see GradeList
 * @author Volha Hrynko
 * @version 0.1.1
 */
@XmlRootElement(name = "students")
@XmlSeeAlso({Student.class})
public class StudentList {
    List<Student> students;
    /**
     * Constructor - creation of a new object
     * @see StudentList#StudentList(List)
     */
    public StudentList(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param students
     */
    public StudentList(List<Student> students) {
        this.students = students;
    }

    /**
     * Gets value of field {@link StudentList#students}
     * @return {@link List} of {@link Student}
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets {@link StudentList#students}
     * @param students
     */
    @XmlElement(name = "student")
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
