package com.irene_tllo.festivaleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoActivity extends AppCompatActivity {

    Spinner opciones;
    Button btnContacto;
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contacto");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(ContactoActivity.this,R.string.up, Toast.LENGTH_SHORT).show();
            }
        });

        opciones = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        texto=(EditText)findViewById(R.id.descripcion);

        btnContacto = (Button) findViewById(R.id.btnContacto);

        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("");
                    Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();

            }
        });

    }

}
