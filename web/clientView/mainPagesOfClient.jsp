<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Fitness/resources/css/bootstrap.css">
        <link rel="stylesheet" href="/Fitness/resources/css/styles.css">
        <title>Главная страница Клиента</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Вы можете:</h2>
            </div>
            <div>${path}
                <c:if test="${not empty userName}">
                    <h1>Здравствуйте, ${userName}</h1>
                </c:if>
            </div>

            <c:if test="${not empty message}">
                <h1>${message}</h1>
            </c:if>
                
            <jsp:useBean id="user" scope="session" class="models.User"/>
            <jsp:useBean id="subscription" scope="request" class="models.Subscription"/>
            <c:if test="${user.idSubscription == 0}">
                <div><a href="/Fitness/Client/createNewSubscription.jsp">Создать абонемент</a></div>
            </c:if>
            <c:if test="${user.idSubscription != 0}">
                <div>
                    <a href="/Fitness/Client/deleteSubscription.jsp">Удалить абонемент</a>
                </div>
                <c:if test="${subscription.status == 'Оплачен'}">
                    <div><a href="/Fitness/Client/paySubscription.jsp">Оплатить абонемент</a></div>
                </c:if>
                <div><a href="/Fitness/Client/viewStatusOfSubscription.jsp">Посмотреть статус абонемента</a></div>
            </c:if>    
            <div><a href="/Fitness/Client/editDataAboutItself.jsp">Отредактировать личную информацию</a></div></br>
            <div><a href="/Fitness/index.jsp" class="btn btn-success">Выйти</a></div>
        </div>
    </body>
</html>
