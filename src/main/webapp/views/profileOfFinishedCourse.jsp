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
<div class="w3-row-padding w3-grey  w3-padding">
    <div class="w3-col s9 w3-grey w3-left-align w3-padding">
        <fmt:message key="profileOfCourse_jsp.Course"/> ${course.course_name}<br>
        <fmt:message key="profileOfCourse_jsp.start_day"/>${course.start_day}<br>
        <fmt:message key="profileOfCourse_jsp.duration"/>${course.duration}<br>
        <fmt:message key="profileOfCourse_jsp.teacher"/>${teacher.name} ${teacher.surname}<br>
        <fmt:message key="profileOfCourse_jsp.num_of_student"/>${numOfStudent}
        <div class="w3-text-red"><h2><fmt:message key="coursesByStudent_jsp.mark"/> ${mark}</h2></div>
        <c:if test="${sessionScope.user.role == 'ADMIN' }">
            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="detail_of_course"/>
                <input type="hidden" name="id" value="${course.id}"/>
                <button type="submit" class="w3-btn w3-black w3-opacity w3-hover-red w3-round-large"
                ><fmt:message key="profileOfCourse_jsp.detail"/>
                </button>
            </form>
        </c:if>
    </div>
</div>
<div class="w3-container w3-grey  w3-left-align w3-padding">
    <fmt:message key="profileOfCourse_jsp.description"/>${course.description}
</div>
<br>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <tf:back_to_main/>
</div>
</body>
</html>
