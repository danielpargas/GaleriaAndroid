package com.tesis.galeria.galeria.adapter;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.ViewHolder.NoticiaVH;
import com.tesis.galeria.galeria.db.ConexionDB;
import com.tesis.galeria.galeria.modelos.Noticia;

import java.util.ArrayList;

/**
 * Created by danie on 22/5/2016.
 */
public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaVH> {

    private AppCompatActivity context;
    private ArrayList<Noticia> noticias;

    public NoticiaAdapter() {
    }

    public NoticiaAdapter(AppCompatActivity context, ArrayList<Noticia> noticias) {
        this.context = context;
        this.noticias = noticias;
    }

    @Override
    public NoticiaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticia_row, parent, false);
        return new NoticiaVH(v);
    }

    @Override
    public void onBindViewHolder(NoticiaVH holder, int position) {
        Noticia noticia = getNoticia(position);

        holder.tvTitulo.setText(noticia.titulo);
        holder.tvResumen.setText(noticia.resumen);
        holder.tvFecha.setText(noticia.fecha);

        Log.d("IMAGEN", Constantes.DOMINIO + Constantes.URL_IMAGEN_USUARIO + noticia.applicationUser.imagen);

        // ConexionDB.getUnsafeOkHttpClient(context);

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_USUARIO + noticia.applicationUser.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.civImagenUsuario);

        /*
        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_NOTCIA + noticia.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivImagen);
                */

        Uri uri = Uri.parse(Constantes.DOMINIO + Constantes.URL_IMAGEN_NOTCIA + noticia.imagen);
        holder.sdvImagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.sdvImagen.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public Noticia getNoticia(int posicion) {
        return noticias.get(posicion);
    }

    public void addNoticia(Noticia noticia) {
        noticias.add(noticia);
        notifyItemInserted(noticias.indexOf(noticia));
    }

    public void addNoticia(Noticia noticia, int posicion) {
        noticias.add(posicion, noticia);
        notifyItemInserted(posicion);
    }

    public void removeNoticia(int posicion) {
        noticias.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setNoticia(Noticia noticia, int posicion) {
        noticias.set(posicion, noticia);
        notifyItemChanged(posicion);
    }

}
