<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Изменение личных данных</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Изменение личных данных
                </h2>
            </div>
            <jsp:useBean id="User" scope="request" class="models.User"/>
            <form method="POST" action="<c:url value="/Client/submitEditDataAboutItself.jsp"/>">
                
                <div> <label for="fullName"> Фамилия Имя Отчетсво </label> </div>
                <div> <input type="text" id="fullName" name="fullName"/> </div>

                <div> <label for="email"> Адрес эл. почты </label> </div>
                <div> <input type="text" id="email" name="email"/> </div>

                <div> <label for="telephone"> Телефон </label> </div>
                <div> <input type="text" id="telephone" name="telephone"/> </div>

                <div> <input type="submit" class="btn btn-success" value="Изменить" /></div>
            </form>

            <hr>
            <div><a href="/Fitness/Client/mainPagesOfClient.jsp">Вернуться назад</a></div>
        </div>
    </body>
</html>
