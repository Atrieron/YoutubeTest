package youtubeTest.to;

import java.io.Serializable;

public class GameSearchTo implements Serializable {
    private Integer id;
    private String name;
    private String steamId;
    private String img_path;
    private String descriprion;

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

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

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public GameSearchTo() {
    }

    public GameSearchTo(Integer id, String name, String steamId, String img_path) {
        this.id = id;
        this.name = name;
        this.steamId = steamId;
        this.img_path = img_path;
    }
}