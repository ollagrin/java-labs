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
<c:import url="WEB-INF/components/navbar.jsp"/>
<%--<jsp:include page="WEB-INF/components/hat.jsp"/>--%>
<div class="flex flex-col justify-center items-center">
    <h5 class="mt-12 text-3xl font-extrabold text-gray-900">Your students here:</h5>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-5">
        <table class="w-full text-sm text-left">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50">
            <tr>
                <th scope="col" class="px-6 py-3">Name</th>
                <th scope="col" class="px-6 py-3">Surname</th>
                <th scope="col" class="px-6 py-3">Group</th>
                <th scope="col" class="px-6 py-3"></th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="students" scope="request" type="java.util.List"/>
            <c:forEach var="student" items="${students}">
                <tr class="bg-white border-b hover:bg-gray-50">
                    <td class="px-6 py-4">
                        <c:out value="${student.getName()}"/>
                    </td>
                    <td class="px-6 py-4">
                        <c:out value="${student.getSurname()}"/>
                    </td>
                    <td class="px-6 py-4 text-center">
                        <c:out value="${student.getGroupNumber()}"/>
                    </td>
                    <td class="px-6 py-4">
                        <a href="delete?id=<c:out value='${student.getId()}' />"
                           class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="flow mt-10 mb-10">
        <a class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
            rounded-lg text-sm px-5 py-2.5 mr-2 mt-10 mb-10" href="<%=request.getContextPath()%>/new?page=name">Add new
        student</a>
        <a class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
            rounded-lg text-sm px-5 py-2.5 mr-2 ml-2 mt-10 mb-10" href="<%=request.getContextPath()%>/losers">Show losers</a>
    </div>

</div>
</body>
</html>