package com.gmail.parking_system;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


public class ConnectTCP implements Runnable {

    private Socket socket;
    private static String SERVER_IP = "192.168.0.5";
    private String inputdata;
    public BufferedReader bufferedReader;
    public Context mContext;
    public Handler handler = new Handler();
    public int port, carnumber;


    public ConnectTCP(Context context, int port, int carnumber){
        mContext = context;
        this.port = port;
        this.carnumber = carnumber;
    }



    @Override
    public void run() {



        try {

            socket = new Socket(SERVER_IP,port);

            while (true) {
                bufferedReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                inputdata = bufferedReader.readLine();
                System.out.println("input data :" + inputdata);

                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        if(inputdata.equals("1")){
                            showView(carnumber);
                        }
                        else{
                            hideView(carnumber);
                        }

                    }


                });



                Thread.sleep(1000);

            }


        } catch (Exception e) {
            //text.setText("서버에러");
            e.printStackTrace();
        }




    }

    private void showView(int carnumber) {
        switch(carnumber){
            case 1:
                ((MainActivity) mContext).car1.setVisibility(View.VISIBLE);
                break;
            case 2:
                ((MainActivity) mContext).car2.setVisibility(View.VISIBLE);
                break;
            case 3:
                ((MainActivity) mContext).car3.setVisibility(View.VISIBLE);
                break;
            case 4:
                ((MainActivity) mContext).car4.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void hideView(int carnumber) {
        switch(carnumber){
            case 1:
                ((MainActivity) mContext).car1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                ((MainActivity) mContext).car2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                ((MainActivity) mContext).car3.setVisibility(View.INVISIBLE);
                break;
            case 4:
                ((MainActivity) mContext).car4.setVisibility(View.INVISIBLE);
                break;
        }
    }


}







