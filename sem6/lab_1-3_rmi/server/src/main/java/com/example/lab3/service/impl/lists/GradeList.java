package com.example.lab3.service.impl.lists;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Class which contains {@link List} of <b>grades</b>.
 * @see List
 * @see StudentList
 * @see ExamList
 * @author Volha Hrynko
 * @version 0.1.1
 */
@XmlRootElement(name = "grades")
@XmlSeeAlso({Grade.class})
public class GradeList {

    List<Grade> grades;

    /**
     * Constructor - creation of a new object
     * @see GradeList#GradeList(List)
     */
    public GradeList(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param grades
     */
    public GradeList(List<Grade> grades) {
        this.grades = grades;
    }

    /**
     * Gets value of field {@link GradeList#grades}
     * @return {@link List} of {@link Grade}
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * Sets {@link GradeList#grades}
     * @param grades
     */
    @XmlElement(name = "grade")
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

}
