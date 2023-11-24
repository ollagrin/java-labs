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
<%--<nav style="background-color: black">--%>
<%--    <div class="text-center text-2xl p-4" style="color: azure">Students Info</div>--%>
<%--</nav>--%>
<c:import url="/WEB-INF/components/navbar.jsp"/>
<div class="flex flex-col justify-center items-center">
    <h5 class="mt-12 text-3xl font-extrabold text-gray-900">Your grades here:</h5>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-5">
        <table class="w-full text-sm text-left">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">Name</th>
                <th scope="col" class="px-6 py-3">Surname</th>
                <th scope="col" class="px-6 py-3">Group</th>
                <th scope="col" class="px-6 py-3">Exam</th>
                <th scope="col" class="px-6 py-3">Mark</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="grade" items="${grades}">
                <tr class="bg-white border-b hover:bg-gray-50">
                    <td class="px-6 py-4">
                        <c:out value="${grade.getStudentName()}"/>
                    </td>
                    <td class="px-6 py-4">
                        <c:out value="${grade.getStudentSurname()}"/>
                    </td>
                    <td class="px-6 py-4 text-center">
                        <c:out value="${grade.getGroupNumber()}"/>
                    </td>
                    <td class="px-6 py-4 text-center">
                        <c:out value="${grade.getExam()}"/>
                    </td>
                    <td class="px-6 py-4 text-center">
                        <c:out value="${grade.getMark()}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
            rounded-lg text-sm px-5 py-2.5 mr-2 mt-10 mb-10" href="<%=request.getContextPath()%>/grades?page=new">Add new
        grade</a>
</div>
</body>
</html>