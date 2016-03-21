<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Удалить группу</title>
    </head>
    <div class="main">
        <div class="header">
            <h2>
                Вы уверены, что хотите удалить группу?
            </h2>
        </div>
        <jsp:useBean id="group" scope="request" class="models.Group"/>
        <form method="POST" action="<c:url value="/Trainer/submitDeleteGroup.jsp">
                  <c:param name="id_group" value="${group.idGroup}"/>
              </c:url>">
            <input type="hidden" name="id_group" value="${group.idGroup}"/>
            
            <div> <label> Название группы </label> </div>
            <div>${group.nameGroup}</div></br>

            <div> <input type="submit" class="btn btn-success" value="Удалить" /></div>
        </form>
        <hr>
        <div><a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Вернуться назад</a></div>
    </div>
</body>
</html>
