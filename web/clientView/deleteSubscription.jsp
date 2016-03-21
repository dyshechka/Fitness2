<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Удалить абонемент</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Вы уверены, что хотите удалить абонемент?
                </h2>
            </div>
            <jsp:useBean id="subscription" scope="request" class="models.Subscription"/>
            <form method="POST" action="<c:url value="/Client/submitDeleteSubscription.jsp">
                  <c:param name="id_sub" value="${subscription.idSubscription}"/>
                </c:url>">
                <input type="hidden" name="id_sub" value="${subscription.idSubscription}"/>
                <div>Тип тренировки</div>
                <div>${subscription.typeTraining}</div></br>

                <div>Вид тренировки</div>
                <div>${subscription.kindTraining}</div></br>

                <div>Продолжительность</div>
                <div>${subscription.duration}</div></br>

                <div>Стоимость</div>
                <div>${subscription.duration*subscription.price}</div></br>

                <div> <input type="submit" class="btn btn-success" value="Удалить" /></div>
            </form>
            <hr>
            <div><a href="/Fitness/Client/mainPagesOfClient.jsp">Вернуться назад</a></div>
        </div>
    </body>
</html>
