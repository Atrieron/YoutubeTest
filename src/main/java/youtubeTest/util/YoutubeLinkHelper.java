package youtubeTest.util;

import youtubeTest.model.YoutubeVideo;

public class YoutubeLinkHelper {
	private final static String YOUTUBE_SHORT_HEADER = "https://youtu.be/";
	private final static String YOUTUBE_LONG_HEADER = "https://www.youtube.com/watch?";
	
	public static YoutubeVideo getByLink(String link, String name) {
		if(link.startsWith(YOUTUBE_SHORT_HEADER)) {
			String[] parameters = link.substring(YOUTUBE_SHORT_HEADER.length()).split("/?t=");
			if(parameters.length>0) {
				YoutubeVideo ret = new YoutubeVideo();
				ret.setName(name);
				ret.setYoutubeId(parameters[0].substring(0,parameters[0].length()-1));
				if(parameters.length==2) {
					ret.setStartTime(Integer.parseInt(parameters[1]));
				}
				return ret;
			}
		} else if(link.startsWith(YOUTUBE_LONG_HEADER)) {
			boolean hasId = false;
			YoutubeVideo ret = new YoutubeVideo();
			ret.setName(name);
			for(String param:link.substring(YOUTUBE_LONG_HEADER.length()).split("&/?")) {
				if(param.startsWith("v=")) {
					ret.setYoutubeId(param.substring(2));
					hasId = true;
				} else if(param.startsWith("t=")) {
					ret.setStartTime(Integer.parseInt(param.substring(2)));
				}					
			}
			if(hasId) {
				return ret;
			}
		}
		throw new IllegalArgumentException();
	}
}