<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${game.name}</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script type="text/javascript"	src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <style type="text/css">
        .section{
            border:solid 1px #999;
            float:left;
            height:140px;
            margin-left:10px;
            width:150px;
            display: inline-block;
        }
        .sectionbutton{
            clear: both;
            float:left;
            margin-left:10px;
            display: inline-block;
            top: 50%;
            left: 50%;
        }
        .sectionimange{
            height:70%;
            width:140px;
            display: inline-block;
        }
        #columnLeft {
            float: left;
            width: 160px;
            margin-left: 10px;
            padding-top: 1em;
        }
        #columnRight {
            padding-top: 1em;
            margin: 0 2em 0 200px;
        }
        #footer {
            clear: both;
            background:#EFEFEF;
            padding-bottom: 1em;
            border-top: 1px solid #333;
            padding-left: 20px;
            height:150px;
        }
        #file-input {
            cursor: pointer;
            outline: none;
            position: absolute;
            top: 0;
            left: 0;
            width: 0;
            height: 0;
            overflow: hidden;
            filter: alpha(opacity=0); /* IE < 9 */
            opacity: 0;
        }
        .input-label {
            cursor: pointer;
            position: relative;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div>
        <div id ="columnLeft">
            <label for="file-input" class="input-label">
                <img style="display: inline-block; width: 90%; height: 130px" src="${pageContext.request.contextPath}/imageController/${game.id}"/>
                <input type="file" id="file-input" onchange="fileChanged()" accept="image/*">
            </label>
        </div>
        <div id ="columnRight">
            <h1>${game.name}</h1>
            ${game.description}
        </div>
    </div>
    <div id="footer">

    </div>
    <div id="playerPic" class="modal fade" style="width: 60%; height: 60%">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body" id="playerdiv">
                    <div id="playerholder"></div>
                </div>
            </div>
        </div>
    </div>
    <div id="videoAddition" class="modal fade" style="width: 60%; height: 60%">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="detailsForm">
                        <input type="hidden" id="gameId" name="gameId" value="${game.id}">

                        <div class="form-group">
                            <label for="description" class="control-label col-xs-3">Description</label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="description" name="description">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="link" class="control-label col-xs-3">Link</label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="link" name="link" placeholder="Link to video">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-9">
                                <button class="btn btn-primary" type="button" onclick="save()">
                                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    var ajaxUrl = "ajax/video/";
    var gameId;
    var player;

    $(function()
        {
            var tag = document.createElement('script');
            tag.src = "//www.youtube.com/player_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

            $('#playerPic').on('hidden.bs.modal', function ()
            {
                player.stopVideo();
            });
            gameId = document.getElementById('gameId').value;
            updateVideo();
        }
    );

    function setId(ytid, time) {
        $("#playerdiv").html("<div id=\"playerholder\"></div>");

        player = new YT.Player('playerholder', {
            height: '70%',
            width: '100%',
            videoId: ytid,
            playerVars: {'autoplay': 1, 'start':time}
        });

        $("#playerPic").modal('show');
    }

    function add() {
        description
        $("#description").val("");
        $("#link").val("");
        $("#videoAddition").modal();
    }

    function updateVideo() {
        $.ajax({
            type: "POST",
            url: ajaxUrl + "byGame",
            data: {gameId:gameId}
        }).done(updateVideoByData);
    }

    function updateVideoByData(data) {
        text = "<a class=\"btn btn-primary sectionbutton\" onclick=\"add()\">"+
        "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>"+
        "    Add video"+
        "</a>";
        for (var i = 0; i < data.length; i++) {
            text = text + "<div class=\"section\">"+
            "<a onclick=\"setId('"+data[i].youtubeId+"','"+data[i].startTime+"')\"> <img class=\"sectionimange\""+
            "src=\"https://img.youtube.com/vi/"+data[i].youtubeId+"/1.jpg\"><br>"+
            "    </a>"+
            "    <p>"+data[i].name+"</p>"+
            "    </div>";
        }
        $("#footer").html(text);
    }

    function save() {
        $.ajax({
            type: "POST",
            url: ajaxUrl,
            data: $("#detailsForm").serialize()
        }).done(function () {
            $("#videoAddition").modal("hide");
            updateVideo();
        });
    }

    function fileChanged() {
        var input = document.querySelector('file-input');
        var curFiles = input.files;
        if(curFiles.length == 0) {
            alert("No files")
        } else {
            alert(curFiles[0].name);
        }
    }
</script>
</html>