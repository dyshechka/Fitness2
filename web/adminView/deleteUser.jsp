<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Удалить пользователя</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Вы уверены, что хотите удалить пользователя?
                </h2>
            </div>
            <jsp:useBean id="User" scope="request" class="models.User"/>
            <form method="POST" action="<c:url value="/Admin/submitDeleteUser.jsp">
                                            <c:param name="id_user" value="${User.idUser}"/>
                                        </c:url>">
                <input type="hidden" name="id_user" value="${User.idUser}"/>
                <div>ФИО</div>
                <div>${User.fullName}</div></br>
                
                <div>Дата рождения</div>
                <div>${User.dateOfBirth}</div></br>
                
                <div>Адрес эл.почты</div>
                <div>${User.email}</div></br>
                
                <div>Телефон</div>
                <div>${User.telephone}</div></br>
                
                <div>Логин</div>
                <div>${User.login}</div></br>
                              
                <div>Роль</div>
                <div>${User.nameRole}</div></br>
                
                <div>Заморожен?</div>
                <div>${User.frozen}</div></br>
                
                <div>№Абонемента</div>
                <div>${User.idSubscription}</div></br>
                
                <div><input type="submit" class="btn btn-success" value="Удалить" /></div>
            </form>


            
            <hr>
            <div><a href="/Fitness/Admin/mainPagesOfAdmin.jsp">Вернуться назад</a></div>
        </div>
    </body>
</html>
