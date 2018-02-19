package authTest.util;

import authTest.model.Game;
import authTest.model.Image;
import authTest.to.GameSearchTo;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class NetHelper {
    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static byte[] getImage(String urlString) throws IOException {
        InputStream stream = null;
        try {
            URL url = new URL(urlString);
            stream = url.openStream();
            int read = 0;
            byte[] buf = new byte[1024];
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            while ((read = stream.read(buf)) != -1)
                bs.write(buf, 0, read);
            return bs.toByteArray();
        } finally {
            if (stream != null)
                stream.close();
        }
    }

    public static GameSearchTo getBySteamId(String steamId) throws Exception {
        String st = NetHelper.readUrl("http://store.steampowered.com/api/appdetails?appids="+steamId);
        String substr = "{\"" + steamId + "\":";
        if(st.startsWith(substr)) {
            st = st.substring(substr.length());
            st = st.substring(0, st.length()-1);
            if(st.startsWith("{\"success\":true,\"data\":")) {
                st = st.substring("{\"success\":true,\"data\":".length());
                st = st.substring(0, st.length()-1);
                JSONObject jo = new JSONObject(st);
                GameSearchTo game = new GameSearchTo();
                game.setSteamId(steamId);
                if(jo.has("name")){
                    game.setName(jo.get("name").toString());
                }
                if(jo.has("short_description")){
                    game.setDescriprion(jo.get("short_description").toString());
                }
                if (jo.has("header_image")) {
                    game.setImg_path(jo.get("header_image").toString());
                }
                return game;
            }
        }
        return null;
    }
}