package org.example.parser;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class SteamInputStream extends DataInputStream {

    public SteamInputStream(ByteArrayInputStream inputStream) {
        super(inputStream);
    }

    public String readString() throws IOException {
        String res = "";
        byte b = readByte();
        while (b != 0x00) {
            res += (char) b;
            b = readByte();
        }
        return res;
    }

    public int readSteamLong() throws IOException {
        return Integer.reverseBytes(readInt());
    }

    public float readSteamFloat() throws IOException {
        return Float.intBitsToFloat(Integer.reverseBytes(readInt()));
    }

    public int readSteamShort() throws IOException {
        return Short.reverseBytes(readShort());
    }

}