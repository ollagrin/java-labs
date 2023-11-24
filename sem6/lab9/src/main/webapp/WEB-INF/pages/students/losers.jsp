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
<div class="flex flex-col justify-center items-center">
    <h5 class="mt-12 text-3xl font-extrabold text-gray-900">Losers:</h5>
<%--    <c:if test="${!losers.isEmpty()}">--%>
        <c:choose>
            <c:when test="${!losers.isEmpty()}">
                <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-5">
                    <table class="w-full text-sm text-left">
                        <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                        <tr>
                            <th scope="col" class="px-6 py-3">Name</th>
                            <th scope="col" class="px-6 py-3">Surname</th>
                            <th scope="col" class="px-6 py-3">Group</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="loser" items="${losers}">
                            <tr class="bg-white border-b hover:bg-gray-50">
                                <td class="px-6 py-4">
                                    <c:out value="${loser.getName()}"/>
                                </td>
                                <td class="px-6 py-4">
                                    <c:out value="${loser.getSurname()}"/>
                                </td>
                                <td class="px-6 py-4 text-center">
                                    <c:out value="${loser.getGroupNumber()}"/>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <h5 class="mt-12 text-xl font-bold text-gray-900">Couldn't find such</h5>
            </c:otherwise>
        </c:choose>
    <div class="flow mt-10 mb-10">
        <a class="py-2.5 px-5 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                        border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4
                        focus:ring-gray-2005 mr-2 ml-2 mt-10 mb-10"
           href="<%=request.getContextPath()%>/start">Back</a>
        <a class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
            rounded-lg text-sm px-5 py-2.5 mr-2 mt-10 mb-10" href="delete?id=losers">
            Delete all losers</a>
    </div>
</div>
</body>
</html>