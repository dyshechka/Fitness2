<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Fitness/resources/css/bootstrap.css">
        <link rel="stylesheet" href="/Fitness/resources/css/styles.css">
        <title>Главная страница Администратора</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Список пользователей</h2>
            </div>

            <div>
                <c:if test="${not empty userName}">
                    <h1>Здравствуйте, ${userName}</h1>
                </c:if>
            </div>

            <c:if test="${not empty message}">
                <h1>${message}</h1>
            </c:if>

            <div class="right-text">
                <a href="/Fitness/Admin/createNewUser.jsp">Создать нового пользователя</a>
            </div>

            <table class="table table-striped table-hover" style="margin-top: 20px;">
                <tr>
                    <td>ФИО</td>
                    <td>Дата рождения</td>
                    <td>Адрес эл.почты</td>
                    <td>Телефон</td>
                    <td>Логин</td>
                    <td>Роль</td>
                    <td>Заморожен?</td>
                    <td>№ Абонемента</td>
                    <td>Действия</td>
                </tr>

                <c:forEach items="${requestScope.listUsers}" var="user">
                    <tr>
                        <td>${user.fullName}</td>
                        <td>${user.dateOfBirth}</td>
                        <td>${user.email}</td>
                        <td>${user.telephone}</td>
                        <td>${user.login}</td>
                        <td>${user.nameRole}</td>
                        <td>${user.frozen}</td>
                        <td>${user.idSubscription}</td>
                        <td class="actions">
                            <a href="
                               <c:url value="/Admin/editRole.jsp">
                                   <c:param name="id_user" value="${user.idUser}"/>
                               </c:url>
                               ">Изменить роль</a>

                            <a href="
                               <c:url value="/Admin/deleteUser.jsp">
                                   <c:param name="id_user" value="${user.idUser}"/>
                               </c:url>
                               ">Удалить пользователя</a>
                        </td>
                    </tr>
                </c:forEach>
                ${path}

            </table>
            <div> 
            </div>
            <hr> 
            <div class="right-text">
                <a href="/Fitness/index.jsp" class="btn btn-success">Выйти</a>
            </div>
        </div>
    </body>
</html>
