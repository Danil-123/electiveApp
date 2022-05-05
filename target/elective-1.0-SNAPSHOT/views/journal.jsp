<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <title>journal</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-row-padding w3-panel w3-border w3-round-xxlarge">
    <p>
    <div class="w3-bar w3-padding">
        <c:forEach items="${students}" var="student">
            <form method="POST" action="/controller">
                <button type="button"
                        class="w3-btn w3-black w3-opacity w3-round-xxlarge">${student.name} ${student.surname}</button>
                <input type="hidden" name="command" value="set_mark"/>
                <input type="hidden" name="id_student" value="${student.id}"/>
                <input type="hidden" name="id_course" value="${course_id}"/>
                <input type="text" name="mark" placeholder="<ctg:MyTag mark="${student.mark}"/>"/>
                <button type="submit" class="w3-btn w3-right w3-black w3-opacity w3-hover-red w3-round-large"
                ><fmt:message key="journal_jsp.ok"/>
                </button>
            </form>
        </c:forEach>

    </div>
</div>
<tf:back_to_main/>
</body>
</html>
