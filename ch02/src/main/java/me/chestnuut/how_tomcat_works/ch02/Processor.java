package me.chestnuut.how_tomcat_works.ch02;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Processor {

    public static void processServletRequest(Request request, Response response) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, ServletException, IOException {
        // uri looks like /PrimitiveServlet.do
        String servletName = "me.chestnuut.how_tomcat_works.ch02.servlet." + StringUtils.removeEnd(request.getUri(),".do").substring(1);
        Class myClass = Class.forName(servletName);
        Servlet servlet = (Servlet) myClass.newInstance();
        RequestFacade facadeReq = new RequestFacade(request);
        servlet.service(facadeReq, response);
    }

    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator  + "webroot";
    public static final String SUCCESS = "HTTP/1.1 200 OK\r\n\r\n";
    public static final String ERROR_MSG =
            "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";

    public static void processStaticResource(Request request, Response response) throws IOException {
        FileInputStream fis = null;
        OutputStream os = response.getOs();
        try {
            File indexFile = new File(WEB_ROOT, request.getUri());
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
