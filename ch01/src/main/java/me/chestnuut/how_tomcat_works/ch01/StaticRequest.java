package me.chestnuut.how_tomcat_works.ch01;

import com.google.common.io.ByteSource;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * stands for a simple Http request
 *
 * This class is for get the request uri from socket InputStream
 */
public class StaticRequest {

    /**
     * InputStream to String and distinguish uri
     */
    public String parse(InputStream is) {
        String rs;
        StringBuilder request = new StringBuilder(2048);
        byte[] buffer = new byte[2048];
        try {
            is.read(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        rs = new String(buffer, StandardCharsets.UTF_8);
        System.out.println("get request string: " + rs);
        return parseUri(rs);
    }

    public String parseII(InputStream is) {
        String rs = null;
        try {
            // IOUtils.toString will hang here, why?
            rs = IOUtils.toString(is, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseUri(rs);
    }

    public String parseIII(InputStream is) {
        String rs = "";
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return is;
            }
        };
        try {
            rs = byteSource.asCharSource(StandardCharsets.UTF_8).read();
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseUri(rs);
    }

    /**
     * parse /index.html in HTTP request
     * GET /index.html HTTP/1.1
     * @param requestString InputSteam in socket
     * @return uri of the string
     */
    public String parseUri(String requestString) {
        if (requestString == null || requestString.isEmpty()) {
            return null;
        }
        int i = requestString.indexOf(' ');
        if (i == -1) return null;
        int j = requestString.indexOf(' ', i + 1);
        if (j > i) {
            return requestString.substring(i + 1, j);
        }
        return null;
    }
}
