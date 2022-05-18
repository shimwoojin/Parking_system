package com.gmail.parking_system;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectTCP_Carnum implements  Runnable{
    private Socket socket;
    private static String SERVER_IP = "192.168.229.123";
    private String inputdata;
    public BufferedReader bufferedReader;
    public int port;
    public Handler handler = new Handler();
    public Message msg;
    public Context myContext;

    public ConnectTCP_Carnum(Context context, int port){
        myContext = context;
        this.port = port;

    }

    @Override
    public void run(){
        try {

            socket = new Socket(SERVER_IP,port);

                Thread.sleep(10000);
                bufferedReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                inputdata = bufferedReader.readLine();
                handler.post(new Runnable(){
                @Override
                public void run(){
                    ((MainActivity)myContext).carnum.setText(inputdata);

                }


            });



                System.out.println("input data :" + inputdata);

                socket.close();



                Thread.sleep(1000);




        } catch (Exception e) {
            //text.setText("서버에러");
            e.printStackTrace();
        }

    }

    public String get_inputdata(){
        return inputdata;
    }



}
