package com.gmail.parking_system;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CarnumRequest extends StringRequest {

    final static private String URL = "http://swj627.ivyro.net/FindCarNum.php";
    private Map<String, String> map;
    ArrayList<String> Carnum = new ArrayList<>();


    public CarnumRequest(Response.Listener<String> listener){

        super(Method.GET, URL, new Response.Listener<String>() {  //응답을 문자열로 받아서 여기다 넣어달란말임(응답을 성공적으로 받았을 떄 이메소드가 자동으로 호출됨
            @Override
            public void onResponse(String response) {

                for(int i=0;i<response.length();i++)
                {
                    try{

                    }


                        catch(ArrayIndexOutOfBoundsException e){

                        }
                        finally{

                    }
                }
            }
        }, null);




    }

}
