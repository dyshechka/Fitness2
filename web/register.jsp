<%-- 
    Document   : register
    Created on : 17.11.2015, 19:52:59
    Author     : Marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/bootstrap.css">
        <link rel="stylesheet" href="resources/css/styles.css">
        <title>Регистрация</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Заполните анкету:</h2>
            </div>
       

            <div> <label> Фамилия Имя Отчетсво </label> </div>
            <div> <input type="text"/> </div>

            <div> <label> Дата рождения </label> </div>
            <div> <input type="text"/> </div>

            <div> <label> Адрес эл. почты </label> </div>
            <div> <input type="text"/> </div>

            <div> <label> Телефон </label> </div>
            <div> <input type="text"/> </div>

            <div> <label> Логин </label> </div>
            <div> <input type="text"/> </div>

            <div> <label> Пароль </label> </div>
            <div> <input type="text"/> </div>

            <div> <input type="submit" class="btn btn-success" value="Зарегистрироваться" /></div>
            
            <hr> 
            <div><a href="/Fitness/index.jsp">Вернуться на главную страницу</a></div>
        </div>
    </body>
</html>
