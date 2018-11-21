package com.irene_tllo.festivaleo;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by irene_ on 20/11/2016.
 */

public class ComentarioAdapter  extends RecyclerView.Adapter<ComentarioAdapter.Fila_Comentario> implements View.OnClickListener {
        private List<ComentarioFestival> data;
        private View.OnClickListener listener;
        private String nombreUser;
        private boolean borrar;
        private DBManager dbm;

        public ComentarioAdapter(@NonNull List<ComentarioFestival> data, String nombreUser, DBManager dbm) {
            this.data = data;
            this.nombreUser=nombreUser;
            this.dbm=dbm;
        }

        @Override
        public Fila_Comentario onCreateViewHolder(ViewGroup parent, int viewType) {
            View fila_Coment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_comentario, parent, false);
            fila_Coment.setOnClickListener(this);
            return new Fila_Comentario(fila_Coment,dbm);
        }

        @Override
        public void onBindViewHolder(Fila_Comentario holder, int position) {
            ComentarioFestival comentario = data.get(position);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date fec=comentario.getFecha();
            String fecha=sdf.format(fec);

            holder.getNombreF().setText(comentario.getNombU());
            holder.getSubtitleTextView().setText(comentario.getComent());
            holder.getNombreUsuario().setText(fecha);
            Log.i("veamosss", "nomUser: "+nombreUser+" holderNomUser: "+holder.getNombreF().getText().toString());
            if(!nombreUser.equals(holder.getNombreF().getText().toString())) {
                holder.getbtnBorrar().setVisibility(View.INVISIBLE);
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



    public class Fila_Comentario extends RecyclerView.ViewHolder {
        private View fotoFest;
        private TextView nombreF;
        private TextView descFest;
        private TextView nombreUsuario;
        private Button btnborrar;
        private DBManager dbm;

        public Fila_Comentario(View itemView,DBManager dbm) {
            super(itemView);
            //fotoFest= itemView.findViewById(R.id.foto);
            nombreF = (TextView) itemView.findViewById(R.id.titleTextView);
            descFest= (TextView) itemView.findViewById(R.id.subtitleTextView);
            nombreUsuario = (TextView) itemView.findViewById(R.id.nomUser);
            btnborrar = (Button) itemView.findViewById(R.id.btnborrar);
            final DBManager dbmanager=dbm;

            btnborrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("lalalalalal"," "+ nombreF.getText().toString());
                    dbmanager.borrarComentario(nombreF.getText().toString(),descFest.getText().toString(),nombreUsuario.getText().toString());


                }
            });

        }

        public TextView getNombreF() {
            return nombreF;
        }

        public TextView getSubtitleTextView() {
            return descFest;
        }

        public TextView getNombreUsuario() {
            return nombreUsuario;
        }

        public Button getbtnBorrar() {
            return (Button) btnborrar;
        }

    }


}
