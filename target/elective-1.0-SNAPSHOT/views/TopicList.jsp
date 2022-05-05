<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tf" %>
<html>
<head>
    <style>
        .form-group > * {
            display: inline-block;
        }
    </style>
    <title>Courses</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-row w3-container w3-opacity w3-grey">
    <c:forEach items="${allTopic}" var="topic">
    <c:if test="${(topic.status=='0')}">
    <div class="form-group w3-col s7 w3-container w3-grey ">
        <form method="POST" action="/controller">
            <input type="hidden" name="command" value="course_list"/>
            <input type="hidden" name="name" value="${topic.name}"/>
            <button type="submit" class="w3-btn w3-hover-red w3-black w3-jumbo w3-round-large">${topic.name}
            </button>
        </form>
        <c:if test="${sessionScope.user.role == 'ADMIN' }">
            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="del_topic"/>
                <input type="hidden" name="name" value="${topic.name}"/>
                <button type="submit"
                        class="w3-btn w3-red  w3-round-large">
                    <fmt:message key="TopicList_jsp.del_topic"/>
                </button>
            </form>
        </c:if>
    </div>
    </c:if>
    </c:forEach>

    <div class="w3-col s5 w3-container w3-grey  w3-right-align w3-padding">
        <c:if test="${sessionScope.user.role == 'ADMIN' }">
            <div class="w3-grey w3-xxlarge w3-padding">
                <button onclick="document.getElementById('id01').style.display='block'"
                        class="w3-btn w3-btn w3-green w3-round-large"><fmt:message key="TopicList_jsp.add_topic"/>
                </button>
            </div>

            <!-- Модальное окно -->
            <div id="id01" class="w3-modal">
                <div class="w3-modal-content w3-animate-top">
                    <div class="w3-container">
      <span onclick="document.getElementById('id01').style.display='none'"
            class="w3-button w3-display-topright">&times;</span>
                        <div class="w3-right-align"><p><fmt:message key="TopicList_jsp.set_name_of_topic"/></p></div>
                        <p>
                        <form method="POST" action="/controller">
                            <input type="hidden" name="command" value="add_topic"/>
                            <input type="text" name="topicName" value=""/>
                            <button type="submit" class="w3-btn w3-green w3-round-large"><fmt:message
                                    key="TopicList_jsp.add_topic"/>
                            </button>
                        </form>
                        </p>
                    </div>
                </div>
            </div>
            <form method="POST" action="/controller">
                <input type="hidden" name="command" value="bin_of_topics"/>
                <div class=" w3-grey w3-xxlarge w3-padding">
                    <button type="submit" class="w3-btn w3-btn w3-green w3-round-large">
                        <fmt:message key="TopicList_jsp.bin"/>
                    </button>
                </div>
            </form>
        </c:if>
        <div class="w3-padding">
            <form method="POST" action="/controller">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="command" value="user_course_list"/>
                <button type="submit" class="w3-btn w3-left-align w3-black w3-hover-red w3-round-large">
                    <fmt:message key="TopicList_jsp.your_courses"/>
                </button>
            </form>
        </div>
        <tf:back_to_main/>
    </div>
</body>
</html>