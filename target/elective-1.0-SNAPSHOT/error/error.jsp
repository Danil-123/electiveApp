<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Error Page</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
Sorry, ${error}<br>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form method="POST" action="controller">
        <input type="hidden" name="command" value="main_page"/>
        <button type="submit" class="w3-btn w3-black w3-hover-red w3-round-large"
        >Back to profile
        </button>
    </form>
</div>
</body>
</html>