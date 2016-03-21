<%-- 
    Document   : changeGroup
    Created on : 17.11.2015, 22:22:33
    Author     : Marta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Изменить группу клиенту</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Изменить группу клиенту
                </h2>
            </div>

            <div> <label> Выберите группу</label> </div>
            <div> 
                <select>
                    <option>Группа №1</option>
                    <option>Группа №2</option>
                    <option>Группа №...</option>
                </select> 
            </div>

            <div> <input type="submit" class="btn btn-success" value="Сохранить" /></div>
        </div>
    </body>
</html>
