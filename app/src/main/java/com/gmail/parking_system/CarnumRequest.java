package com.gmail.parking_system;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CarnumRequest extends StringRequest {

    final static private String URL = "http://swj627.ivyro.net/FindCarNum.php";
    private Map<String, String> map;

    public CarnumRequest(String CarNumber, Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("CarNumber", CarNumber);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}
