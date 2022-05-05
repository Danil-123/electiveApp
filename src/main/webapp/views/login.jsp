<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<form action="/views/changeLocale.jsp" method="post">
    <fmt:message key="settings_jsp.label.set_locale"/>:
    <select name="locale">
        <c:forEach items="${applicationScope.locales}" var="locale">
            <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
            <option value="${locale.key}" ${selected}>${locale.value}</option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
</form>
<div class="w3-card-4">
    <form name="loginForm" method="POST" action="/controller" class="w3-selection w3-light-grey w3-padding">

        <fmt:message key="main_jsp.login"/><br/>
        <input type="text" name="login" value=""
               class="w3-input w3-animate-input w3-border w3-round-large" style="width: 35%"/>
        <br/><fmt:message key="main_jsp.pass"/><br/>
        <input type="password" name="password" value=""
               class="w3-input w3-animate-input w3-border w3-round-large" style="width: 35%"/>
        <br/>
        <div class="w3-container w3-deep-orange" style="width: 35%">
            ${errorLoginPassMessage}
        </div>
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="hidden" name="command" value="login"/>
        <button type="submit" class="w3-btn w3-grey w3-round-large w3-margin-bottom
w3-hover-text-green"><fmt:message key="main_jsp.button.login"/>
        </button>
    </form>
    <form name="registerForm" method="POST" action="/controller" class="w3-selection w3-light-grey w3-padding">
        <input type="hidden" name="command" value="register_page"/>
        <button type="submit" class="w3-btn w3-grey w3-round-large w3-margin-bottom
w3-hover-text-green"><fmt:message key="main_jsp.button.sign_up"/>
        </button>
    </form>
</div>
</body>
</html>