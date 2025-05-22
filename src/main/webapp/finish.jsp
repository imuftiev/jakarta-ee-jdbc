<%@ page import="ru.imuftiev.app.webapp.bean.Main" %><%--
  Created by IntelliJ IDEA.
  User: iwast
  Date: 21.05.2025
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Финишная страница</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>Финишная страница</h1>
    <table class="info-table">
        <tr>
            <td>Сумма «чётных и отрицательных»</td>
            <td>${res1}</td>
        </tr>
        <tr>
            <td>Сумма «нечётных и положительных»</td>
            <td>${res2}</td>
        </tr>
    </table>
    <a href="start.jsp" class="btn" >Стартовая страница</a>
    <a href="main.jsp" class="btn" <%
        Main main = new Main(true, "Был совершен переход");
        session.setAttribute("main", main);
    %>>
        Главная страница
    </a>
</div>
</body>
</html>
