var imageChanged = false;
var imageSrc = "";

$(function()
    {
        addButtonText = "<a class=\"btn btn-primary sectionbutton\" onclick=\"add()\">"+
            "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>"+
            "    Add video"+
            "</a>";

        rebuildImageEditMenu();
        imageSrc = document.getElementById("gamePic").currentSrc;
    }
);

function rebuildImageEditMenu(){
    text = "<label for=\"file-input\" class=\"input-label\">"+
        "<input type=\"file\" id=\"file-input\" accept=\"image/*\" onchange=\"fileChanged()\" />"+
        "    Change "+
        "    </label>";
    if(imageChanged) {
        text = text + "<input type=\"button\" value=\"Upload\" id=\"uploadButton\" onclick=\"uploadImage()\" />";
        text = text + "<input type=\"button\" value=\"Redo\" id=\"removeImageButton\" onclick=\"redoImage()\" />";
    }
    $("#imagecontrol").html(text);
}

function fileChanged(){
    var filesSelected = document.getElementById("file-input").files;
    if (filesSelected.length > 0)
    {
        var fileToLoad = filesSelected[0];
        var fileReader = new FileReader();
        fileReader.onload = function(fileLoadedEvent)
        {
            var imageLoaded = document.getElementById("gamePic");
            imageLoaded.src = fileLoadedEvent.target.result;
            imageChanged = true;
            rebuildImageEditMenu();
        };
        fileReader.readAsDataURL(fileToLoad);
    }
}

function uploadImage(){
    var form = new FormData();
    form.append("id",gameId);
    form.append("image", $("#file-input")[0].files[0]);

    $.ajax({
        type: "POST",
        url: "ajax/game/image/",
        processData: false,
        contentType: false,
        data: form
    }).done(function () {
        imageChanged = false;
        rebuildImageEditMenu();
    });
}

function redoImage() {
    $('#gamePic').attr('src', imageSrc);
    imageChanged = false;
    rebuildImageEditMenu();
}