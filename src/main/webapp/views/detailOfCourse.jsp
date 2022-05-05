<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <style>
        button[name="run_script"] {
            border: none;
            border-radius: 7px;
            padding: 10px 25px;
            background: #7e6b4c;
            cursor: pointer;
            text-transform: uppercase;
            font-weight: bold;
            color: #100f0f;
        }

        button[name="run_script"]:hover {
            background: #c7309d;
        }
    </style>
    <title>details</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-row-padding w3-grey  w3-padding">
    <div class="w3-col s9 w3-grey w3-left-align w3-padding">
        <fmt:message key="detailOfCourse_jsp.teacher"/>
        <div class="w3-dropdown-click">
            <button onclick="dropDownTeacher()" class="w3-button w3-black">${teacher.name} ${teacher.surname}<br>
            </button>
            <div id="Demo" class="w3-dropdown-content w3-bar-block w3-card-4 w3-animate-zoom">
                <c:forEach items="${listOfTeacher}" var="teach">
                    <form method="POST" action="/controller">
                        <input type="button" name="teacher_name" value="${teach.name} ${teach.surname}"/>
                        <input type="hidden" name="id_course" value="${course.id}"/>
                        <input type="hidden" name="id_account" value="${teach.id}"/>
                        <input type="hidden" name="command" value="change_teacher"/>
                        <button type="submit" class="w3-btn w3-black w3-opacity w3-hover-red w3-round-large"
                        ><fmt:message key="detailOfCourse_jsp.change"/>
                        </button>
                    </form>
                </c:forEach>
            </div>
        </div>

        <c:forEach items="${students}" var="student">
            <p>
            <div class="w3-row-padding w3-panel w3-border w3-round-xxlarge">

                <div class="w3-bar w3-padding">
                    <form method="POST" action="/controller">
                        <button type="button" name="run_script">${student.name} ${student.surname}</button>
                        <input type="hidden" name="command" value="del_student_from_course"/>
                        <input type="hidden" name="id_student" value="${student.id}"/>
                        <input type="hidden" name="id_course" value="${course.id}"/>
                        <button type="submit" class="w3-btn w3-right w3-black w3-opacity w3-hover-red w3-round-large"
                        ><fmt:message key="detailOfCourse_jsp.delete"/>
                        </button>
                    </form>
                </div>
            </div>
        </c:forEach>

        <p>
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="change_description"/>
            <input type="hidden" name="id_course" value="${course.id}"/>
            <textarea name="description" cols="30" rows="4" placeholder="${course.description}"></textarea>
            <button type="submit" class="w3-btn w3-black w3-opacity w3-hover-red w3-round-large"
            ><fmt:message key="detailOfCourse_jsp.set"/>
            </button>
        </form>
        <fmt:message key="detailOfCourse_jsp.duration"/>
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="change_duration"/>
            <input type="hidden" name="id_course" value="${course.id}"/>
            <input type="text" name="duration" placeholder="${course.duration}"/>
            <button type="submit" class="w3-btn w3-black w3-opacity w3-hover-red w3-round-large"
            ><fmt:message key="detailOfCourse_jsp.set"/>
            </button>
        </form>
    </div>
</div>
<tf:back_to_main/>
<script>
    function dropDownTeacher() {
        var x = document.getElementById("Demo");
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }
</script>
</body>
</html>