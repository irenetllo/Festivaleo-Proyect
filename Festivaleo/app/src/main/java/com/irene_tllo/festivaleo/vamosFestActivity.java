package com.irene_tllo.festivaleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class vamosFestActivity extends AppCompatActivity {

    EditText ciudad;
    Button btnbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vamos_fest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VAMONOS!");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(vamosFestActivity.this,R.string.up, Toast.LENGTH_SHORT).show();
            }
        });
        final String idu = getIntent().getExtras().getString("idu");

        final DBManager dbmanag = new DBManager(this);
        final ArrayList<Usuario> users = dbmanag.consultarUsuariosGO(getIntent().getExtras().getString("idf"),idu);

        UsuarioAdapter adapter = new UsuarioAdapter(users, dbmanag.consultarUsuarioId(idu).getNombre(),dbmanag);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ciudad = (EditText)findViewById(R.id.ciudad);
        btnbuscar = (Button)findViewById(R.id.btnbuscar);
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ciu = ((TextView) findViewById(R.id.ciudad)).getText().toString();
                Log.i("ciudad: ", " " + ciu);
                if (!ciu.equals("")) {
                    String idu = getIntent().getExtras().getString("idu");
                    String idf = getIntent().getExtras().getString("idf");
                    ArrayList<Usuario> usuarios = dbmanag.consultarUsuariosGOCiudad(idf, idu, ciu);
                    UsuarioAdapter adapter = new UsuarioAdapter(usuarios, getIntent().getExtras().getString("idu"), dbmanag);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsers);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } else {
                    final DBManager dbmanag = new DBManager(getApplicationContext());
                    final ArrayList<Usuario> users = dbmanag.consultarUsuariosGO(getIntent().getExtras().getString("idf"), idu);
                    UsuarioAdapter adapter = new UsuarioAdapter(users, dbmanag.consultarUsuarioId(idu).getNombre(), dbmanag);
                    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsers);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
        });

    }
}
