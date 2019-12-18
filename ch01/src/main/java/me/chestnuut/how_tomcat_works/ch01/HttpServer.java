package me.chestnuut.how_tomcat_works.ch01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    public static final String SHUTDOWN_CMD = "/SHUTDOWN";

    private boolean shutdown;

    /**
     * create server socket on 8080, take socket
     */
    public void await() throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            try {
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                StaticRequest request = new StaticRequest();
                String uri = request.parse(is);
                // String uri = request.parseII(is);
                // String uri = request.parseIII(is);
                if (!SHUTDOWN_CMD.equals(uri)) {
                    StaticResponse response = new StaticResponse();
                    response.sendStaticResource(os, uri);
                } else {
                    shutdown = true;
                }
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.await();
    }
}
