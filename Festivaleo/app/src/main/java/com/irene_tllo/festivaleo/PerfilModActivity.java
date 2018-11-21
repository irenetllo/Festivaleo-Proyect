package com.irene_tllo.festivaleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilModActivity extends AppCompatActivity {

    private TextView nombreUS,telefonoUS,ciudadUS,anioUS,correoUS,passwordUS;
    Button btnGuardarCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mod);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Modificar perfil");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(PerfilModActivity.this,R.string.up, Toast.LENGTH_SHORT).show();



            }
        });
        DBManager dbManager = new DBManager(this);
        Intent intent1=getIntent();
        String id = intent1.getExtras().getString("id");
        Log.i("ID","id MOD ACT: "+id);
        Usuario user=dbManager.consultarUsuarioId(id);
        Log.i("ID","ID Usuario: "+user.getId());

        nombreUS = (TextView)findViewById(R.id.nombreUS);
        telefonoUS = (TextView)findViewById(R.id.telefonoUS);
        ciudadUS = (TextView)findViewById(R.id.ciudadUS);
        anioUS = (TextView)findViewById(R.id.anioUS);
        correoUS = (TextView)findViewById(R.id.correoUS);
        passwordUS=(TextView)findViewById(R.id.passwordUS);


        nombreUS.setText(user.getNombre());
        telefonoUS.setText(user.getTelefono());
        ciudadUS.setText(user.getCiudad());
        anioUS.setText(user.getAnio());
        correoUS.setText(user.getCorreo());
        passwordUS.setText(user.getPassword());

        btnGuardarCam = (Button) findViewById(R.id.btnGuardarCam);

        btnGuardarCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                String idOLD = intent.getExtras().getString("id");
                DBManager dbManager=new DBManager(getApplicationContext());

                Log.i("mod user id ", " "+idOLD);
                int modOk=dbManager.modificarUauario(idOLD,nombreUS.getText().toString(),telefonoUS.getText().toString(),ciudadUS.getText().toString(),
                        anioUS.getText().toString(),correoUS.getText().toString(),passwordUS.getText().toString());
                Log.i("modificado user:", " "+modOk);
                onBackPressed();
            }
        });

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                action(R.string.action_add);
                return true;
            case R.id.action_edit:
                action(R.string.action_edit);
                return true;
            case R.id.action_settings:
                action(R.string.action_settings);
                return true;
            case R.id.action_help:
                action(R.string.action_help);
                return true;
            case R.id.action_about:
                action(R.string.action_about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }
}
