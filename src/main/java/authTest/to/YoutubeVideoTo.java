package authTest.to;

import java.io.Serializable;

public class YoutubeVideoTo implements Serializable {
    private Integer id;
    private String name;
    private String youtubeId;
    private int startTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public YoutubeVideoTo(Integer id, String name, String youtubeId, int startTime) {
        this.id = id;
        this.name = name;
        this.youtubeId = youtubeId;
        this.startTime = startTime;
    }
}