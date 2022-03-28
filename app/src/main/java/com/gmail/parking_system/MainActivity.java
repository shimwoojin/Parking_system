package com.gmail.parking_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private Button send_button;
    private Socket socket;
    private static String SERVER_IP = "192.168.0.5";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_button = findViewById(R.id.send_button);

        send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Thread socket = new Thread(new TCPServer());
                socket.start();

            }

        });


    }
    public class TCPServer implements Runnable {

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



}
