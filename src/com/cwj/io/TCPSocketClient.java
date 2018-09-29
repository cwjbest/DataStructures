package com.cwj.io;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by cwj on 18-8-28.
 *
 */
public class TCPSocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 8888));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
