package org.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Request {

    public static final byte[] HEADER = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    public static final byte INFO_CODE = 0x54;
    public static final byte INFO_RESPONSE = 0x49;
    public static final byte[] INFO_MSG = "Source Engine Query".getBytes();

    public static byte[] INFO() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(HEADER);
        baos.write(INFO_CODE);
        baos.write(INFO_MSG);
        baos.write(0x00);
        return baos.toByteArray();
    }





}
