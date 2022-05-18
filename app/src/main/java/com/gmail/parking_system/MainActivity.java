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

    public TextView carnum;
    public View car1,car2,car3,car4;
    private Button show_car_button, pay_button, carnum_validate_button;
    private String Carnumdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_car_button = findViewById(R.id.show_car_button);
        pay_button = findViewById(R.id.pay_button);
        carnum_validate_button = findViewById(R.id.carnum_validate_button);
        carnum = findViewById(R.id.carnum);

        this.initialize_view();


        pay_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent( MainActivity.this, Show_pay_Activity.class );
            startActivity( intent );
            }
        });

        ConnectTCP tcp1 = new ConnectTCP(this,8080,1);
        ConnectTCP tcp2 = new ConnectTCP(this,8081,2);
        ConnectTCP tcp3 = new ConnectTCP(this,8082,3);
        ConnectTCP tcp4 = new ConnectTCP(this,8083,4);


        show_car_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("hello world");
                Thread t1 = new Thread(tcp1);
                Thread t2 = new Thread(tcp2);
                Thread t3 = new Thread(tcp3);
                Thread t4 = new Thread(tcp4);
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                System.out.println("hello world2");

            }

        });

        ConnectTCP_Carnum tcp5 = new ConnectTCP_Carnum(this,8084);
        ConnectTCP_Servo tcp6 = new ConnectTCP_Servo(8085);

        carnum_validate_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carnumdata = tcp5.get_inputdata();
                Thread t5 = new Thread(tcp5);
                t5.start();
                Carnumdata = carnum.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success) {

                                Thread t6 = new Thread(tcp6);
                                t6.start();

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
                CarnumRequest carnumrequest = new CarnumRequest( Carnumdata,listener );
                RequestQueue queue = Volley.newRequestQueue( MainActivity.this );
                queue.add( carnumrequest );

            }
        });





    }

    private void initialize_view()
    {
        car1 = findViewById(R.id.car1);
        car2 = findViewById(R.id.car2);
        car3 = findViewById(R.id.car3);
        car4 = findViewById(R.id.car4);


    }






}
