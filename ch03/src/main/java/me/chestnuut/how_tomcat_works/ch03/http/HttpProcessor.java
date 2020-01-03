package me.chestnuut.how_tomcat_works.ch03.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpProcessor {

    public static void process(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        byte[] bytes = new byte[2048];
        is.read(bytes);
        String requestInput = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(requestInput);
    }
}
