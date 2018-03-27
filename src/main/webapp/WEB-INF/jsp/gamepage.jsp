<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${game.name}</title>
    <base href="${pageContext.request.contextPath}/"/>

    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/gamepage.css">

    <script type="text/javascript"	src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    <script type="text/javascript" src="resources/js/gamepage.js" defer></script>
</head>
<body>
    <div>
        <div id ="columnLeft">
            <label for="file-input" class="input-label">
                <input type="file" id="file-input" onchange="fileChanged()" accept="image/*">
                Change
            </label>
            <input type="button" value="Upload" onclick="uploadImage()"/>
            <img style="display: inline-block; width: 90%; height: 130px" id="gamePic" src="${pageContext.request.contextPath}/imageController/${game.id}"/>
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
                                <button class="btn btn-primary" id="videoSaveButton" type="button">
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
</html>