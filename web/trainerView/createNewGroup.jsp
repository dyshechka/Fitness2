<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Создать новую группу</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Создать группу
                </h2>
            </div>

            <form method="POST" action="<c:url value="/Trainer/submitCreateNewGroup.jsp"/>">
                <div> <label for="nameGroup"> Название группы </label> </div>
                <div> <input type="text" id="nameGroup" name="nameGroup"/> </div>

                <div> <label for="nameTraining"> Название тренировки </label> </div>
                <div> <input type="text" id="nameTraining" name="nameTraining"/> </div>
                
                <div> <input type="submit" class="btn btn-success" value="Создать" /></div>
            </form>
            <hr>
            <div><a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Вернуться назад</a></div>
        </div>
    </body>
</html>
