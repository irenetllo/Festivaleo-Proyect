package com.irene_tllo.festivaleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ComentariosFesActivity extends AppCompatActivity {

    String comentario;
    Button btnComentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios_fes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(ComentariosFesActivity.this,R.string.up, Toast.LENGTH_SHORT).show();
            }
        });

        final DBManager dbmanag = new DBManager(this);
        String idu = getIntent().getExtras().getString("idu");
        String idf = getIntent().getExtras().getString("idf");
        getSupportActionBar().setTitle("Comentarios "+dbmanag.buscarFestivalId(idf).getNombre());
        final ArrayList<ComentarioFestival> comentarioFestivals = dbmanag.consultarComentarios(idf);


        btnComentar = (Button) findViewById(R.id.btnComentar);

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comentario = ((TextView)findViewById(R.id.comentario)).getText().toString();
                String idu = getIntent().getExtras().getString("idu");
                String idf = getIntent().getExtras().getString("idf");
                ArrayList<ComentarioFestival> comentarioFestivals = dbmanag.consultarComentarios(idf);
                comentarioFestivals =dbmanag.insertarComentario(idu,idf,comentario);
                ComentarioAdapter adapter = new ComentarioAdapter(comentarioFestivals,getIntent().getExtras().getString("idu"),dbmanag );
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComent);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                ((TextView)findViewById(R.id.comentario)).setText("");
            }
        });



        //añadir comentario a la bd y a la recicle
        ComentarioAdapter adapter = new ComentarioAdapter(comentarioFestivals, dbmanag.consultarUsuarioId(idu).getNombre(),dbmanag);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewComent);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
