package com.irene_tllo.festivaleo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by irene_ on 16/11/2016.
 */

public class Fila_Festival extends RecyclerView.ViewHolder {
    private ImageView fotoFest;
    private TextView nombreF;
    private TextView descFest;

    public Fila_Festival(View itemView) {
        super(itemView);
        fotoFest= (ImageView) itemView.findViewById(R.id.foto);
        nombreF = (TextView) itemView.findViewById(R.id.titleTextView);
        descFest= (TextView) itemView.findViewById(R.id.subtitleTextView);
    }



    public TextView getNombreF() {
        return nombreF;
    }

    public TextView getSubtitleTextView() {
        return descFest;
    }

    public ImageView getFotoF() {
        return fotoFest;
    }

}
