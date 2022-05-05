<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<c:forEach items="${listOfCourses}" var="course">
    <form method="POST" action="/controller">
        <input type="hidden" name="command" value="profile_course"/>
        <input type="hidden" name="id" value="${course.id}"/>
        <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
        </button>
        <br><fmt:message key="profileOfCourse_jsp.start_day"/>${course.start_day}
    </form>
</c:forEach>
<tf:back_to_main/>
</body>
</html>
