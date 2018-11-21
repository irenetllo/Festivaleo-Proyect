package com.irene_tllo.festivaleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioActivity extends AppCompatActivity {


    Button btnregistro, btnSesion;
    private DBHelper helper;
    private DBManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnregistro = (Button) findViewById(R.id.btnregistro);

        btnSesion = (Button) findViewById(R.id.btniniSesion);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(intent2);
            }
        });

        helper = new DBHelper(this);
        manager = new DBManager(this);
    }
}
