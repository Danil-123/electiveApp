<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <title>Courses</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        /* Стилизация выпадающей кнопки */
        .dropbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        /* Контейнер <div> - необходимо разместить выпадающий контент */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        /* Выпадающий контент (скрыт по умолчанию) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        /* Ссылки внутри выпадающего */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Изменить цвет выпадающих ссылок при наведении */
        .dropdown-content a:hover {
            background-color: #f1f1f1
        }

        /* Показать выпадающее меню при наведении */
        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Изменить цвет фона кнопки выпадающего списка, когда отображается содержимое выпадающего списка. */
        .dropdown:hover .dropbtn {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
<div class="dropdown">
    <button class="dropbtn"><fmt:message key="AllCourses_jsp.sort"/></button>
    <div class="dropdown-content">
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="sort_by_name"/>
            <button type="submit" class="w3-btn  w3-black w3-round-large"><fmt:message key="registration_jsp.name"/>
            </button>
        </form>
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="sort_by_duration"/>
            <button type="submit" class="w3-btn  w3-black w3-round-large"><fmt:message key="AllCourses_jsp.duration"/>
            </button>
        </form>
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="sort_by_num_of_student"/>
            <button type="submit" class="w3-btn  w3-black w3-round-large"><fmt:message
                    key="AllCourses_jsp.num_of_student"/>
            </button>
        </form>
    </div>
</div>
<c:forEach items="${listOfCourses}" var="course">
    <div class="w3-container w3-grey w3-opacity w3-left-align w3-padding">
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="profile_course"/>
            <input type="hidden" name="id" value="${course.id}"/>
            <button type="submit" class="w3-btn  w3-black w3-round-large">${course.course_name}
            </button>
            <br>
            <fmt:message key="coursesByStudent_jsp.date_start_of_course"/>${course.start_day}
        </form>
        <c:if test="${sessionScope.user.role == 'ADMIN' }">

            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="edit_course"/>
                <input type="hidden" name="course_id" value="${course.id}"/>
                <input type="hidden" name="name" value="${course.course_name}"/>
                <input type="text" name="newName" value="${course.course_name}"/>
                <button type="submit" class="w3-btn w3-black w3-hover-green w3-round-large"
                        onclick="editCourse();return false;"><fmt:message key="AllCourses_jsp.edit"/>
                </button>
            </form>

            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="delete_course"/>
                <input type="hidden" name="name" value="${course.course_name}"/>
                <input type="hidden" name="course_id" value="${course.id}"/>
                <button type="submit" class="w3-btn w3-red w3-round-large"
                        onclick="delCourse();return false;"><fmt:message key="detailOfCourse_jsp.delete"/>
                </button>
            </form>
        </c:if>
    </div>
    <br>
</c:forEach>

<div class="w3-container w3-grey w3-right-align w3-padding">
    <c:if test="${sessionScope.user.role == 'ADMIN' }">
        <div class=" w3-grey w3-center w3-jumbo">
            <button onclick="document.getElementById('id01').style.display='block'"
                    class="w3-btn w3-btn w3-green w3-round-large"><fmt:message key="AllCourses_jsp.add_course"/>
            </button>
        </div>

        <!-- Модальное окно -->
        <div id="id01" class="w3-modal">
            <div class="w3-modal-content w3-animate-top">
                <div class="w3-container">
    <span onclick="document.getElementById('id01').style.display='none'"
          class="w3-button w3-display-topright">&times;</span>
                    <div class="w3-left-align"><p><fmt:message key="AllCourses_jsp.set_date"/></p></div>
                    <p>
                    <form method="POST" action="/controller">
                        <input type="hidden" name="command" value="add_course"/>
                        <fmt:message key="AllCourses_jsp.name"/>
                        <input type="text" name="courseName" value=""/>
                        <input type="hidden" name="topicName" value="${topic}"/>
                        <br> <fmt:message key="coursesByStudent_jsp.date_start_of_course"/>
                        <input name="start_day" type="date"/>
                        <br>
                        <button type="submit" class="w3-btn w3-green w3-round-large"><fmt:message
                                key="AllCourses_jsp.add_course"/>
                        </button>
                    </form>
                    </p>
                </div>
            </div>
        </div>
    </c:if>
    <tf:back_to_main/>
</div>
<script>
    function editCourse() {
        if (confirm('<fmt:message key="AllCourses_jsp.ed"/>')) {
            $('html,body').css({'background': '#000'});
        }
    }

    function delCourse() {
        if (confirm('<fmt:message key="AllCourses_jsp.del"/>')) {
            $('html,body').css({'background': '#000'});
        }
    }
</script>
</body>
</html>