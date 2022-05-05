
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-grey  w3-right-align w3-padding">
    <form method="POST" action="/controller">
        <input type="hidden" name="command" value="main_page"/>
        <button type="submit" class="w3-btn w3-black w3-hover-red w3-round-large"
        ><fmt:message key="all_jsp.back_to_main"/>
        </button>
    </form>
</div>
</body>
</html>