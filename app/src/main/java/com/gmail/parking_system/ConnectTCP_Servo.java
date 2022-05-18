package com.gmail.parking_system;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectTCP_Servo implements Runnable{

    private Socket socket;
    private static String SERVER_IP = "192.168.229.29";
    private String inputdata;
    public int port;
    private String senddata="1";
    private static OutputStream os;

    public ConnectTCP_Servo(int port){
        this.port = port;

    }


    @Override
    public void run(){
        try{
            socket = new Socket(SERVER_IP,port);



        }catch(Exception e){
            e.printStackTrace();
        }




    }

}
