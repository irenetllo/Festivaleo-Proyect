package com.irene_tllo.festivaleo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by irene_ on 16/11/2016.
 */

public class FestivalAdapter extends RecyclerView.Adapter<Fila_Festival> implements View.OnClickListener {
    private List<Festival> data;
    private View.OnClickListener listener;
    private Context con;

    public FestivalAdapter(@NonNull List<Festival> data, Context con) {
        this.data = data;
        this.con = con;
    }

    @Override
    public Fila_Festival onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila_festi = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_festival, parent, false);
        fila_festi.setOnClickListener(this);
        return new Fila_Festival(fila_festi);
    }

    @Override
    public void onBindViewHolder(Fila_Festival holder, int position) {
        Festival festival = data.get(position);
        //Bitmap b = BitmapFactory.decodeResource(con.getResources(),R.mipmap.logoarenal);
        //holder.getFotoF().setImageBitmap(b);
        holder.getNombreF().setText(festival.getNombre());
        holder.getSubtitleTextView().setText(festival.getLugar());
        if (festival.getFotof() != null){
            Log.i("URL", "---" + festival.getFotof());
            new DownloadImageTask(holder,position).execute(festival.getFotof());
        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        Fila_Festival holder;
        int id;

        public DownloadImageTask(Fila_Festival holder, int id){
            this.holder = holder;
            this.id=id;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return cargarImagen(strings[0]);
        }

        protected void onPostExecute(Bitmap result) {
            holder.getFotoF().setImageBitmap(result);
            data.get(id).setFoto(result);


        }

        private Bitmap cargarImagen(String url){
            URL imageUrl = null;
            HttpURLConnection conn = null;
            try {
                imageUrl = new URL(url);
                conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream());
                return imagen;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Error: ",e.toString());
                return null;
            }
        }
    }

}
