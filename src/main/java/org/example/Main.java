package org.example;


import org.example.sender.ISender;
import org.example.sender.Sender;

import java.util.ArrayList;
import java.util.List;


import static org.example.enums.Constants.A2S_INFO;


public class Main {

    private static final String addressDusk = "185.189.255.183";
    private static final int portDuskCherno = 2403;
    private static final int portDuskNamalsk = 2603;


    public static void main(String[] args) {

        List<Integer> portlist = new ArrayList<>();
        portlist.add(2403);
        portlist.add(2603);
        for (Integer i: portlist) {
            ISender sender = new Sender.Configuration()
                    .setAddress(addressDusk, i)
                    .build();

            sender.send(A2S_INFO);
            System.out.println(sender.receive());
        }
    }
}