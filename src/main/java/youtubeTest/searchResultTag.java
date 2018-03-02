package youtubeTest;

import youtubeTest.to.GameSearchTo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class searchResultTag extends SimpleTagSupport {
    private GameSearchTo gameSearchTo;

    public void setGameSearchTo(GameSearchTo gameSearchTo) {
        this.gameSearchTo = gameSearchTo;
    }

    public void doTag() throws JspException, IOException {
        String text = "";
        if(gameSearchTo.getId()==null) {
            text = text + "<div class='listItem'><div class='columnLeft'>" +
                    "<img style=\"display: inline-block; width: 90%; height: 50px\" src=\"" + gameSearchTo.getImg_path() + "\"></div>" +
                    "<div class='columnRight'><p>" + gameSearchTo.getName() + "</p><a class='btn btn-primary sectionbutton' onclick='voteSteam(" + gameSearchTo.getSteamId() + ")'><span class=\"glyphicon glyphicon-plus\" aria-hidden=\"true\"></span>Vote</a>" +
                    "</div></div><br>";
        } else {
            text = text + "<div class='listItem'><div class='columnLeft'>" +
                    "<a href=\"game/"+gameSearchTo.getId()+"\"><img style=\"display: inline-block; width: 90%; height: 50px\" src=\"" + gameSearchTo.getImg_path() + "\"></a></div>" +
                    "<div class='columnRight'><a href=\"game/"+gameSearchTo.getId()+"\">" + gameSearchTo.getName() + "</a>" +
                    "</div></div><br>";
        }

        JspWriter out = getJspContext().getOut();
        out.println(text);
    }
}