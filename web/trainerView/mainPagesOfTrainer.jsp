<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../resources/css/bootstrap.css">
        <link rel="stylesheet" href="../resources/css/styles.css">
        <title>Главная страница Тренера</title>
    </head>
    <body>
        <div class="main">
            <div class="header">
                <h2>Возможности тренера</h2>
            </div>

            <div>
                <c:if test="${not empty userName}">
                    <h1>Здравствуйте, ${userName}</h1>
                </c:if>
            </div>

            <c:if test="${not empty message}">
                <h1>${message}</h1>
            </c:if>

            <div><a href="/Fitness/Trainer/createNewGroup.jsp">Создать новую группу</a></div> </br>

            <table class="table table-striped table-hover" style="margin-top: 20px;">
                <tr>
                    <td>
                        Название группы
                    </td>

                    <td>
                        Действия
                    </td>
                </tr>

                <c:forEach items="${requestScope.listGroups}" var="group">
                    <tr>
                        <td>
                            ${group.nameGroup}
                        </td>

                        <td class="actions">
                            <a href="
                               <c:url value="/Trainer/editGroup.jsp">
                                   <c:param name="id_group" value="${group.idGroup}"/>
                               </c:url>
                               ">
                                Изменить название группы
                            </a>
                            
                            <a href="
                               <c:url value="/Trainer/detailsGroup.jsp">
                                   <c:param name="id_group" value="${group.idGroup}"/>
                               </c:url>
                               ">
                                Подробная информация о группе
                            </a>
                            
                            <a href="
                               <c:url value="/Trainer/deleteGroup.jsp">
                                   <c:param name="id_group" value="${group.idGroup}"/>
                               </c:url>
                               ">
                                Удалить группу
                            </a>
                        </td>
                    </tr>
                </c:forEach>              
            </table></br>    

            <div>
                <a href="/Fitness/Trainer/listFrozenClient.jsp">Список замороженных пользователей</a> 
            </div>
            <div>
                <a href="/Fitness/Trainer/showFramedSubscription.jsp">Список оформленных абонементов</a>
            </div>
            </br>
            <div><a href="/Fitness/index.jsp" class="btn btn-success">Выйти</a></div>
        </div>      

    </div>
</body>
</html>
