<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Welcome</title>
    <!-- Bootstrap CSS (Cloudflare CDN) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.1/css/bootstrap.min.css"
          integrity="sha512-T584yQ/tdRR5QwOpfvDfVQUidzfgc2339Lc8uBDtcp/wYu80d7jwBgAxbyMh0a9YM9F8N3tdErpFI8iaGx6x5g=="
          crossorigin="anonymous" referrerpolicy="no-referrer">
    <!-- jQuery (Cloudflare CDN) -->
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
            integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- Bootstrap Bundle JS (Cloudflare CDN) -->
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.1/js/bootstrap.min.js"
            integrity="sha512-UR25UO94eTnCVwjbXozyeVd6ZqpaAE9naiEUBK/A+QDbfSTQFhPGj5lOR6d8tsgbBk84Ggb5A3EkjsOgPRPcKA=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <style>
        body {
            margin-top: 20px;
            color: #1a202c;
            text-align: left;
            background-color: #e2e8f0;
        }

        .main-body {
            padding: 15px;
        }

        .card {
            box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0 rgba(0, 0, 0, .06);
        }

        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid rgba(0, 0, 0, .125);
            border-radius: .25rem;
        }

        .card-body {
            flex: 1 1 auto;
            min-height: 1px;
            padding: 1rem;
        }

        .gutters-sm {
            margin-right: -8px;
            margin-left: -8px;
        }

        .gutters-sm > .col, .gutters-sm > [class*=col-] {
            padding-right: 8px;
            padding-left: 8px;
        }

        .mb-3, .my-3 {
            margin-bottom: 1rem !important;
        }

        .bg-gray-300 {
            background-color: #e2e8f0;
        }

        .h-100 {
            height: 100% !important;
        }

        .shadow-none {
            box-shadow: none !important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="${user.avatar}" alt="${user.role}"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${user.name} ${user.surname}</h4>
                                <p class="text-secondary mb-1">${user.role}</p>
                                <!-- Button trigger modal -->
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#exampleModalCenter">
                                    Edit
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLongTitle">Avatars</h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <c:import url="avatars.jsp"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="main_jsp.login"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.login}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="main_jsp_email"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="main_jsp.phone"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.phone}
                            </div>
                        </div>
                        <hr>
                        <c:set var="status" value="${user.status}"/>
                        <c:if test="${(status=='1')}">
                            <h1 class="text-danger"><fmt:message key="main_jsp.blocked_account"/></h1>
                        </c:if>
                        <c:if test="${(sessionScope.user.role=='TEACHER')}">
                            <form method="POST" action="/controller">
                                <input type="hidden" name="command" value="teach_courses"/>
                                <input type="hidden" name="id_teacher" value="${user.id}"/>
                                <button type="submit" class="btn btn-info " target="__blank"><fmt:message
                                        key="main_jsp.courses"/>
                                </button>
                            </form>
                        </c:if>

                        <c:if test="${(sessionScope.user.role=='STUDENT')||(sessionScope.user.role=='ADMIN')}">
                            <c:set var="status" value="${user.status}"/>
                            <c:choose>
                                <c:when test="${(status=='0')}">
                                    <form method="POST" action="/controller">
                                        <input type="hidden" name="command" value="topic_list"/>
                                        <button type="submit" class="btn btn-info " target="__blank"><fmt:message
                                                key="main_jsp.courses"/>
                                        </button>
                                    </form>
                                </c:when>
                                <c:when test="${(status=='1')}">
                                    <form method="POST" action="/controller">
                                        <button class="btn btn-secondary btn-lg" disabled><fmt:message
                                                key="main_jsp.courses"/></button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </c:if>

                        <c:if test="${sessionScope.user.role == 'ADMIN' }">
                            <form method="POST" action="/controller">
                                <input type="hidden" name="command" value="register_page"/>
                                <button type="submit" class="btn btn-info " target="__blank"><fmt:message
                                        key="main_jsp.newTeacher"/>
                                </button>
                            </form>

                            <form method="POST" action="/controller">
                                <input type="hidden" name="command" value="admin_students"/>
                                <input type="hidden" name="firstrow" value="4">
                                <input type="hidden" name="rowcount" value="0">
                                <button type="submit" class="btn btn-info " target="__blank"><fmt:message
                                        key="main_jsp.Students_action"/>
                                </button>
                            </form>
                        </c:if>
                        <div class="text-right">
                            <form method="POST" action="/controller">
                                <input type="hidden" name="command" value="logout"/>
                                <button type="submit" class="btn btn-danger " target="__blank"><fmt:message
                                        key="main_jsp.logout"/>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
