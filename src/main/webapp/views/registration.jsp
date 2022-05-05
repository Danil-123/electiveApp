<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Password check</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <style>
        #pswd_info {
            display: none;
        }

        .register-block .invalid, .restore-block .invalid {
            padding-left: 22px;
            line-height: 24px;
            color: #ec3f41;
        }

        .register-block .valid, .restore-block .valid {
            padding-left: 22px;
            line-height: 24px;
            color: #3a7d34;
        }

        .hidden-error-block {
            text-align: center;
            padding: 10px 0;
        }

        .big-black-block {
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            position: absolute;
            z-index: 99998;
            width: 100%;
            height: 600px;
            background: rgba(0, 0, 0, 0.5);
        }

        .register-block {
            max-width: 350px;
            overflow: hidden;
            margin: 0 auto;
            background: #eaeaea;
            padding: 15px 40px;
            border-radius: 10px;
            position: relative;
            top: 20px;
            font-family: 'Open Sans', sans-serif;
        }

        .input-block input {
            padding: 12px;
            margin-bottom: 10px;
            width: 90%;
        }

        .input-block button {
            width: 100%;
            border: none;
            background: #b1572f;
            color: #fff;
            padding: 12px;
            text-transform: uppercase;
            border-radius: 6px;
            transition: 0.5s;
            cursor: pointer;
        }

        .input-block button:hover {
            background: #fff;
            color: #b1572f;
        }
    </style>
</head>
<body>

<div class="big-black-block">
    <div class="register-block">

        <p class="login-button" id="reg-hide"><fmt:message key="registration_jsp.Sign_up"/></p>

        <div class="input-block" id="input-block-register">
            <form name="registerForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="register"/>
                <c:if test="${sessionScope.user.role == 'ADMIN' }">
                    <input type="hidden" name="role" value="TEACHER"/>
                </c:if>
                <input type="text" name="login" value="${sessionScope.loginMess}" placeholder=
                <fmt:message
                        key="registration_jsp.login"/>
                />

                <input type="text" name="name" id="name_reg" value="" placeholder=
                <fmt:message key="registration_jsp.name"/>
                />

                <input type="text" name="surname" id="lastname_reg" value="" placeholder=
                <fmt:message key="registration_jsp.surname"/>
                />

                <input type="text" name="phone" id="phone_reg" value="${sessionScope.phoneMess}" placeholder=
                <fmt:message
                        key="registration_jsp.phone"/>
                />

                <input type="email" name="email" value="${sessionScope.emailMess}" id="email_reg" placeholder=
                <fmt:message
                        key="registration_jsp.email"/>
                />

                <input type="password" class="register_inp" name="psw" value="" id="password_reg" placeholder=
                <fmt:message key="registration_jsp.pass"/>
                />
                <ul id="pswd_info">
                    <li id="letter" class="invalid"><fmt:message key="registration_jsp.restrict_num_of_letter"/></li>
                    <li id="capital" class="invalid"><fmt:message
                            key="registration_jsp.restrict_capital_of_letter"/></li>
                    <li id="number" class="invalid"><fmt:message key="registration_jsp.restrict_one_number"/></li>
                    <li id="length" class="invalid"><fmt:message key="registration_jsp.restrict_num_of_character"/></li>
                </ul>
                <input type="password" class="register_inp" name="psw-repeat" value="" id="password_dup_reg"
                       placeholder=
                       <fmt:message key="registration_jsp.repeat_pass"/>
                />
                <input type="hidden" id="input-from-bot-reg"/>
                <button type="submit" id="register_but"><fmt:message key="registration_jsp.Sign_up"/></button>
            </form>
        </div>

        <p class="hidden-error-block"></p>

    </div>
</div>
<script>
    $(function () {
        function validateEmail(email) {
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        function validatePhone(phone) {
            var re = /^(\+38|38|8)(\d){10}$/;
            return re.test(phone);
        }

        $(document).on('click', '#register_but', function () {
            var name = $('#name_reg').val();
            var lastname = $('#lastname_reg').val();
            var email = $('#email_reg').val();
            var password = $('#password_reg').val();
            var password_rep = $('#password_dup_reg').val();
            var phone = $('#phone_reg').val();

            if (password != password_rep) {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_match_pass"/>').css('color', 'red');
                return false;
            } else if (password.length < 4) {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_fill_all_input_boxes"/>').css('color', 'red');
                return false;
            } else if (name == '') {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_fill_all_input_boxes"/>').css('color', 'red');
                return false;
            } else if (lastname == '') {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_fill_all_input_boxes"/>').css('color', 'red');
                return false;
            } else if (email == '') {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_fill_all_input_boxes"/>').css('color', 'red');
                return false;
            } else if (!validateEmail(email)) {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_email"/>').css('color', 'red');
                return false;
            }else if (!validatePhone(phone)) {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_phone"/>').css('color', 'red');
                return false;
            } else if ((password == password_rep) && (password.length > 3) && (password.match(/\d/)) && (password.match(/[A-Z]/)) && (password.match(/[A-z]/))) {
                $('.hidden-error-block').html('Good').css('color', 'green');
            } else {
                $('.hidden-error-block').html('<fmt:message key="registration_jsp.check_pass"/>').css('color', 'red');
            }
        });

        $('#password_dup_reg').keyup(function () {
            var pas = $('#password_reg').val();
            var con_pas = $(this).val();
            if (pas === con_pas) {
                $('.register_inp').css('border-color', 'seagreen');
            } else {
                $('.register_inp').css('border-color', 'red');
            }
        });
        $('#password_reg').keyup(function () {
            var pswd = $('#password_reg').val();
            if (pswd.length < 4) {
                $('#length').removeClass('valid').addClass('invalid');
            } else {
                $('#length').removeClass('invalid').addClass('valid');
            }
            //validate letter
            if (pswd.match(/[A-zА-я]/)) {
                $('#letter').removeClass('invalid').addClass('valid');
            } else {
                $('#letter').removeClass('valid').addClass('invalid');
            }

            //validate capital letter
            if (pswd.match(/[A-ZА-Я]/)) {
                $('#capital').removeClass('invalid').addClass('valid');
            } else {
                $('#capital').removeClass('valid').addClass('invalid');
            }

            //validate number
            if (pswd.match(/\d/)) {
                $('#number').removeClass('invalid').addClass('valid');
            } else {
                $('#number').removeClass('valid').addClass('invalid');
            }
        }).focus(function () {
            $('#pswd_info').show(500);
        }).blur(function () {
            $('#pswd_info').hide(500);
        });
    });
</script>
</body>
</html>