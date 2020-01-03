package me.chestnuut.how_tomcat_works.ch03.http;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

public class MySocketInputStreamTest {

    @Test
    public void testRestrictTo255() {
        int t = 256;
        Assert.assertEquals(t & 0xff, 0);
    }

    @Test
    public void learnInputStream() throws IOException {
        String ini = "text";
        InputStream targetStream = new ByteArrayInputStream(ini.getBytes());
        int t = targetStream.read();
        System.out.println((char)t);
    }

}