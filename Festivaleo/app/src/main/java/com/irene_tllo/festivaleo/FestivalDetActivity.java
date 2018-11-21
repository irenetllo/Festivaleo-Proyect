package com.irene_tllo.festivaleo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
public class FestivalDetActivity extends AppCompatActivity {

    TextView nombreF,fechaF,lugarF,descripcionF,asistentesF,countLikes, valoraciones;
    Button btnVoy,btnUrl,btnLike,btnMap,btnComent,btnAsistentes,btnValorar;
    ImageView foto;
    private Bitmap loadedImage;
    private String imageHttp;
    RatingBar ratingBar;
    ContentValues values;
    DBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_festival_det);
        final Intent intent = getIntent();
        helper = new DBHelper(this);
        final DBManager dbManager = new DBManager(this);
        Festival fest = dbManager.buscarFestivalId(intent.getExtras().getString("id"));
        boolean likeSi = dbManager.buscarLikesUser(intent.getExtras().getString("idUsuario"), fest.getId());
        values = new ContentValues();
        if (likeSi) {
            btnLike = (Button) findViewById(R.id.btnLike);
            btnLike.setBackground(getResources().getDrawable(R.mipmap.likeok));

        }
        if (dbManager.buscarVoyUser(intent.getExtras().getString("idUsuario"), fest.getId())) {
            btnVoy = (Button) findViewById(R.id.btnVoy);
            btnVoy.setBackground(getResources().getDrawable(R.mipmap.gookk));

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(fest.getNombre());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(FestivalDetActivity.this, R.string.up, Toast.LENGTH_SHORT).show();
            }
        });

        nombreF = (TextView) findViewById(R.id.nombreF);
        fechaF = (TextView) findViewById(R.id.fechaF);
        lugarF = (TextView) findViewById(R.id.lugarF);
        descripcionF = (TextView) findViewById(R.id.descripcionF);
        foto = (ImageView) findViewById(R.id.fotoF);
        countLikes = (TextView) findViewById(R.id.contLikes);
        valoraciones = (TextView)findViewById(R.id.valoraciones);
        mostrarValoracion();


        nombreF.setText(fest.getNombre());
        fechaF.setText(fest.getFecha());
        lugarF.setText(fest.getLugar());
        descripcionF.setText(fest.getDescripcion());
        Log.i("ASISTENTE AL FESTIVAL! ", " "+dbManager.buscarCuantosVoy(fest.getId()));
        countLikes.setText(fest.getLikes());
        foto.setImageBitmap((Bitmap)Intercambio.getObjeto());


        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                values.put(dbManager.AT_Puntuacion, rating);
            }
        });

        btnValorar = (Button) findViewById(R.id.btnValorar);
        btnValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.put(dbManager.AT_ID_Festival,dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getId());
                values.put(dbManager.AT_ID_Usuario,getIntent().getExtras().getString("idUsuario"));
                SQLiteDatabase db = helper.getWritableDatabase();
                if(!dbManager.valoradoporUser(getIntent().getExtras().getString("id"),getIntent().getExtras().getString("idUsuario"))) {
                    long result = db.insert(dbManager.TABLE_NAME_Valoraciones, null, values);
                    db.close();
                    if (result > 0) {
                        Toast.makeText(getApplicationContext(), "Su opinión se ha mandado correctamente", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Se ha producido un fallo en la valoración", Toast.LENGTH_SHORT).show();
                    mostrarValoracion();
                }else{
                    long result = db.update(dbManager.TABLE_NAME_Valoraciones,values,dbManager.AT_ID_Festival+"="+getIntent().getExtras().getString("id")+" and "+
                            dbManager.AT_ID_Usuario+"="+getIntent().getExtras().getString("idUsuario"),null);
                    if(result>0) {
                        Toast.makeText(getApplicationContext(), "Su opinión se ha modificado correctamente", Toast.LENGTH_SHORT).show();
                        mostrarValoracion();
                    }
                }

            }
        });

        btnVoy = (Button) findViewById(R.id.btnVoy);
        btnVoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Festival f = dbManager.voyFestival(getIntent().getExtras().getString("idUsuario"), getIntent().getExtras().getString("id"));
                if(dbManager.buscarVoyUser(getIntent().getExtras().getString("idUsuario"), getIntent().getExtras().getString("id"))){
                    btnVoy.setBackground(getResources().getDrawable(R.mipmap.gookk));
                }else
                    btnVoy.setBackground(getResources().getDrawable(R.mipmap.go));
            }
        });

        btnLike = (Button) findViewById(R.id.btnLike);

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Festival f = dbManager.likeFestival(getIntent().getExtras().getString("idUsuario"), getIntent().getExtras().getString("id"));
                countLikes.setText(f.getLikes());
                boolean likeSi = dbManager.buscarLikesUser(getIntent().getExtras().getString("idUsuario"), getIntent().getExtras().getString("id"));
                if (likeSi) {
                    btnLike.setBackground(getResources().getDrawable(R.mipmap.likeok));
                } else
                    btnLike.setBackground(getResources().getDrawable(R.mipmap.likes));
            }
        });

        btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FestivalDetActivity.this, MapsActivity.class);
                intent1.putExtra("lat", dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getCoordenadasLat());
                intent1.putExtra("lng", dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getCoordenadasLng());
                intent1.putExtra("nombre", dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getNombre());
                intent1.putExtra("lugar", dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getLugar());
                startActivity(intent1);
            }
        });

        btnComent = (Button) findViewById(R.id.btnComent);

        btnComent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FestivalDetActivity.this, ComentariosFesActivity.class);
                intent1.putExtra("idu", getIntent().getExtras().getString("idUsuario"));
                intent1.putExtra("idf", getIntent().getExtras().getString("id"));
                startActivity(intent1);
            }
        });

        btnAsistentes = (Button) findViewById(R.id.btnAsistentes);

        btnAsistentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FestivalDetActivity.this, vamosFestActivity.class);
                intent1.putExtra("idu", getIntent().getExtras().getString("idUsuario"));
                intent1.putExtra("idf", getIntent().getExtras().getString("id"));
                startActivity(intent1);
            }
        });

        btnUrl = (Button) findViewById(R.id.btnUrl);

        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link=dbManager.buscarFestivalId(getIntent().getExtras().getString("id")).getUrl();
                Intent intent1 = new Intent(intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent1);
            }
        });

    }

    private void mostrarValoracion() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select avg(puntuacion) as 'Media' FROM Valoraciones WHERE _idFestival=" +
                getIntent().getExtras().getString("id") + " ;", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            valoraciones.setText(String.valueOf(cursor.getFloat(cursor.getColumnIndex("Media"))));
        }else
            valoraciones.setText(String.valueOf(0));
    }
}
