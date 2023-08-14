package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Request {

    public static final byte[] HEADER = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    public static final byte A2S_INFO = 0x54;
    public static final byte INFO_RESPONSE = 0x49;
    public static final byte[] INFO_MSG = "Source Engine Query".getBytes();

    public static byte[] info() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(HEADER);
            baos.write(A2S_INFO);
            baos.write(INFO_MSG);
            baos.write(0x00);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos.toByteArray();
    }

}
