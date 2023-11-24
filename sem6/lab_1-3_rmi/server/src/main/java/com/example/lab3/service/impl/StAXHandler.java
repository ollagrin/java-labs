package com.example.lab3.service.impl;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which works with database with technology <b>RMI</b> with classes {@link Student}, {@link Exam} and {@link Grade}.
 * <p>Class works with <strong>XML</strong> using <strong>StAX</strong> technology as database.</p>
 * @see com.example.lab3.service.DBHandlerInterface
 * @see com.example.lab3.service.impl.DBHandler
 * @see com.example.lab3.service.impl.JAXBHandler
 * @author Volha Hrynko
 * @version 0.1.1
 */
public class StAXHandler extends UnicastRemoteObject implements DBHandlerInterface {

    private static final String XML_PATH_EXAMS = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\StAX\\exams.xml";
    private static final String XML_PATH_GRADES = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\StAX\\grades.xml";
    private static final String XML_PATH_STUDENTS = "C:\\Users\\USER\\OneDrive\\Рабочий стол\\учеба\\3 курс\\6 сем\\сак\\lab3-rmi\\server\\src\\main\\resources\\StAX\\students.xml";

    private final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

    /**
     * Constructor - creation of a new object
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    public StAXHandler() throws RemoteException {
    }


    /**
     * Parses students data from XML file
     * @param fileName
     * @return {@link List} of {@link Student}
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    private List<Student> parseStudents(String fileName) throws FileNotFoundException, XMLStreamException {
        List<Student> studentsList = new ArrayList<>();

        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

        Student student = null;

        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();

            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("student")) {
                    student = new Student(0, "", "", 0);
                    Attribute attribute = startElement.getAttributeByName(new QName("id"));
                    if (attribute != null) {
                        student.setId(Integer.parseInt(attribute.getValue()));
                    }
                } else if (startElement.getName().getLocalPart().equals("name")) {
                    xmlEvent = reader.nextEvent();
                    student.setName(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("surname")) {
                    xmlEvent = reader.nextEvent();
                    student.setSurname(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("groupNumber")) {
                    xmlEvent = reader.nextEvent();
                    student.setGroupNumber(Integer.parseInt(xmlEvent.asCharacters().getData()));
                }
            }

            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("student")) {
                    studentsList.add(student);
                }
            }
        }
        return studentsList;
    }

    /**
     * Parses exams data from XML file
     * @param fileName
     * @return {@link List} of {@link Exam}
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    private List<Exam> parseExams(String fileName) throws FileNotFoundException, XMLStreamException {
        List<Exam> examsList = new ArrayList<>();

        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        Exam exam = null;
        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();

            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("exam")) {
                    Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                    exam = new Exam(0, "");
                    if (idAttr != null) {
                        exam.setId(Integer.parseInt(idAttr.getValue()));
                    }
                } else if (startElement.getName().getLocalPart().equals("examName")) {
                    xmlEvent = reader.nextEvent();
                    exam.setExamName(xmlEvent.asCharacters().getData());
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("exam")) {
                    examsList.add(exam);
                }
            }
        }
        return examsList;
    }

    /**
     * Parses grades data from XML file
     * @param fileName
     * @return {@link List} of {@link Grade}
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    private List<Grade> parseGrades(String fileName) throws FileNotFoundException, XMLStreamException {
        List<Grade> gradesList = new ArrayList<>();
        Grade grade = null;
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

        while (reader.hasNext()) {
            XMLEvent xmlEvent = reader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("grade")) {
                    Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                    grade = new Grade(0, 0, 0, 0);
                    if (idAttr != null) {
                        grade.setId(Integer.parseInt(idAttr.getValue()));
                    }
                } else if (startElement.getName().getLocalPart().equals("studentId")) {
                    xmlEvent = reader.nextEvent();
                    grade.setStudentId(Integer.parseInt(xmlEvent.asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("examId")) {
                    xmlEvent = reader.nextEvent();
                    grade.setExamId(Integer.parseInt(xmlEvent.asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("mark")) {
                    xmlEvent = reader.nextEvent();
                    grade.setMark(Integer.parseInt(xmlEvent.asCharacters().getData()));
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals("grade")) {
                    gradesList.add(grade);
                }
            }
        }
        return gradesList;
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
            List<Student> studentsList = parseStudents(XML_PATH_STUDENTS);
            int j = 0;
            while (true) {
                int curr_i = i;
                j = 0;
                for (Student student : studentsList) {
                    j++;
                    if (student.getId() == i) {
                        i++;
                        break;
                    }
                }
                if (curr_i == i && j == studentsList.size())
                    break;
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
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
            List<Grade> gradesList = parseGrades(XML_PATH_GRADES);
            int j = 0;
            while (true) {
                int curr_i = i;
                j = 0;
                for (Grade grade : gradesList) {
                    j++;
                    if (grade.getId() == i) {
                        i++;
                        break;
                    }
                }
                if (curr_i == i && j == gradesList.size())
                    break;
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
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
        List<Student> studentsList;
        try {
            studentsList = parseStudents(XML_PATH_STUDENTS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }

    /**
     * Gets list of all grades which are contained in database
     * @return {@link List} of {@link Grade}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Grade> getAllGrades() throws RemoteException {
        List<Grade> gradesList;
        List<Student> studentsList;
        List<Exam> examsList;
        try {
            studentsList = parseStudents(XML_PATH_STUDENTS);
            gradesList = parseGrades(XML_PATH_GRADES);
            examsList = parseExams(XML_PATH_EXAMS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        List<Grade> grades = new ArrayList<>();

        for (Grade grade : gradesList) {
            Grade newGrade = new Grade("", "", 0, "", 0);
            newGrade.setStudentName(
                    studentsList.stream().
                            filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getName());
            newGrade.setStudentSurname(
                    studentsList.stream().
                            filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getSurname());
            newGrade.setGroupNumber(studentsList.stream().
                    filter(student -> (student.getId() == grade.getStudentId())).toList().get(0).getGroupNumber());
            newGrade.setExam(examsList.stream().
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
        List<Exam> examsList = null;
        try {
            examsList = parseExams(XML_PATH_EXAMS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return examsList;
    }

    /**
     * Adds new student in database
     * @param student
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Student
     */
    @Override
    public void addStudent(Student student) throws RemoteException {
        List<Student> studentsList;
        try {
            studentsList = parseStudents(XML_PATH_STUDENTS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        studentsList.add(student);

        try {
            FileOutputStream out = new FileOutputStream(XML_PATH_STUDENTS);

            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("students");
            for (Student stud : studentsList) {

                writer.writeStartElement("student");
                writer.writeAttribute("id", Integer.toString(stud.getId()));
                writer.writeStartElement("name");
                writer.writeCharacters(stud.getName());
                writer.writeEndElement();
                writer.writeStartElement("surname");
                writer.writeCharacters(stud.getSurname());
                writer.writeEndElement();
                writer.writeStartElement("groupNumber");
                writer.writeCharacters(Integer.toString(stud.getGroupNumber()));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (XMLStreamException e) {
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
        List<Grade> gradesList;
        List<Exam> examsList;
        try {
            gradesList = parseGrades(XML_PATH_GRADES);
            examsList = parseExams(XML_PATH_EXAMS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        Grade newGrade = new Grade(
                grade.getId(),
                grade.getStudentId(),
                examsList.stream().filter((exam) -> exam.getId() == grade.getExamId()).toList().get(0).getId(),
                grade.getMark()
        );
        System.out.println(grade.getId() + " " + grade.getStudentId() + " " + examsList.stream().filter((exam) -> exam.getId() == grade.getExamId()).toList().get(0).getId() + " " + grade.getMark());
        gradesList.add(newGrade);

        try {
            FileOutputStream out = new FileOutputStream(XML_PATH_GRADES);

            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("grades");
            for (Grade gr : gradesList) {
                writer.writeStartElement("grade");
                writer.writeAttribute("id", Integer.toString(gr.getId()));
                writer.writeStartElement("studentId");
                writer.writeCharacters(Integer.toString(gr.getStudentId()));
                writer.writeEndElement();
                writer.writeStartElement("examId");
                writer.writeCharacters(Integer.toString(gr.getExamId()));
                writer.writeEndElement();
                writer.writeStartElement("mark");
                writer.writeCharacters(Integer.toString(gr.getMark()));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();


        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (XMLStreamException e) {
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
        List<Student> studentsList;
        try {
            studentsList = parseStudents(XML_PATH_STUDENTS);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < studentsList.size(); i++) {
            if (studentsList.get(i).getId() == id) {
                studentsList.remove(i);
            }
        }
        deleteGradeByStudentId(id);
        try {
            FileOutputStream out = new FileOutputStream(XML_PATH_STUDENTS);

            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("students");
            for (Student student : studentsList) {

                writer.writeStartElement("student");
                writer.writeAttribute("id", Integer.toString(student.getId()));
                writer.writeStartElement("name");
                writer.writeCharacters(student.getName());
                writer.writeEndElement();
                writer.writeStartElement("surname");
                writer.writeCharacters(student.getSurname());
                writer.writeEndElement();
                writer.writeStartElement("groupNumber");
                writer.writeCharacters(Integer.toString(student.getGroupNumber()));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();


            writer.flush();
            writer.close();


        } catch (IOException | XMLStreamException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Deletes grade from database by its student id
     * @param id
     */
    private void deleteGradeByStudentId(int id) {
        List<Grade> gradesList = null;
        try {
            gradesList = parseGrades(XML_PATH_GRADES);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }

        if (gradesList.stream().filter(gr -> gr.getStudentId() == id).toList().isEmpty()) {
            return;
        }

        for (int i = 0; i < gradesList.size(); i++) {
            if (gradesList.get(i).getStudentId() == id) {
                gradesList.remove(i);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(XML_PATH_GRADES);

            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(out);

            writer.writeStartDocument("utf-8", "1.0");
            writer.writeStartElement("grades");
            for (Grade gr : gradesList) {
                writer.writeStartElement("grade");
                writer.writeAttribute("id", Integer.toString(gr.getId()));
                writer.writeStartElement("studentId");
                writer.writeCharacters(Integer.toString(gr.getStudentId()));
                writer.writeEndElement();
                writer.writeStartElement("examId");
                writer.writeCharacters(Integer.toString(gr.getExamId()));
                writer.writeEndElement();
                writer.writeStartElement("mark");
                writer.writeCharacters(Integer.toString(gr.getMark()));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

        } catch (IOException | XMLStreamException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Gets list of all students which are contained in database that have failed their exams
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Student> getLosers() throws SQLException, RemoteException {
        List<Student> studentsList = getAllStudents();
        List<Grade> gradesList = null;
        try {
            gradesList = parseGrades(XML_PATH_GRADES);
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> ides = new ArrayList<>();
        for (Grade gr : gradesList) {
            if (!studentsList.
                    stream().
                    filter(student -> student.getId() == gr.getStudentId()).
                    toList().
                    isEmpty()
                    && gr.getMark() < 4) {
                ides.add(gr.getStudentId());
            }
        }

        ArrayList<Student> losers = new ArrayList<>();
        for (Integer id : ides) {
            for (Student stud : studentsList) {
                if (stud.getId() == id) {
                    losers.add(stud);
                }
            }
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
