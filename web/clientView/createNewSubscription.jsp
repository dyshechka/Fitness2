<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Создать абонемент</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Создание абонемента*</h2>
            </div>

            <form method="POST" action="<c:url value="/Client/submitCreateNewSubscription.jsp"/>">
                <div> <label for="type"> Тип тренировки:</label> </div>
                <div> 
                    <select id="nameTraining" name="trainingType">
                        <c:forEach items="${requestScope.listTraining}" var="training">
                            <option>${training.typeTraining}</option>
                        </c:forEach>
                    </select> 
                </div>
                
                <div> <label for="kind"> Вид тренировки:</label> </div>
                <div>
                    <select id="nameTraining" name="trainingKind">
                        <c:forEach items="${requestScope.listTraining}" var="training">
                            <option>${training.kindTraining}</option>
                        </c:forEach>
                    </select> 
                </div>

                <div> <label for="duration"> Продолжительность (в месяцах): </label> </div>
                <div> 
                    <select name="duration">
                        <option>1</option>
                        <option>3</option>
                        <option>6</option>
                        <option>12</option>
                    </select> 
                </div>           

                <div> <input type="submit" class="btn btn-success" value="Создать" /></div>
            </form>


            <hr>
            <div><a href="/Fitness/Client/mainPagesOfClient.jsp">Вернуться</a></div>
            <hr>

            <div>
                <p><b>Примечание:</b> 
                    *После создания абонемент пребывает в статусе "Оформлен".
                    А список таких оформленных абонементов будет отправляться тренеру,
                    который будет проверять корректность данных. Если все введено верно, 
                    то тогда тренер подтвердит абонемент. И абонемент станет доступным для оплаты, 
                    т.к. приобретет статус "Подтвержден".
                    При этом есть одно ограничение в системе: 
                    у одного клиента может быть только один абонемент. 
                    И если клиент захочет поменять абонемент, 
                    то ему придется либо удалить абонемент, 
                    либо создать новую учетную запись
                </p>
            </div>
        </div>
    </body>
</html>
