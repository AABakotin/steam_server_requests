package org.example.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    private byte protocol;
    private String name;
    private String map;
    private String folder;
    private String game;
    private int appid;
    private int players;
    private int maxPlayers;
    private int bots;
    private char type;
    private char env;
    private byte visibility;
    private byte vac;
    private String version;
    private byte EDF;
    private int gamePort;
    private Long steamid;
    private int sourceTVPort;
    private String sourceTVName;
    private String descTags;
    private Long gameid;

    private byte[] challenge;
    private boolean challengeValid;

    private HashMap<String, String> rules;

    public Parser() {

        this.challenge = new byte[4];
        this.rules = new HashMap<>();
        this.challengeValid = false;

    }

    public void parseInfo(DatagramPacket packet) throws IOException {
        if (packet == null){
            return;
        }
        SteamInputStream sis = new SteamInputStream(new ByteArrayInputStream(packet.getData()));
        sis.skipBytes(5);

        this.protocol = sis.readByte();
        this.name = sis.readString();
        this.map = sis.readString();
        this.folder = sis.readString();
        this.game = sis.readString();
        this.appid = sis.readSteamShort();
        this.players = sis.read();
        this.maxPlayers = sis.read();
        this.bots = sis.read();
        this.type = (char) sis.read();
        this.env = (char) sis.read();
        this.visibility = sis.readByte();
        this.vac = sis.readByte();
        this.version = sis.readString();
        this.EDF = sis.readByte();

        if ((EDF & 0x80) > 0) {
            this.gamePort = sis.readSteamShort();
        }
        if ((EDF & 0x10) > 0) {
            this.steamid = sis.readLong();
        }
        if ((EDF & 0x40) > 0) {
            this.sourceTVPort = sis.readSteamShort();
            this.sourceTVName = sis.readString();
        }
        if ((EDF & 0x20) > 0) {
            this.descTags = sis.readString();
        }
        if ((EDF & 0x01) > 0) {
            this.gameid = sis.readLong();
        }

    }

    public byte getProtocol() {
        return protocol;
    }

    public String getName() {
        return name;
    }

    public String getMap() {
        return map;
    }

    public String getFolder() {
        return folder;
    }

    public String getGame() {
        return game;
    }

    public int getAppid() {
        return appid;
    }

    public int getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getBots() {
        return bots;
    }

    public char getType() {
        return type;
    }

    public char getEnv() {
        return env;
    }

    public byte getVisibility() {
        return visibility;
    }

    public byte getVac() {
        return vac;
    }

    public String getVersion() {
        return version;
    }

    public byte getEDF() {
        return EDF;
    }

    public int getGamePort() {
        return gamePort;
    }

    public Long getSteamid() {
        return steamid;
    }

    public int getSourceTVPort() {
        return sourceTVPort;
    }

    public String getSourceTVName() {
        return sourceTVName;
    }

    public String getDescTags() {
        return descTags;
    }

    public Long getGameid() {
        return gameid;
    }

    public byte[] getChallenge() {
        return challenge;
    }

    public boolean isChallengeValid() {
        return challengeValid;
    }



    public HashMap<String, String> getRules() {
        return rules;
    }
}

