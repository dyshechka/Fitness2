<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bootstrap.css">
        <link rel="stylesheet" href="resources/css/styles.css">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Добро пожаловать!</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Добро пожаловать в фитнес-центр!</h2>
            </div>
            <div>
                <c:if test="${not empty message}">
                    <h1>${message}</h1>
                </c:if>
                <form action="/Fitness/Controller/" method="post">
                    <div> <label for="login"> Логин </label> </div>
                    <div> <input type="text" id="login" name="login"/> </div>

                    <div> <label for="password"> Пароль </label> </div>
                    <div> <input type="password" id="password" name="password"/> </div>

                    <div> <input type="submit" class="btn btn-success" value="Войти" /></div>
                </form>
                <hr> 
                <div class="footer-menu">
                    <a href="/Fitness/register.jsp">Зарегистрироваться</a> 
                </div>
            </div>
        </div>
    </body>
</html>
