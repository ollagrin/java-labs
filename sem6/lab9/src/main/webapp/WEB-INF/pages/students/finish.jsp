<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix = "ex" uri = "/WEB-INF/customTags/info.tld"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Service</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body style="background: linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c);">
<c:import url="/WEB-INF/components/navbar.jsp"/>

<div class="flex flex-col justify-center items-center mt-20">
    <div class="max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow">
        <h5 class="mb-6 text-2xl font-bold tracking-tight text-gray-900">
            You complete adding student to database!</h5>
        <jsp:useBean id="student" scope="session" type="com.example.lab9.domain.Student"/>
        <ex:infoTag student="${student}">
            Your new student:
        </ex:infoTag>
        <div class="flex mt-5">

            <form action="new" method="GET">
                <input type="hidden" name="page" value="group"/>
                <input type="hidden" name="edit" value="false"/>
                <button type="submit"
                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500
                           focus:border-blue-500 block w-full p-2.5 mr-2">Back
                </button>
            </form>
            <form action="add" method="POST">
                <button type="submit"
                        class="button text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium
                rounded-lg text-sm px-5 py-2.5 ml-2 ">
                    Add
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>