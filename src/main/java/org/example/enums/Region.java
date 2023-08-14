package org.example.enums;

public enum Region {


    AFRICA((byte) 0x07),
    ALL((byte) 0xFF),
    ASIA((byte) 0x04),
    AUSTRALIA((byte) 0x05),
    EUROPE((byte) 0x03),
    MIDDLE_EAST((byte) 0x06),
    SOUTH_AMERICA((byte) 0x02),
    US_EAST_COAST((byte) 0x00),
    US_WEST_COAST((byte) 0x01);

    private final byte i;

    Region(byte i) {
        this.i = i;
    }

    public byte getI() {
        return i;
    }
}
