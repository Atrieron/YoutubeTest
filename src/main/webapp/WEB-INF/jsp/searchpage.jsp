<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script type="text/javascript"	src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
</head>
<body>
    <form id="searchForm">
        <input id="searchString" name="searchString" placeholder="Steam shop address" oninput="inputchange()"/>
    </form>
    <div id="searchResult">

    </div>
</body>
<script type="text/javascript">
    var ajaxUrl = "ajax/game/";

    function inputchange() {
        if(document.getElementById('searchString').value){
            $.ajax({
                type: "POST",
                url: ajaxUrl + "search",
                data: $("#searchForm").serialize()
            }).done(updateTableByData);
        }
        else {
            $("#searchResult").html("");
        }
    }

    function updateTableByData(data) {
        alert("search end");
        text = "";
        for (var i = 0; i < data.length; i++) {
            text = text + "<div><img src=\"" + data[i].img_path + "\"></div>"
        }
        $("#searchResult").html(text);
    }
</script>
</html>
