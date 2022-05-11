package com.gmail.parking_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private View car1,car2,car3,car4,car5,car6,car7,car8,car9,car10;
    private Button send_button, pay_button,test_button;
    private Socket socket;
    private static String SERVER_IP = "192.168.0.5";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_button = findViewById(R.id.send_button);
        pay_button = findViewById(R.id.pay_button);
        test_button = findViewById(R.id.test_button);
        this.initialize_view();

        pay_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent( MainActivity.this, Show_pay_Activity.class );
            startActivity( intent );
            }
        });

        send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Thread socket = new Thread(new TCPServer());
                socket.start();

            }

        });

        test_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(car1.getVisibility() == View.INVISIBLE)
                {
                    car1.setVisibility(View.VISIBLE);
                }
                else car1.setVisibility(View.INVISIBLE);
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


    public void initialize_view()
    {
        car1 = findViewById(R.id.car1);
        car2 = findViewById(R.id.car2);
        car3 = findViewById(R.id.car3);
        car4 = findViewById(R.id.car4);
        car5 = findViewById(R.id.car5);
        car6 = findViewById(R.id.car6);
        car7 = findViewById(R.id.car7);
        car8 = findViewById(R.id.car8);
        car9 = findViewById(R.id.car9);
        car10 = findViewById(R.id.car10);


    }


}
