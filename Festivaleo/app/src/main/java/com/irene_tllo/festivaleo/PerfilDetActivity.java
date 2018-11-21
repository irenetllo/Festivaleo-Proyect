package com.irene_tllo.festivaleo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilDetActivity extends AppCompatActivity {

    private TextView nombreUS,telefonoUS,ciudadUS,anioUS,correoUS;
    Button btnModPerfil,btnElimCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_det);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Perfil");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(PerfilDetActivity.this,R.string.up, Toast.LENGTH_SHORT).show();
            }
        });


        final DBManager dbManager = new DBManager(this);
        Intent intent1=getIntent();
        String id = intent1.getExtras().getString("id");
        Log.i("id","id:"+id);
        final Usuario user=dbManager.consultarUsuarioId(id);

        nombreUS = (TextView)findViewById(R.id.nombreUS);
        telefonoUS = (TextView)findViewById(R.id.telefonoUS);
        ciudadUS = (TextView)findViewById(R.id.ciudadUS);
        anioUS = (TextView)findViewById(R.id.anioUS);
        correoUS = (TextView)findViewById(R.id.correoUS);


        nombreUS.setText(user.getNombre());
        telefonoUS.setText(user.getTelefono());
        ciudadUS.setText(user.getCiudad());
        anioUS.setText(user.getAnio());
        correoUS.setText(user.getCorreo());

        btnModPerfil = (Button) findViewById(R.id.btnModPErfil);

        btnModPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilDetActivity.this, PerfilModActivity.class);


                Log.i("commit v1.0 :)  ", user.getNombre());


                intent.putExtra("id",user.getId());
                startActivity(intent);
            }
        });

        btnElimCuenta = (Button) findViewById(R.id.btnElimPerfil);

        btnElimCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("¿Desea continuar? Eliminaras tu perfil")
                        .setTitle("Advertencia")
                        .setCancelable(false)
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                })
                        .setPositiveButton("Continuar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String idu = getIntent().getExtras().getString("id");
                                        dbManager.eliminarUserId(idu);
                                        finish();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();*/


                String idu = getIntent().getExtras().getString("id");
                dbManager.eliminarUserId(idu);
                finish();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("pasando por: ","onRestart");
        DBManager dbManager = new DBManager(this);
        Intent intent1=getIntent();
        String id = intent1.getExtras().getString("id");
        Usuario user=dbManager.consultarUsuarioId(id);
        Log.i("id","id:"+id);
        Log.i("nombre",":"+user.getNombre());
        Log.i("telefono",":"+user.getTelefono());
        Log.i("ciudad",":"+user.getCiudad());

        nombreUS = (TextView)findViewById(R.id.nombreUS);
        telefonoUS = (TextView)findViewById(R.id.telefonoUS);
        ciudadUS = (TextView)findViewById(R.id.ciudadUS);
        anioUS = (TextView)findViewById(R.id.anioUS);
        correoUS = (TextView)findViewById(R.id.correoUS);

        nombreUS.setText(user.getNombre());
        telefonoUS.setText(user.getTelefono());
        ciudadUS.setText(user.getCiudad());
        anioUS.setText(user.getAnio());
        correoUS.setText(user.getCorreo());

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
