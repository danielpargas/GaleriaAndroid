package com.tesis.galeria.galeria.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.DetallesArtistaActivity;
import com.tesis.galeria.galeria.ViewHolder.ArtistaVH;
import com.tesis.galeria.galeria.modelos.Artista;

import java.util.ArrayList;

/**
 * Created by danie on 4/6/2016.
 */
public class ArtistaAdapter extends RecyclerView.Adapter<ArtistaVH> {

    private AppCompatActivity context;
    private ArrayList<Artista> artistas;

    public ArtistaAdapter(AppCompatActivity context, ArrayList<Artista> artistas) {
        this.context = context;
        this.artistas = artistas;
    }

    @Override
    public ArtistaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artista_row, parent, false);
        return new ArtistaVH(v);
    }

    @Override
    public void onBindViewHolder(ArtistaVH holder, int position) {
        final Artista artista = getArtista(position);

        if (position == 0) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.topMargin = 8;
            holder.itemView.setLayoutParams(params);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesArtistaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_ARTISTA, artista);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_ARTISTA + artista.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.civImagen);

        holder.tvNombre.setText(artista.nombre + " " + artista.apellido);
        holder.tvFecha.setText(artista.fecha);
    }

    @Override
    public int getItemCount() {
        return artistas.size();
    }

    public Artista getArtista(int posicion) {
        return artistas.get(posicion);
    }

    public void addArtista(Artista artista) {
        artistas.add(artista);
        notifyItemInserted(artistas.indexOf(artista));
    }

    public void addArtista(Artista artista, int posicion) {
        artistas.add(posicion, artista);
        notifyItemInserted(posicion);
    }

    public void removeArtista(int posicion) {
        artistas.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setArtista(Artista artista, int posicion) {
        artistas.set(posicion, artista);
        notifyItemChanged(posicion);
    }

}
