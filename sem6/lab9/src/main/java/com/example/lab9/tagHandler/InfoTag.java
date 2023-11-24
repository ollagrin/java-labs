package com.example.lab9.tagHandler;

import com.example.lab9.domain.Student;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class InfoTag extends SimpleTagSupport {
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
    }

    StringWriter sw = new StringWriter();

    public void doTag() throws IOException, JspException {
        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(sw);
        StringBuilder stringBuilder = new StringBuilder(sw.toString()).
                append(student.getName()).
                append(" ").
                append(student.getSurname()).
                append(" ").
                append("group №").
                append(student.getGroupNumber());
        out.println("<p class=\"text-red-600\">" +
                "<i>" + stringBuilder + "</i>" +
                "</p>");
    }
}
