<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Service</title>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body style="background: linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c);">
<c:import url="/WEB-INF/components/navbar.jsp"/>

<div id="alert-additional-content-2"
     class="flex flex-col justify-center items-center mt-20 p-4 mb-4 text-red-800 border border-red-300 rounded-lg bg-red-50"
     role="alert">
    <div class="flex items-center">
        <svg aria-hidden="true" class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20"
             xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd"
                  d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z"
                  clip-rule="evenodd"></path>
        </svg>
        <span class="sr-only">Info</span>
        <h3 class="text-lg font-medium">Wrong input!</h3>
    </div>
    <div class="mt-2 mb-4 text-sm">
        Your input must be a number.
    </div>
    <form action="new" method="GET">
        <input type="hidden" name="page" value="group"/>
        <input type="hidden" name="edit" value="false"/>
        <button type="submit"
                class="button text-red-800 bg-transparent border border-red-800 hover:bg-red-900 hover:text-white focus:ring-4
                focus:outline-none focus:ring-red-300 font-medium rounded-lg text-xs px-3 py-1.5 text-center">
            Dismiss
        </button>
    </form>
</div>
</body>
</html>