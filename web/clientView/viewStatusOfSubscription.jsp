<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Текущий статус абонемента</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Статус Вашего абонемента...*
                </h2>
            </div>
            
            <p>${statusSubscription}</p>

            <div><a href="/Fitness/Client/mainPagesOfClient.jsp">Вернуться назад</a></div>
            <hr>
            
            <div>
                <p><b>Примечание:</b> </br>
                    * Статус абонемента может быть в следующих состояниях:</p>
                <ul>
                    <li>Оформлен</li>
                    <li>Подтвержден</li>
                    <li>Оплачен</li>
                    <li>Просрочен</li>
                    <li>Удален</li>
                </ul>
            </div>
        </div>
    </body>
</html>
