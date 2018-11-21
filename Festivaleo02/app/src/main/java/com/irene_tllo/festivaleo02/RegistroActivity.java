package com.irene_tllo.festivaleo02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    Button btnISR;
    DBManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        manager = new DBManager(this);
        btnISR = (Button)findViewById(R.id.btnISR);



        btnISR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String email= ((EditText)findViewById(R.id.email)).getText().toString();
                final String nomU =((EditText)findViewById(R.id.nomU)).getText().toString();
                final String password= ((EditText)findViewById(R.id.password)).getText().toString();
                final String passwordV =((EditText)findViewById(R.id.passwordV)).getText().toString();
                final String fechaNac =((EditText)findViewById(R.id.fechaNac)).getText().toString();
                final String tel =((EditText)findViewById(R.id.tel)).getText().toString();
                final String ciudad =((EditText)findViewById(R.id.ciudad)).getText().toString();

                Log.i("yesss","yesssssssssssssssssssssssssssss");

                //if(password==passwordV && email.toString()!="" && nomU!="") {


                    long insertOK = manager.insertarUsuario(nomU, tel, ciudad, fechaNac, email, password);
                    if (insertOK > 0 ) {
                        Toast.makeText(getApplicationContext(),"oko",Toast.LENGTH_LONG).show();
                        Log.i("okooo","okooooooooooooooo");

                        Intent sig2 = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(sig2);
                    }else
                        Toast.makeText(getApplicationContext(),"error"+insertOK,Toast.LENGTH_LONG).show();
                //}
            }
        });


    }
}
