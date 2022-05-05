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
    <c:if test="${(sessionScope.user.role == 'STUDENT')||(sessionScope.user.role == 'ADMIN') }">
        <div class="w3-col s3 w3-grey w3-opacity w3-center w3-jumbo">
            <button onclick="document.getElementById('id01').style.display='block'"
                    class="w3-btn  w3-black w3-hover-green w3-round-large"><fmt:message
                    key="profileOfCourse_jsp.enroll"/>
            </button>
        </div>
    </c:if>
    <c:if test="${(sessionScope.user.role == 'TEACHER') }">
        <div class="w3-col s3 w3-grey w3-opacity w3-center w3-jumbo">
            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="journal"/>
                <input type="hidden" name="course_id" value="${course.id}"/>
                <button type="submit" cclass="w3-btn  w3-black w3-hover-green w3-round-large"
                ><fmt:message key="profileOfCourse_jsp.journal"/>
                </button>
            </form>
        </div>
    </c:if>
</div>
<div class="w3-container w3-grey  w3-left-align w3-padding">
    <fmt:message key="profileOfCourse_jsp.description"/>${course.description}
</div>
<br>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <tf:back_to_main/>
    <!-- Модальное окно -->
    <div id="id01" class="w3-modal">
        <div class="w3-modal-content w3-animate-top">
            <div class="w3-container">
      <span onclick="document.getElementById('id01').style.display='none'"
            class="w3-button w3-display-topright">&times;</span>
                <div class="w3-left-align"><p>Вы будете записаны ну курс ${course.course_name}</p></div>
                <br>
                <p>
                <form method="POST" action="/controller">
                    <input type="hidden" name="command" value="enroll_to_course"/>
                    <input type="hidden" name="course_id" value="${course.id}"/>
                    <input type="hidden" name="student_id" value="${sessionScope.user.id}"/>
                    <button type="submit" class="w3-btn w3-black w3-hover-green w3-round-large"><fmt:message
                            key="profileOfCourse_jsp.confirm"/>
                    </button>
                </form>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
