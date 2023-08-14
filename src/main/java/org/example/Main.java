package org.example;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class Main {

    private static final String address = "195.18.27.98";
    private static final int port = 2310;




    public static void main(String[] args) throws IOException {
//        new Main().start(address, port);
       Sender sender = new Sender(new InetSocketAddress(address,port));
       sender.send(Request.INFO());
        DatagramPacket recieve = sender.recieve(Request.INFO_RESPONSE);
        Parser parser = new Parser();
        parser.parseInfo(recieve);
        System.out.println(parser.getPlayers() + "/"+ parser.getMaxPlayers()+"\n"
        + parser.getName());
    }

    public void start(String host, int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            InetSocketAddress address = new InetSocketAddress(host,port);
            byte[] sendData = Request.INFO();
            byte[] receiveData = new byte[4096];
            DatagramPacket packet = new DatagramPacket(
                    sendData,
                    sendData.length,
                    address.getAddress(),
                    address.getPort());

            datagramSocket.send(packet);

            //Получаем ответ receivePacket
            DatagramPacket receivePacket = new DatagramPacket(
                    receiveData,
                    receiveData.length);
            datagramSocket.receive(receivePacket);

            String sentence = new String( receivePacket.getData(), 4,
                    receivePacket.getLength() );
            System.out.println("RECEIVED: " + sentence);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}