$(function()
    {
        addButtonText = "<a class=\"btn btn-primary sectionbutton\" onclick=\"add()\">"+
            "<span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>"+
            "    Add video"+
            "</a>";
        $('#file-input').change(function(){
            var filesSelected = document.getElementById("file-input").files;
            if (filesSelected.length > 0)
            {
                var fileToLoad = filesSelected[0];
                var fileReader = new FileReader();
                fileReader.onload = function(fileLoadedEvent)
                {
                    var imageLoaded = document.getElementById("gamePic");
                    imageLoaded.src = fileLoadedEvent.target.result;
                };
                fileReader.readAsDataURL(fileToLoad);
            }
        });

        $('#uploadButton').click(function(){
            var form = new FormData();
            form.append("id",gameId);
            form.append("image", $("#file-input")[0].files[0]);

            $.ajax({
                type: "POST",
                url: "ajax/game/image/",
                processData: false,
                contentType: false,
                data: form
            });
        });

        $('#videoSaveButton').click(function(){
            $.ajax({
                type: "POST",
                url: ajaxUrl,
                data: $("#detailsForm").serialize()
            }).done(function () {
                $("#videoAddition").modal("hide");
                updateVideo();
            });
        });
    }
);