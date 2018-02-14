<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Games</title>
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
    <c:forEach items="${games}" var="game">
        <a href="game/${game.id}"> ${game.name}</a><br>
    </c:forEach>
</body>
</html>
