<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Подробная информация о группе</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Подробная информация о группе
                </h2>
            </div>

            <table class="table table-striped table-hover" style="margin-top: 20px;">
                <tr>
                    <td>ФИО клиента</td>
                    <td>
                        Действия
                    </td>
                    <td><label>Дата посещения 1</label></td>
                    <td><label>Дата посещения 2</label></td>
                    <td><label>Дата посещения n</label></td>
                </tr>

                <tr>
                    <td>ФИО клиента</td>
                    <td class="actions">
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Изменить группу клиенту</a>
                        <a href="/Fitness/Trainer/listFrozenClient.jsp">Заморозить клиента*</a>
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Отметить посещение**</a>
                    </td>
                    <td><label>Дата посещения 1</label></td>
                    <td><label>Дата посещения 2</label></td>
                    <td><label>Дата посещения n</label></td>
                </tr>
                
                <tr>
                    <td>ФИО клиента</td>
                    <td class="actions">
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Изменить группу клиенту</a>
                        <a href="/Fitness/Trainer/listFrozenClient.jsp">Заморозить клиента*</a>
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Отметить посещение**</a>
                    </td>
                    <td><label>Дата посещения 1</label></td>
                    <td><label>Дата посещения 2</label></td>
                    <td><label>Дата посещения n</label></td>
                </tr>
                
                <tr>
                    <td>ФИО клиента</td>
                    <td class="actions">
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Изменить группу клиенту</a>
                        <a href="/Fitness/Trainer/listFrozenClient.jsp">Заморозить клиента*</a>
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Отметить посещение**</a>
                    </td>
                    <td><label>Дата посещения 1</label></td>
                    <td><label>Дата посещения 2</label></td>
                    <td><label>Дата посещения n</label></td>
                </tr>
            </table>

                <div><a href="/Fitness/Trainer/mainPagesOfTrainer.jsp" class="btn btn-success">Вернуться назад</a></div>
                <hr>

                <div>
                    <p><b>Примечание:</b></br> 
                        * По нажатию кнопки "Заморозить клиента" клиент попадает в список "Замороженных клиентов";</br>
                        ** По нажатию ссылки "Отметить посещение" тренер подтверждает, что клиент присутствовал на занятии.
                    </p>
                </div>
        </div>
    </body>
</html>
