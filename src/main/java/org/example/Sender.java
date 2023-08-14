package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Sender {
    protected InetSocketAddress host;
    protected DatagramSocket socket;
    private long lastResponse;

    private long responseLatency;
    private long lastSent;

    public Sender() {
    }

    public Sender(InetSocketAddress host) {
        this.host = host;
        this.lastResponse = -1;
        this.responseLatency = -1;
        this.lastSent = -1;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(5000);
            socket.setTrafficClass(0x04);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    protected DatagramPacket receive() {
        byte[] buf = new byte[4096];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(recv);
        } catch (IOException e) {
            System.out.println(host.getAddress().getHostAddress() + ":" + host.getPort() + " did not respond.");
            return null;
        }

        if (recv.getLength() > 0 && recv.getData()[4] != Request.INFO_RESPONSE) {
            System.out.println("ERROR: wrong packet received, expected 0x" + Request.INFO_RESPONSE);
            return null;
        }

        lastResponse = System.currentTimeMillis();
        responseLatency = lastResponse - lastSent;
        return recv;
    }

    protected void send(byte[] data) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length, host);
        lastSent = System.currentTimeMillis();
        socket.send(packet);
    }

    public long getLastResponse() {
        return lastResponse;
    }

    public long getResponseLatency() {
        return responseLatency;
    }

    public long getLastSent() {
        return lastSent;
    }
}
