package com.gmail.parking_system;

import java.net.Socket;



public class TCPServer implements Runnable {

    private Socket socket;
    private static String SERVER_IP = "192.168.0.5";

    @Override
    public void run() {

        try {
            socket = new Socket(SERVER_IP,9999);

        } catch (Exception e) {
            //text.setText("서버에러");
            e.printStackTrace();
        }
    }
}
