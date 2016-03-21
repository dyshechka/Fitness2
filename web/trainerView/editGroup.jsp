<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Изменить название группы</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Изменить название группы
                </h2>
            </div>
            <jsp:useBean id="group" scope="request" class="models.Group"/>
            <form method="POST" action="<c:url value="/Trainer/submitEditGroup.jsp"/>">
                
                <input type="hidden" name="id_group" value="${group.idGroup}"/>
                
                <div> <label for="nameGroup"> Название группы </label> </div>
                <div> <input type="text" id="nameGroup" name="nameGroup"/> </div>

                <div> <input type="submit" class="btn btn-success" value="Изменить" /></div>
            </form>

            </br>
            <div><a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Вернуться назад</a></div>
        </div>
    </body>
</html>
