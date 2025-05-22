<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>Главная страница</h1>
    <p>Здесь находится поле ввода для чисел, необходимых для вычисления функции.</p>
    <form method="post">
        <label>
            <input name="nums" type="text" placeholder="Введите числа через запятую: ">
        </label>
        <button type="submit">Выполнить</button>
    </form>
    <jsp:useBean id="main" class="ru.imuftiev.app.webapp.bean.Main" scope="session"/>
    <%!
        String firstAnswer;
        String secondAnswer;
        public List<Integer> toList(String nums) {
            return Arrays.stream(nums.split(",")).map(Integer::parseInt).toList();
        }
        public String oddPositiveSum(List<Integer> nums) {
            return "" +
                    nums.stream()
                            .filter(s -> s % 2 != 0 && s > 0)
                            .mapToInt(Integer::intValue)
                            .sum();
        }
        public String evenNegativeSum(List<Integer> nums) {
            return "" +
                    nums.stream()
                            .filter(s -> s < 0 && s % 2 == 0)
                            .mapToInt(Integer::intValue)
                            .sum();
        }
    %>
    <%
        if ("post".equalsIgnoreCase(request.getMethod())) {
            String params = request.getParameter("nums");
            List<Integer> nums = toList(params);
            firstAnswer = oddPositiveSum(nums);
            secondAnswer = evenNegativeSum(nums);
            request.setAttribute("res1", firstAnswer);
            request.setAttribute("res2", secondAnswer);
            request.getRequestDispatcher("finish.jsp").forward(request, response);
        }
    %>
    <p> ${main.message} </p>
    <a href="finish.jsp" class="btn">Финишная страница</a>
    <a href="start.jsp" class="btn">Стартовая страница</a>
</div>
</body>
</html>
