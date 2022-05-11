package com.gmail.parking_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private EditText carnumber;
    private View car1,car2,car3,car4,car5,car6,car7,car8,car9,car10;
    private Button send_button, pay_button,test_button,test_button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send_button = findViewById(R.id.send_button);
        pay_button = findViewById(R.id.pay_button);
        test_button = findViewById(R.id.test_button);
        test_button2 = findViewById(R.id.test_button2);
        carnumber = findViewById(R.id.carnumber);
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

        test_button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CarNumber = carnumber.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success) {
                                Toast.makeText(getApplicationContext(), String.format("차량 정보가 DB에 있습니다."), Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(getApplicationContext(), "차량 정보가 DB에 없습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                CarnumRequest carnumrequest = new CarnumRequest( CarNumber,listener );
                RequestQueue queue = Volley.newRequestQueue( MainActivity.this );
                queue.add( carnumrequest );

            }
        });





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
