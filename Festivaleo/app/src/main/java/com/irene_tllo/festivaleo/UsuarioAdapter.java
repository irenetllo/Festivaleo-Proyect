package com.irene_tllo.festivaleo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Created by irene_ on 12/12/2016.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.Fila_Usuario> implements View.OnClickListener {
    private List<Usuario> data;
    private View.OnClickListener listener;
    private String nombreUser;
    private boolean borrar;
    private DBManager dbm;

    public UsuarioAdapter(@NonNull List<Usuario> data, String nombreUser, DBManager dbm) {
        this.data = data;
        this.nombreUser=nombreUser;
        this.dbm=dbm;
    }

    @Override
    public UsuarioAdapter.Fila_Usuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View fila_User = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_usuario, parent, false);
        fila_User.setOnClickListener(this);
        return new UsuarioAdapter.Fila_Usuario(fila_User,dbm);
    }

    @Override
    public void onBindViewHolder(UsuarioAdapter.Fila_Usuario holder, int position) {
        Usuario usuario = data.get(position);

        holder.getNombreF().setText(usuario.getNombre());
        holder.getSubtitleTextView().setText(usuario.getTelefono());
        holder.getNombreUsuario().setText(usuario.getCiudad());
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



    public class Fila_Usuario extends RecyclerView.ViewHolder {
        private TextView nombreF;
        private TextView descFest;
        private TextView nombreUsuario;
        private Button btnborrar;
        private DBManager dbm;

        public Fila_Usuario(View itemView,DBManager dbm) {
            super(itemView);
            //fotoFest= itemView.findViewById(R.id.foto);
            nombreF = (TextView) itemView.findViewById(R.id.titleTextView);
            descFest= (TextView) itemView.findViewById(R.id.subtitleTextView);
            nombreUsuario = (TextView) itemView.findViewById(R.id.nomUser);
           // btnborrar = (Button) itemView.findViewById(R.id.btnborrar);
            //final DBManager dbmanager=dbm;

            /*btnborrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("lalalalalal"," "+ nombreF.getText().toString());
                    //dbmanager.borrarComentario(nombreF.getText().toString(),descFest.getText().toString(),nombreUsuario.getText().toString());


                }
            });*/

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
