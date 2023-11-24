<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Service</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body style="background: linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c);">
<c:import url="/WEB-INF/components/navbar.jsp"/>

<div class="flex flex-col justify-center items-center mt-20">
    <div class="w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700">
        <form class="space-y-6">
            <h5 class="text-2xl font-medium text-gray-900">Add new grade</h5>
            <form action="grades" method="GET">
                <input type="hidden" name="page" value="finish"/>
                <div>
                    <label for="students" class="block mb-2 text-sm font-medium text-gray-900">Select an option</label>
                    <select id="students" name="studentId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg
                    focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" required>
                        <option selected disabled>Choose a student</option>
                        <c:forEach var="student" items="${students}">
                            <option value="<c:out value='${student.getId()}' />">
                                <c:out value="${student.toString()}"/>
                            </option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="exams" class="block mb-2 text-sm font-medium text-gray-900">
                        Select an option
                    </label>
                    <select id="exams" name="examId" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg
                    focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5" required>
                        <option selected disabled>Choose an exam</option>
                        <c:forEach var="exam" items="${exams}">
                            <option value="<c:out value='${exam.getId()}' />">
                                <c:out value="${exam.getExamName()}"/>
                            </option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="default-input" class="block mb-2 text-sm font-medium text-gray-900">
                        Enter mark
                    </label>
                    <jsp:useBean id="grade" scope="session" class="com.example.lab9.domain.Grade"/>
                    <input type="text"
                           id="default-input"
                           value="<c:out value='${grade.mark}' />"
                           name="mark"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500
                           focus:border-blue-500 block w-full p-2.5"
                           required>
                    <button type="submit"
                            class="button mt-6 text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
                            rounded-lg text-sm px-5 py-2.5 mr-2 focus:outline-none ">
                        Add
                    </button>
                </div>
            </form>
            <a type="submit"
               class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                        border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4
                        focus:ring-gray-200"
               href="${pageContext.request.contextPath}/grades?page=show">
                Back
            </a>
        </form>
    </div>
</div>
</body>
</html>
