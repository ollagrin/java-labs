package com.example.lab3.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.lab3.domain.Exam;
import com.example.lab3.domain.Grade;
import com.example.lab3.domain.Student;
import com.example.lab3.service.DBHandlerInterface;


/**
 * Class which works with database with technology <b>RMI</b> with classes {@link Student}, {@link Exam} and {@link Grade}.
 * <p>Class works with <strong>PostgreSQL</strong> as database.</p>
 * @see com.example.lab3.service.DBHandlerInterface
 * @see com.example.lab3.service.impl.StAXHandler
 * @see com.example.lab3.service.impl.JAXBHandler
 * @author Volha Hrynko
 * @version 0.1.1
 */
public class DBHandler extends UnicastRemoteObject implements DBHandlerInterface {
    private static final String DB_NAME = "postgres";
    private static final String DB_PASSWORD = "2043nw";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";

    private Connection connection;

    /**
     * Constructor - creation of a new object which connects to PostgreSQL with driver
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    public DBHandler() throws RemoteException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = connection;
    }

    /**
     * Gets available id for new element (<strong>student</strong>) in database
     * @return int
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public int getNewId() throws SQLException, RemoteException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id=?");
        int i = 1;
        preparedStatement.setInt(1, i);
        while (preparedStatement.executeQuery().next()) {
            i++;
            preparedStatement.setInt(1, i);
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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM grades WHERE id=?");
        int i = 1;
        preparedStatement.setInt(1, i);
        while (preparedStatement.executeQuery().next()) {
            i++;
            preparedStatement.setInt(1, i);
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
        List<Student> answer = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM students";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Student student = new Student(0, "", "", -1);

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setGroupNumber(resultSet.getInt("group_number"));

                answer.add(student);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

    /**
     * Gets list of all grades which are contained in database
     * @return {@link List} of {@link Grade}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Grade> getAllGrades() throws RemoteException {
        List<Grade> answer = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT students.name, students.surname, students.group_number FROM students " +
                    "JOIN grades on students.id = grades.studentid";
            ResultSet resultSet = statement.executeQuery(SQL);
            Statement statement2 = connection.createStatement();
            ResultSet examSet = statement2.executeQuery("SELECT exams.name FROM exams " +
                    "JOIN grades on exams.id = grades.examid");
            Statement statement3 = connection.createStatement();
            ResultSet gradeSet = statement3.executeQuery("SELECT mark FROM grades");

            while (resultSet.next() && examSet.next() && gradeSet.next()) {
                Grade grade = new Grade("", "", 0, "", -1);

                grade.setStudentName(resultSet.getString("name"));
                grade.setStudentSurname(resultSet.getString("surname"));
                grade.setGroupNumber(resultSet.getInt("group_number"));
                grade.setExam(examSet.getString("name"));
                grade.setMark(gradeSet.getInt("mark"));
//                grade.setId(resultSet.getInt("id"));
//                grade.setExamId(resultSet.getInt("examid"));
//                grade.setStudentId(resultSet.getInt("studentid"));
                //grade.setGrade(resultSet.getInt("mark"));

                answer.add(grade);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

    /**
     * Gets list of all exams which are contained in database
     * @return {@link List} of {@link Exam}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Exam> getAllExams() throws RemoteException {
        List<Exam> answer = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM exams";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Exam exam = new Exam(0, "");

                exam.setId(resultSet.getInt("id"));
                exam.setExamName(resultSet.getString("name"));

                answer.add(exam);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return answer;
    }

    /**
     * Adds new student in database
     * @param student
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Student
     */
    @Override
    public void addStudent(Student student) throws RemoteException{
        try {
            //add student
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO students VALUES(?, ?, ?, ?)");

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setInt(4, student.getGroupNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds new exam to database
     * @param exam
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Exam
     */
    @Override
    public void addExam(Exam exam) throws RemoteException{
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO exams VALUES(?, ?)");

            preparedStatement.setInt(1, exam.getId());
            preparedStatement.setString(2, exam.getExamName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds new grade to database
     * @param grade
     * @throws RemoteException - mistake in work with <b>RMI</b>
     * @see Grade
     */
    @Override
    public void addGrade(Grade grade) throws RemoteException{
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO grades VALUES(?, ?, ?, ?)");
            String exam = grade.getExam();
            Statement statement3 = connection.createStatement();
            String SQL = String.format("SELECT * FROM exams WHERE name = '%s'", exam);
            ResultSet resultSet = statement3.executeQuery(SQL);
            resultSet.next();
            preparedStatement.setInt(1, grade.getId());
//            preparedStatement.setInt(2, resultSet.getInt("id"));
            preparedStatement.setInt(2, grade.getExamId());
            preparedStatement.setInt(3, grade.getStudentId());
            preparedStatement.setInt(4, grade.getMark());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes student from database by its id
     * @param id
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public void deleteStudent(int id) throws RemoteException{
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Gets list of all students which are contained in database that have failed their exams
     * @return {@link List} of {@link Student}
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public List<Student> getLosers() throws SQLException, RemoteException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM grades WHERE mark < 4;");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id=?;");
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Student> arr = new ArrayList<>();
        while (resultSet.next()) {
            int st = resultSet.getInt(3);
            if (array.contains(st)) {
                continue;
            }
            array.add(st);
            preparedStatement.setInt(1, st);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()) {
                int id2 = resultSet1.getInt(1);
                String name = resultSet1.getString(2);
                String secondName = resultSet1.getString(3);
                int group = resultSet1.getInt(4);
                arr.add(new Student(id2, name, secondName, group));
                //System.out.printf("id-%d, firstName-%s secondName-%s, group-%d\n", id2, name, secondName, group);
            }
        }
        return arr;
    }

    /**
     * Deletes students from database that have failed their exams
     * @throws SQLException - mistake in access to database
     * @throws RemoteException - mistake in work with <b>RMI</b>
     */
    @Override
    public void deleteLosers() throws SQLException, RemoteException{
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from grades where mark < 4;");
        while (resultSet.next()) {
            PreparedStatement preparedStatement2 = connection.prepareStatement("delete from grades where studentid=?;");
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where id=?;");
            int st = resultSet.getInt(3);
            preparedStatement2.setInt(1, st);
            preparedStatement2.executeUpdate();
            preparedStatement.setInt(1, st);
            preparedStatement.executeUpdate();
            System.out.println("Losers were deleted!\n");
        }
    }
}
