package me.chestnuut.how_tomcat_works.ch03;

public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
