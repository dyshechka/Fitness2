<%-- 
    Document   : showFramedSubscription
    Created on : 17.11.2015, 22:10:40
    Author     : Marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Список оформленных абонементов</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Список оформленных абонементов
                </h2>
            </div>
            <table class="table table-striped table-hover" style="margin-top: 20px;">
                <tr>
                    <td>Фамилия Имя Отчество </td>
                    <td>Дата рождения</td>
                    <td>Адрес эл. почты</td>
                    <td>Телефон</td>
                    <td>Заморожен?</td>
                    <td>Действия</td>
                </tr>

                <tr>
                    <td>Фамилия Имя Отчество </td>
                    <td>Дата рождения</td>
                    <td>Адрес эл. почты</td>
                    <td>Телефон</td>
                    <td>Заморожен?</td>
                    <td class="actions">
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Подтвердить абонемент*</a>
                        <a href="/Fitness/Trainer/showFramedSubscription.jsp">Отклонить абонемент**</a>
                    </td>
                </tr>
                
                <tr>
                    <td>Фамилия Имя Отчество </td>
                    <td>Дата рождения</td>
                    <td>Адрес эл. почты</td>
                    <td>Телефон</td>
                    <td>Заморожен?</td>
                    <td class="actions">
                        <a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Подтвердить абонемент*</a>
                        <a href="/Fitness/Trainer/showFramedSubscription.jsp">Отклонить абонемент**</a>
                    </td>
                </tr>

            </table>

            <hr>
            <div><a href="/Fitness/Trainer/mainPagesOfTrainer.jsp">Вернуться назад</a></div>
            <hr>

            <div>
                <p><b>Примечание:</b> </br>
                    * По нажатию кнопки "Подтвердить абонемент" статус абонемента становится в состоянии "Подтвержден"</br>
                    ** По нажатию ссылки "Отклонить абонемент" статус абонемента становится в состоянии "Удален"</p>
            </div>
        </div>
    </body>
</html>
