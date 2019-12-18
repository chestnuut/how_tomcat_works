package me.chestnuut.how_tomcat_works.ch01;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Stands for a simple HTTP response
 */
public class StaticResponse {

    public static final String SUCCESS = "HTTP/1.1 200 OK\r\n\r\n";
    public static final String ERROR_MSG =
            "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";

    /**
     * write static resources file into os
     * @param os socket outputStream
     */
    public void sendStaticResource(OutputStream os, String uri) throws IOException {
        FileInputStream fis = null;
        try {
            File indexFile = new File(HttpServer.WEB_ROOT, uri);
            if (!indexFile.exists()) {
                os.write(ERROR_MSG.getBytes());
            } else {
                fis = new FileInputStream(indexFile);
                os.write(SUCCESS.getBytes());
                IOUtils.copy(fis, os);
                os.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
