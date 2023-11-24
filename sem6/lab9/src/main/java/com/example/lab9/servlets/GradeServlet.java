package com.example.lab9.servlets;

import com.example.lab9.domain.Grade;
import com.example.lab9.service.DBHandler;
import com.example.lab9.service.DBHandlerInterface;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

public class GradeServlet extends HttpServlet {
    DBHandlerInterface dbHandler;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("page");
        if (action.equals("show")) {
            List<Grade> grades = dbHandler.getAllGrades();
            request.setAttribute("grades", grades);
            request.getRequestDispatcher("WEB-INF/pages/grades/table.jsp").forward(request, response);
        } else if (action.equals("new")) {
            Grade currGrade;
            if (request.getSession().getAttribute("grade") != null) {
                currGrade = (Grade) request.getSession().getAttribute("grade");
            } else {
                currGrade = new Grade();
                try {
                    currGrade.setId(dbHandler.getNewIdGrade());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            request.getSession().setAttribute("students", dbHandler.getAllStudents());
            request.getSession().setAttribute("exams", dbHandler.getAllExams());
            request.getSession().setAttribute("grade", currGrade);
            request.getRequestDispatcher("WEB-INF/pages/grades/new.jsp").forward(request, response);
        } else if (action.equals("finish")) {
            Grade currGrade = (Grade) request.getSession().getAttribute("grade");
            if (request.getParameter("edit") == null) {
                currGrade.setStudentId(Integer.parseInt(request.getParameter("studentId")));
                currGrade.setExamId(Integer.parseInt(request.getParameter("examId")));
                currGrade.setMark(Integer.parseInt(request.getParameter("mark")));
                request.getSession().setAttribute("grade", currGrade);
            }
            request.getRequestDispatcher("WEB-INF/pages/grades/finish.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Grade grade = (Grade) session.getAttribute("grade");
        dbHandler.addGrade(grade);
        session.removeAttribute("grade");
        String path = request.getContextPath() + "/grades?page=show";
        response.sendRedirect(path);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        dbHandler = new DBHandler();
    }
}
