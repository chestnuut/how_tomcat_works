package me.chestnuut.how_tomcat_works.ch03;

import me.chestnuut.how_tomcat_works.ch03.http.HttpProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Run server in a separate thread
 */
public class HttpConnector implements Runnable{

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public static final String SHUTDOWN_CMD = "/SHUTDOWN";

    private boolean shutdown;

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (!shutdown) {
                try (Socket socket = serverSocket.accept()) {
                    HttpProcessor.process(socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
