package com.example.amst.app_pingui_g11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class temperatura extends AppCompatActivity {

    private RequestQueue mQueue;
    private String token = "";
    private String mensaje;
    private String tempe;
    private String id;
    private String fechaReg;
    private String reco;
    private String arreglo;
    private JSONArray json;

    private String[] parts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);
        mQueue = Volley.newRequestQueue(this);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        revisarTemp();
    }
    private void revisarTemp() {
        String url_temp = "https://amstdb.herokuapp.com/db/registroDeFrios";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url_temp, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        TextView txt = (TextView) findViewById(R.id.txtTemp);
                        try {
                            String dataTemp = "id, fecha_registro, temperatura\n\n";
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject j = (JSONObject) response.get(i);
                                dataTemp = dataTemp + j.getString("id") + ", " + j.getString("fecha_registro") + ", " + j.getString("temperatura") + "\n\n";
                            }
                            txt.setText(dataTemp);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            public Map<String,
                    String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,
                        String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };
        mQueue.add(request);
    }
}


