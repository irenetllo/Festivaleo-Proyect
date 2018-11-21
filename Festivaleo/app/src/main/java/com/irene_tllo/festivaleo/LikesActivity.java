package com.irene_tllo.festivaleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class LikesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("likes");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(LikesActivity.this,R.string.up, Toast.LENGTH_SHORT).show();
            }
        });



        final DBManager dbmanag = new DBManager(this);
        final ArrayList<Festival> festival = dbmanag.consultarFestivalesLike(getIntent().getExtras().getString("id"));

        final RecyclerView recyclerViewN = (RecyclerView) findViewById(R.id.recycleLikes);
        FestivalAdapter adapter = new FestivalAdapter(festival, getApplicationContext());
        if(!festival.isEmpty()) {
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer valor = (Integer) recyclerViewN.getChildPosition(view);
                    Log.i("DemoRecView", "Pulsado el elemento " + valor);
                    Festival fe = festival.get(valor);
                    Log.i("id fetival pasando ", fe.getNombre());

                    String fo = dbmanag.buscarFestivalNombre(fe.getNombre());

                    Intent intent1 = new Intent(LikesActivity.this, FestivalDetActivity.class);
                    intent1.putExtra("id", fo);
                    Log.i("id fetival pasando ", ":" + fo);
                    Log.i("ID usuario paso fest: ", getIntent().getExtras().getString("id"));
                    intent1.putExtra("idUsuario", getIntent().getExtras().getString("id"));

                    startActivity(intent1);
                }
            });
            recyclerViewN.setAdapter(adapter);

            recyclerViewN.setLayoutManager(new LinearLayoutManager(this));
        }

    }
}
