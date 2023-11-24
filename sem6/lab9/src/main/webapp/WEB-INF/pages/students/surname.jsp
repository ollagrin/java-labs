<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
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
    <div class="w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-6 md:p-8">
        <form class="space-y-6">
            <h5 class="text-2xl font-medium text-gray-900">Add new student</h5>
            <form action="new" method="GET">
                <input type="hidden" name="page" value="group"/>
                <div>
                    <label for="default-input" class="block mb-2 text-sm font-medium text-gray-900">
                        Enter surname of student</label>
                    <jsp:useBean id="student" class="com.example.lab9.domain.Student" scope="session"/>
                    <input type="text"
                           id="default-input"
                           value="<c:out value='${student.surname}' />"
                           name="surname"
                           class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500
                           focus:border-blue-500 block w-full p-2.5"
                           required>
                    <div class="flow mt-6 justify-center items-center">
                        <button type="submit"
                                class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300
                                font-medium rounded-lg text-sm px-5 py-2.5 mr-2">
                            Next
                        </button>
                    </div>
                </div>
            </form>
            <form action="new" method="GET">
                <input type="hidden" name="page" value="name"/>
                <input type="hidden" name="edit" value="false"/>
                <button type="submit"
                        class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
                    Back
                </button>
            </form>
        </form>
    </div>
</div>
</body>
</html>