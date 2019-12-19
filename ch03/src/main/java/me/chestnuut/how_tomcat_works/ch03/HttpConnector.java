package me.chestnuut.how_tomcat_works.ch03;

public class HttpConnector implements Runnable{

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

    }
}
