package com.example.lab3.service.impl.lists;

import com.example.lab3.domain.Exam;

import com.example.lab3.domain.Grade;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which contains {@link List} of <b>exams</b>.
 * @see List
 * @see StudentList
 * @see GradeList
 * @author Volha Hrynko
 * @version 0.1.1
 */
@XmlRootElement(name = "exams")
@XmlSeeAlso({Exam.class})
public class ExamList {
    List<Exam> exams;

    /**
     * Constructor - creation of a new object
     * @see ExamList#ExamList(List)
     */
    public ExamList(){}

    /**
     * Constructor - creation of a new object with concrete values
     * @param exams
     */
    public ExamList(List<Exam> exams) {
        this.exams = exams;
    }

    /**
     * Gets value of field {@link ExamList#exams}
     * @return {@link List} of {@link Exam}
     */
    public List<Exam> getExams() {
        return exams;
    }

    /**
     * Sets {@link ExamList#exams}
     * @param exams
     */
    @XmlElement(name = "exam")
    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
