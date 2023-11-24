package com.example.lab9.servlets;

import com.example.lab9.domain.Student;
import com.example.lab9.service.DBHandler;
import com.example.lab9.service.DBHandlerInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    DBHandlerInterface studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if (action.equals("/start")) {
            List<Student> students = studentService.getAllStudents();
            req.getSession().removeAttribute("student");
            req.setAttribute("students", students);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.equals("/delete")) {
            String par = req.getParameter("id");
            if (par.equals("losers")) {
                try {
                    studentService.deleteLosers();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("losers");
            } else {
                int id = Integer.parseInt(req.getParameter("id"));
                studentService.deleteStudent(id);
                resp.sendRedirect("start");
            }
        } else if (action.equals("/new")) {
            String page = req.getParameter("page");
            if (page.equals("name")) {
                Student currentStudent;
                if (req.getSession().getAttribute("student") != null) {
                    currentStudent = (Student) req.getSession().getAttribute("student");
                } else {
                    currentStudent = new Student();
                    try {
                        currentStudent.setId(studentService.getNewId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                req.getSession().setAttribute("student", currentStudent);
                req.getRequestDispatcher("WEB-INF/pages/students/name.jsp").forward(req, resp);
            } else if (page.equals("surname")) {
                Student currentStudent = (Student) req.getSession().getAttribute("student");
                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("name");
                    currentStudent.setName(inputData);
                    req.getSession().setAttribute("student", currentStudent);
                }
                req.getRequestDispatcher("WEB-INF/pages/students/surname.jsp").forward(req, resp);
            } else if (page.equals("group")) {
                Student currentStudent = (Student) req.getSession().getAttribute("student");
                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("surname");
                    currentStudent.setSurname(inputData);
                    req.getSession().setAttribute("student", currentStudent);
                }
                req.getRequestDispatcher("WEB-INF/pages/students/group.jsp").forward(req, resp);
            } else if (page.equals("finish")) {
                Student currentStudent = (Student) req.getSession().getAttribute("student");
                if (req.getParameter("edit") == null) {
                    String inputData = req.getParameter("group");
                    currentStudent.setGroupNumber(Integer.parseInt(inputData));
                    req.getSession().setAttribute("student", currentStudent);
                }
                req.getRequestDispatcher("WEB-INF/pages/students/finish.jsp").forward(req, resp);
            }
        } else if (action.equals("/losers")) {
            List<Student> losers;
            try {
                losers = studentService.getLosers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("losers", losers);
            req.getRequestDispatcher("WEB-INF/pages/students/losers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getServletPath();
        //if (action.equals("/add")) {
            HttpSession session = req.getSession();
            studentService.addStudent((Student) session.getAttribute("student"));
            session.removeAttribute("student");
            resp.sendRedirect("start");
        //}
    }

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new DBHandler();
    }
}
