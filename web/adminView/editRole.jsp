<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Изменение роли</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>
                    Изменение роли
                </h2> 
            </div>
            <jsp:useBean id="User" scope="request" class="models.User"/>
            <label for="nameRole">Выберите роль</label>
            <form method="POST" action="<c:url value="/Admin/submitEditRole">
                                            <c:param name="id_user" value="${User.idUser}"/>
                                        </c:url>"> 
                <input type="hidden" name="id_user" value="${User.idUser}"/>
                <div> 
                    <select id="nameRole" name="roleName">
                        <c:forEach items="${requestScope.listRoles}" var="role">
                            <option value="${role.nameRole}">${role.nameRole}</option>
                        </c:forEach>                    
                    </select> 
                </div>
                <div> <input type="submit" class="btn btn-success" value="Изменить" /></div>
            </form>
            
            <hr>
            <div><a href="/Fitness/Admin/mainPagesOfAdmin.jsp">Вернуться</a></div>
        </div>
    </body>
</html>
