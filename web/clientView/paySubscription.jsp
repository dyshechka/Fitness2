<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Оплата абонемента</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Оплата абонемента*
                </h2>
            </div>
            <div> <label for="chooseTrainer"> Выберите тренера:** </label> </div>

            <form method="POST" action="<c:url value="/Client/submitPaySubscription.jsp"/>">
                <div> 
                    <select id="chooseTrainer">
                        <c:forEach items="${requestScope.trainers}" var="trainer">
                            <option label="${trainer.fullName}" value="${trainer.idUser}"/>
                        </c:forEach>
                    </select> 
                </div> 
                <div> <input type="submit" class="btn btn-success" value="Оплатить" /></div>
            </form>           

            <hr>
            <div><a href="/Fitness/Client/mainPagesOfClient.jsp">Вернуться назад</a></div>
            <hr>

            <div><p><b>Примечание:</b></br>
                    *Оплата абонемента осуществляется, когда абонемент пребывает в статусе "Подтвержден"</br>
                    **Выбрать тренера возможно только в том случае, если при 
                    "создании абонемента" клиент выбрал вид тренировки с тренером.</p></div>
        </div>
    </body>
</html>
