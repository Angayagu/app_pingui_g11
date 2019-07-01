package com.example.amst.app_pingui_g11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.android.volley.RequestQueue;

public class menu extends AppCompatActivity {
    private RequestQueue mQueue;
    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }
    public void exit(View v){
        this.finish();
        System.exit(0);
    }
    public void irTemp(View v){
        Intent temp = new Intent(getBaseContext(),temperatura.class);
        temp.putExtra("token", token);
        startActivity(temp);
    }
    public void irRec(View v){
        Intent rec = new Intent(getBaseContext(),recorrido.class);
        rec.putExtra("token", token);
        startActivity(rec);
    }
}
