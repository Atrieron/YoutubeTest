<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script type="text/javascript"	src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <style type="text/css">
        .columnLeft {
            float: left;
            width: 160px;
            margin-left: 10px;
            padding-top: 1em;
        }
        .columnRight {
            padding-top: 1em;
            margin: 0 2em 0 200px;
        }
    </style>
</head>
<body>
    <form id="searchForm">
        <input style="margin-left: 10px; margin-top: 10px; width: 80%" id="searchString" name="searchString" placeholder="Steam shop address" oninput="inputchange()"/>
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
        text = "";
        for (var i = 0; i < data.length; i++) {
            text = text + "<div><div class='columnLeft'><img style=\"display: inline-block; width: 90%; height: 50px\" src=\"" + data[i].img_path + "\"></div><div class='columnRight'><p>"+data[i].name+"</p><input type='button' onclick='voteSteam("+data[i].steamId+")'>Vote</input></div></div>"
        }
        $("#searchResult").html(text);
    }

    function voteSteam(steamId) {
        $.ajax({
            type: "POST",
            url: ajaxUrl + "vote",
            data: {id:steamId, type:"steamId"}
        });
    }
</script>
</html>
