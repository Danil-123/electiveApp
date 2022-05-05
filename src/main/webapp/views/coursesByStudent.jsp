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
<div class="w3-row  w3-grey">
    <div class="w3-col s3 w3-opacity   w3-left-align w3-padding">
        <fmt:message key="coursesByStudent_jsp.all_courses"/>
        <c:forEach items="${listOfCourses}" var="course">
            <div class="w3-container   w3-left-align w3-padding">
                <form method="POST" action="/controller">
                    <input type="hidden" name="command" value="profile_course"/>
                    <input type="hidden" name="id" value="${course.id}"/>
                    <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
                    </button>
                    <br><fmt:message key="coursesByStudent_jsp.date_start_of_course"/>${course.start_day}
                </form>
            </div>
            <br>
        </c:forEach>
    </div>
    <div class="w3-col s3  w3-opacity   w3-left-align w3-padding">
        <fmt:message key="coursesByStudent_jsp.started_courses"/>
        <c:forEach items="${listOfStartedCourses}" var="course">
            <div class="w3-container   w3-left-align w3-padding">
                <form method="POST" action="/controller">
                    <input type="hidden" name="command" value="profile_course"/>
                    <input type="hidden" name="id" value="${course.id}"/>
                    <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
                    </button>
                    <br><fmt:message key="coursesByStudent_jsp.date_start_of_course"/>${course.start_day}
                </form>
            </div>
            <br>
        </c:forEach>
    </div>
    <div class="w3-col s3 w3-opacity  w3-left-align w3-padding">
        <fmt:message key="coursesByStudent_jsp.not_started_courses"/>
        <c:forEach items="${listOfNotStartedCourses}" var="course">
            <div class="w3-container   w3-left-align w3-padding">
                <form method="POST" action="/controller">
                    <input type="hidden" name="command" value="profile_course"/>
                    <input type="hidden" name="id" value="${course.id}"/>
                    <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
                    </button>
                    <br><fmt:message key="coursesByStudent_jsp.date_start_of_course"/>${course.start_day}
                </form>
            </div>
            <br>
        </c:forEach>
    </div>
    <div class="w3-col s3 w3-opacity  w3-left-align w3-padding">
        <fmt:message key="coursesByStudent_jsp.finished_courses"/>
        <c:forEach items="${FinishedCourses}" var="course">
            <div class="w3-container   w3-left-align w3-padding">
                <form method="POST" action="/controller">
                    <input type="hidden" name="command" value="profile_finished_course"/>
                    <input type="hidden" name="id_course" value="${course.id}"/>
                    <input type="hidden" name="id_student" value="${id_student}"/>
                    <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
                    </button>
                    <br><fmt:message key="coursesByStudent_jsp.date_start_of_course"/>${course.start_day}
                    <br><fmt:message key="coursesByStudent_jsp.last_day_of_course"/>: ${course.last_day}
                </form>
            </div>
            <br>
        </c:forEach>
    </div>
</div>
</div>
</div>
<tf:back_to_main/>
</div>
</body>
</html>
