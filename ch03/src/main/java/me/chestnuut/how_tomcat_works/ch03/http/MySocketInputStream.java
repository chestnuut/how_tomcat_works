package me.chestnuut.how_tomcat_works.ch03.http;


import org.apache.catalina.util.StringManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MySocketInputStream extends InputStream {

    /**
     * CR.
     */
    private static final byte CR = (byte) '\r';

    /**
     * LF.
     */
    private static final byte LF = (byte) '\n';

    /**
     * SP.
     */
    private static final byte SP = (byte) ' ';

    /**
     * HT.
     */
    private static final byte HT = (byte) '\t';

    /**
     * COLON.
     */
    private static final byte COLON = (byte) ':';

    /**
     * Lower case offset.
     */
    private static final int LC_OFFSET = 'A' - 'a';

    private InputStream is;
    private byte[] buf;
    // last valid byte index + 1
    int count;
    // position in the buf byte array
    int pos;

    public MySocketInputStream(InputStream in, int size) {
        this.is = in;
        buf = new byte[size];
    }

    protected static StringManager sm = StringManager.getManager(Constants.PACKAGE);


    @Override
    public int read() throws IOException {
        return 0;
    }
}
