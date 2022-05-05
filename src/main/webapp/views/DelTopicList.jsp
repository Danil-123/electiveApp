<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <title>List of topic</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<c:forEach items="${allDelTopic}" var="topic">
    <div class="w3-row w3-panel w3-border w3-round-large">
        <p>
        <div class="w3-bar ">
            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="reestablish_topic"/>
                <input type="hidden" name="name" value="${topic.name}"/>
                <button type="button"
                        class="w3-btn w3-black w3-opacity w3-round-xxlarge">${topic.name}</button>
                <button type="submit" class="w3-btn w3-right w3-black w3-opacity w3-hover-red w3-round-large"
                ><fmt:message key="DelTopicList_jsp.reestablish"/>
                </button>
            </form>
        </div>
    </div>
    <br>
</c:forEach>
<tf:back_to_main/>
</body>
</html>
