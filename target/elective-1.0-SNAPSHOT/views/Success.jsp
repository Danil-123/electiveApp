<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-center w3-animate-zoom">
    <h1><fmt:message key="Success_jsp.success"/></h1>
    <img src="${pageContext.request.contextPath}/img_success.jpg"  style="width:30%">
</div>
<tf:back_to_main/>
</body>
</html>
