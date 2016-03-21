<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Создать нового пользователя</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Заполните анкету:
                </h2>
            </div>
            <jsp:useBean id="User" scope="request" class="models.User"/>
            <div>
            <form method="POST" action="<c:url value="/Admin/submitCreateNewUser.jsp"/>"> 
                    <div> <label for="fullName"> Фамилия Имя Отчетсво </label> </div>
                    <div> <input type="text" id="fullName" name="fullName"/> </div>

                    <div> <label for="date"> Дата рождения </label> </div>
                    <div> <input type="date" id="date" name="dateOfBirth"/> </div>

                    <div> <label for="email"> Адрес эл. почты </label> </div>
                    <div> <input type="email" id="email" name="email"/> </div>

                    <div> <label for="telephone"> Телефон </label> </div>
                    <div> <input type="tel" id="telephone"  name="telephone"/> </div>

                    <div> <label for="login"> Логин </label> </div>
                    <div> <input type="text" id="login" name="login"/> </div>

                    <div> <label for="password"> Пароль </label> </div>
                    <div> <input type="password" id="password" name="password"/> </div>

                    <div> <label for="nameRole"> Название роли </label> </div>
                    <div> 
                        <select id="nameRole" name="roleName">
                            <c:forEach items="${requestScope.listRoles}" var="role">
                                <option value="${role.nameRole}">${role.nameRole}</option>
                            </c:forEach>                    
                        </select> 
                    </div>

                    </div>
                    <div> <input type="submit" class="btn btn-success" value="Создать" /></div>
                </form>
                
                <hr>
                <div><a href="/Fitness/Admin/mainPagesOfAdmin.jsp">Вернуться назад</a></div>
            </div>
        </div>
    </body>
</html>
