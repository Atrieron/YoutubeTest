var ajaxUrl = "ajax/video/";
var gameId;
var player;
var addButtonText = "";

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
    text = addButtonText + "";
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