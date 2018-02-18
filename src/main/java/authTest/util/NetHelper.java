package authTest.util;

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
}