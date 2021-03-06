package com.gmail.parking_system;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private Button show_car_button, carnum_validate_button, carnum_show_button, exit_button;
    private String Carnumdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_car_button = findViewById(R.id.show_car_button);
        carnum_validate_button = findViewById(R.id.carnum_validate_button);
        carnum_show_button = findViewById(R.id.carnum_show_button);
        exit_button = findViewById(R.id.exit_button);
        carnum = findViewById(R.id.carnum);

        this.initialize_view();


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

        carnum_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t5 = new Thread(tcp5);
                t5.start();

            }
        });

        carnum_validate_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
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

                                Toast.makeText(getApplicationContext(), String.format("?????? ????????? DB??? ????????????."), Toast.LENGTH_SHORT).show();


                            } else {
                                Toast.makeText(getApplicationContext(), "?????? ????????? DB??? ????????????.", Toast.LENGTH_SHORT).show();
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

        exit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("????????? ?????????????????????????");
                builder.setTitle("?????? ?????????")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("?????? ?????????");
                alert.show();
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
