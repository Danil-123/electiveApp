<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <title>students</title>
</head>
<body>
<c:forEach items="${listOfStudent}" var="student">
    <div class="w3-row-padding w3-panel w3-border w3-round-xxlarge">
        <p>
        <div class="w3-bar w3-padding">
            <c:set var="status" value="${student.status}"/>
            <c:choose>
                <c:when test="${(status=='0')}">
                    <form method="POST" action="/controller">
                        <button type="button"
                                class="w3-btn w3-black w3-opacity w3-round-xxlarge">${student.name} ${student.surname}</button>
                        <input type="hidden" name="command" value="block_account"/>
                        <input type="hidden" name="id_student" value="${student.id}"/>
                        <button type="submit" class="w3-btn w3-right w3-black w3-opacity w3-hover-red w3-round-large"
                        ><fmt:message key="StudentListByAdmin_jsp.block"/>
                        </button>
                    </form>
                </c:when>
                <c:when test="${(status=='1')}">
                    <form method="POST" action="/controller">
                        <button type="button"
                                class="w3-btn w3-black w3-opacity w3-round-xxlarge">${student.name} ${student.surname}</button>
                        <input type="hidden" name="command" value="unblock_account"/>
                        <input type="hidden" name="id_student" value="${student.id}"/>
                        <button type="submit" class="w3-btn w3-right w3-black w3-opacity w3-hover-red w3-round-large"
                        ><fmt:message key="StudentListByAdmin_jsp.unblock"/>
                        </button>
                    </form>
                </c:when>
            </c:choose>
        </div>
    </div>
    <br>
</c:forEach>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <div class="w3-left">
        <div class="w3-bar">
            <form action="/controller" method="post">
                <input type="hidden" name="firstrow" value="${firstrow}">
                <input type="hidden" name="rowcount" value="${rowcount}">
                <input type="hidden" name="command" value="change_page">
                <c:if test="${(prev==true)}">
                    <input type="submit" name="page" value=<fmt:message key="StudentListByAdmin_jsp.prev"/>>
                </c:if>
                <c:if test="${(next==true)}">
                    <input type="submit" name="page" value=<fmt:message key="StudentListByAdmin_jsp.next"/>>
                </c:if>
            </form>
        </div>
    </div>
    <tf:back_to_main/>
</div>
</body>
</html>
