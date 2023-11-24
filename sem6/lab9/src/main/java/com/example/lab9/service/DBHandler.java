package com.example.lab9.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.lab9.domain.Exam;
import com.example.lab9.domain.Grade;
import com.example.lab9.domain.Student;

public class DBHandler implements DBHandlerInterface {
    private static final String DB_NAME = "postgres";
    private static final String DB_PASSWORD = "2043nw";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/test";

    private Connection connection;

    public DBHandler() {
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

    @Override
    public int getNewId() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id=?");
        int i = 1;
        preparedStatement.setInt(1, i);
        while (preparedStatement.executeQuery().next()) {
            i++;
            preparedStatement.setInt(1, i);
        }
        return i;
    }

    @Override
    public int getNewIdGrade() throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM grades WHERE id=?");
        int i = 1;
        preparedStatement.setInt(1, i);
        while (preparedStatement.executeQuery().next()) {
            i++;
            preparedStatement.setInt(1, i);
        }
        return i;
    }

    @Override
    public List<Student> getAllStudents() {
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

    @Override
    public List<Grade> getAllGrades() {
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

    @Override
    public List<Exam> getAllExams() {
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

    @Override
    public void addStudent(Student student){
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

    @Override
    public void addExam(Exam exam){
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

    @Override
    public void addGrade(Grade grade){
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO grades VALUES(?, ?, ?, ?)");
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

    @Override
    public void deleteStudent(int id){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Student> getLosers() throws SQLException{
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

    @Override
    public void deleteLosers() throws SQLException{
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
