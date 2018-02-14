<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<script type="text/javascript"	src="webjars/jquery/3.2.1/dist/jquery.min.js"></script>

	<link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
</head>
<body>
	<div style="float: left; width: 74%;">

		<div id="playerdiv">
            <c:if test="${fn:length(videos) gt 0}">
                <iframe id="ytplayer" type="text/html" width="640" height="360"
						src="https://www.youtube.com/embed/${videos[0].youtubeId}?start=${videos[0].startTime}" frameborder="0"
                        allowfullscreen></iframe>
            </c:if>
		</div>
	</div>
	<div style="float: right; width: 24%;" align="center">
		<a class="btn btn-primary" onclick="add()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			Add
		</a>
		<c:forEach items="${videos}" var="video">
			<div>
				<a onclick="setId('${video.youtubeId}','${video.startTime}')"> <img
					src="https://img.youtube.com/vi/${video.youtubeId}/1.jpg"><br>
				</a>
				<p>${video.name}</p>
			</div>
		</c:forEach>
	</div>

	<div class="modal fade" id="editRow">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h2 class="modal-title" id="modalTitle"></h2>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="detailsForm">
						<input type="hidden" id="id" name="id">

						<div class="form-group">
							<label for="name" class="control-label col-xs-3">Name</label>

							<div class="col-xs-9">
								<input type="text" class="form-control" id="name" name="name"
									   placeholder="name">
							</div>
						</div>
						<div class="form-group">
							<label for="link" class="control-label col-xs-3">Link</label>

							<div class="col-xs-9">
								<input type="text" class="form-control" id="link" name="link"
									   placeholder="link">
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
	function play(id, time) {
		var html = '';

		html += '<iframe id="ytplayer" type="text/html" width="640" height="360"';
		html += ' src="https://www.youtube.com/embed/'+id;
		if(time!="0")
		    html += '?start='+time;
		html += '" frameborder="0" allowfullscreen/>';

		return html;
	};

	function setId(ytid, time) {
		document.getElementById("playerdiv").innerHTML = play(ytid, time);
	}

    function add() {
        $("#modalTitle").html("Add video");
        $('#detailsForm').find(":input").val("");
        $("#editRow").modal();
    }
</script>
</html>