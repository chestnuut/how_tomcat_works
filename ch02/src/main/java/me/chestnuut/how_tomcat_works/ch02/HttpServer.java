package me.chestnuut.how_tomcat_works.ch02;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This HttpServer runs a program, able to redirect call to static processor or servlet processor
 */
public class HttpServer {

    public static final String SHUTDOWN_CMD = "/SHUTDOWN";

    private boolean shutdown;

    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() throws IOException {
        ServerSocket server = new ServerSocket(8080);
        while (!shutdown) {
            try (Socket socket = server.accept()) {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                Request req = new Request(is);
                req.parse();

                Response resp = new Response(os);
                String uri = req.getUri();
                if (uri.endsWith(".do")) {
                    // a servlet request
                    Processor.processServletRequest(req, resp);
                } else {
                    if (!SHUTDOWN_CMD.equals(req.getUri())) {
                        Processor.processStaticResource(req, resp);
                    } else {
                        shutdown = true;
                    }
                }
            } catch (InstantiationException | ServletException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
