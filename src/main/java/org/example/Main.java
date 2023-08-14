package org.example;

import org.example.parser.Parser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;


public class Main {

    private static final String address = "195.18.27.98";
    private static final int port = 2310;



    public static void main(String[] args) {

        try {
            Sender sender = new Sender(new InetSocketAddress(address, port));
            sender.send(Request.info());
            DatagramPacket receive = sender.receive();
            Parser parser = new Parser();
            parser.parseInfo(receive);
            System.out.println(
                    parser.getName() + "\n"
                            + parser.getPlayers() + "/" + parser.getMaxPlayers() + "\n"
                            + parser.getMap() + "\n"
                            + parser.getVersion() + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}