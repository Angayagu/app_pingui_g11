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

public class recorrido extends AppCompatActivity {
    private RequestQueue mQueue;
    private String token = "";
    private String mensaje;
    private String origen;
    private String id;
    private String camion;
    private String fechaIni;
    private String fechaFin;
    private String destino;
    private String[] parts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);

        mQueue = Volley.newRequestQueue(this);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        revisarRec();
    }
    private void revisarRec(){
        String url_temp = "https://amstdb.herokuapp.com/db/recorrido";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url_temp, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        TextView txt = (TextView) findViewById(R.id.txtRec);
                        try {
                            String dataRec = "id, camion, origen, destino\n\n";
                            for(int i = 0; i<response.length(); i++){
                                JSONObject j = (JSONObject) response.get(i);
                                dataRec = dataRec +""+j.getString("id")+", "+j.getString("camion")+", "+j.getString("origen")+", "+j.getString("destino")+"\n\n";
                            }
                            txt.setText(dataRec);
                        } catch (Exception e) {
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

