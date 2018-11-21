package com.irene_tllo.festivaleo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabHost th;
    EditText textList;
    Button btnSeting;
    ListView lista;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Festivaleo");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final DBManager dbmanag = new DBManager(this);
        final ArrayList<Festival> festival1 = dbmanag.consultarFestivalesLike(getIntent().getExtras().getString("id"));

        //--------------------------------------------------------------------------
        //--------------------------------------------------------------------------
        //--------------------------------------------------------------------------
        th=(TabHost)findViewById(R.id.th);

        //TAB 1
        th.setup();
        TabHost.TabSpec ts2=th.newTabSpec("Tab2");
        ts2.setIndicator("Principal");
        ts2.setContent(R.id.tab2);
        th.addTab(ts2);

        final ArrayList<Festival> festival = dbmanag.mostrarFestivales();
        final RecyclerView recyclerViewP = (RecyclerView) findViewById(R.id.recyclerViewPrincipal);

        FestivalAdapter adapter1 = new FestivalAdapter(festival, getApplicationContext());
        adapter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer valor = (Integer) recyclerViewP.getChildPosition(view);
                Log.i("DemoRecView", "Pulsado el elemento " + valor);
                Festival fe=festival.get(valor);
                Log.i("id fetival pasando ",fe.getNombre());

                String fo=dbmanag.buscarFestivalNombre(fe.getNombre());

                Intercambio.setObjeto(fe.getFoto());

                Intent intent1 = new Intent(MenuActivity.this, FestivalDetActivity.class);
                intent1.putExtra("id", fo);
                Log.i("id fetival pasando ",":"+fo);
                Log.i("ID usuario paso fest: ",getIntent().getExtras().getString("id"));
                intent1.putExtra("idUsuario", getIntent().getExtras().getString("id"));


                startActivity(intent1);
            }
        });

        recyclerViewP.setAdapter(adapter1);
        recyclerViewP.setLayoutManager(new LinearLayoutManager(this));

        /*//TAB 2
        th.setup();
        TabHost.TabSpec ts1=th.newTabSpec("Tab1");
        ts1.setIndicator("Tambien Van!");
        ts1.setContent(R.id.tab1);
        th.addTab(ts1);*/

        cargarListaLikeVoy(dbmanag);
        //TAB 3
        th.setup();
        TabHost.TabSpec ts3=th.newTabSpec("Tab3");
        ts3.setIndicator("Voy!");
        ts3.setContent(R.id.tab3);
        th.addTab(ts3);

        //--------------------------------------------------------------------------

    }


    public void onClick(View v) {
        al.add(textList.getText().toString());
        textList.setText("");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume(){
        super.onResume();
        DBManager dbm=new DBManager(this);
        Intent intent1 = getIntent();
        if((dbm.consultarUsuarioId(intent1.getExtras().getString("id")).getNombre().equals(""))) {
            intent1.putExtra("idUsuario onresume ", ":" + getIntent().getExtras().getString("id"));
            finish();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        //TAB 1
       /* th.setup();
        TabHost.TabSpec ts1=th.newTabSpec("Tab1");
        ts1.setIndicator("LIKES");
        ts1.setContent(R.id.tab1);
        th.addTab(ts1);*/
        super.onResume();
        final DBManager dbm=new DBManager(this);
        Intent intent1 = getIntent();
        if((dbm.consultarUsuarioId(intent1.getExtras().getString("id")).getNombre().equals(""))) {
            intent1.putExtra("idUsuario onresume ", ":" + getIntent().getExtras().getString("id"));
            finish();
        }
        cargarListaLikeVoy(dbm);


    }

    public void cargarListaLikeVoy(DBManager db){
        final DBManager dbm=db;
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final ArrayList<Festival> festivalesVoy = dbm.consultarFestivalesVoy(getIntent().getExtras().getString("id"));
        FestivalAdapter adapter2 = new FestivalAdapter(festivalesVoy,getApplicationContext());
        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer valor = (Integer) recyclerView.getChildPosition(view);
                Festival fe=festivalesVoy.get(valor);
                String fo=dbm.buscarFestivalNombre(fe.getNombre());
                Intercambio.setObjeto(fe.getFoto());
                Intent intent1 = new Intent(MenuActivity.this, FestivalDetActivity.class);
                intent1.putExtra("id", fo);
                intent1.putExtra("idUsuario", getIntent().getExtras().getString("id"));
                startActivity(intent1);
            }
        });
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent = getIntent();

        if (id == R.id.nav_camera) {

            Intent intent2 = new Intent(MenuActivity.this, PerfilDetActivity.class);
            intent2.putExtra("id", intent.getExtras().getString("id"));
            Log.i("GET EXTRA MAIN","GET EXTRA MAIN-----------------> "+intent.getExtras().getString("id"));
            startActivity(intent2);


        } else if (id == R.id.nav_gallery) {
            Intent intent2 = new Intent(MenuActivity.this, LikesActivity.class);
            intent2.putExtra("id",intent.getExtras().getString("id"));
            startActivity(intent2);
        } else if (id == R.id.nav_slideshow) {
            Intent intent2 = new Intent(MenuActivity.this, HelpActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_manage) {
            Intent intent2 = new Intent(MenuActivity.this, PerfilModActivity.class);
            intent2.putExtra("id",intent.getExtras().getString("id"));
            startActivity(intent2);
        } else if (id == R.id.nav_share) {
            Intent intent2 = new Intent(MenuActivity.this, ContactoActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_send) {
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
