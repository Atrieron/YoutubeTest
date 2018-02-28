<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "ex" uri = "mytags"%>
<c:forEach items="${games}" var="game">
    <ex:searchResultTag gameSearchTo="${game}"/>
</c:forEach>