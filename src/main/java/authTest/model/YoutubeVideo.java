package authTest.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="videos")
public class YoutubeVideo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
    @Column(name = "name", nullable = false)
    protected String name;
    
    @Column(name = "youtube_id")
    private String youtubeId;
    
    @Column(name = "startTime")
    private int startTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;

	public YoutubeVideo() {
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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public YoutubeVideo(String name, String youtubeId, int startTime) {
		this.name = name;
		this.youtubeId = youtubeId;
		this.startTime = startTime;
	}
}