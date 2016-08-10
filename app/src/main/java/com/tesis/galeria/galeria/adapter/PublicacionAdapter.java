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
import com.tesis.galeria.galeria.DetallesPublicacionActivity;
import com.tesis.galeria.galeria.ViewHolder.PublicacionVH;
import com.tesis.galeria.galeria.modelos.Publicacion;

import java.util.ArrayList;

/**
 * Created by danie on 9/8/2016.
 */
public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionVH> {

    private AppCompatActivity context;
    private ArrayList<Publicacion> publicaciones;

    public PublicacionAdapter(AppCompatActivity context, ArrayList<Publicacion> publicaciones) {
        this.context = context;
        this.publicaciones = publicaciones;
    }

    @Override
    public PublicacionVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.publicacion_row, parent, false);
        return new PublicacionVH(v);
    }

    @Override
    public void onBindViewHolder(PublicacionVH holder, int position) {
        final Publicacion publicacion = getPublicacion(position);

        if (position < 2) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.topMargin = 8;
            holder.itemView.setLayoutParams(params);
        } else if (position == getItemCount() - 1 || position == getItemCount() - 2) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.bottomMargin = 8;
            holder.itemView.setLayoutParams(params);
        } else {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            params.topMargin = 0;
            holder.itemView.setLayoutParams(params);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesPublicacionActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_ASESORIA, publicacion);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        holder.tvTitulo.setText(publicacion.avaluo.obra.titulo);
        holder.tvFecha.setText(publicacion.finicio);

        String estatus = publicacion.estatusPublicacion.nombre.toLowerCase();

        if (estatus.toLowerCase().equals("publicado")) {
            Picasso.with(context)
                    .load(R.drawable.aceptado)
                    .into(holder.ivEstatus);
        } else if (estatus.equals(estatus.equals("bloqueado"))) {
            Picasso.with(context)
                    .load(R.drawable.rechazado)
                    .into(holder.ivEstatus);
        } else if (publicacion.ffinal != null && !publicacion.ffinal.isEmpty()) {
            Picasso.with(context)
                    .load(R.drawable.espera)
                    .into(holder.ivEstatus);
        }

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + publicacion.avaluo.obra.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public Publicacion getPublicacion(int posicion) {
        return publicaciones.get(posicion);
    }

    public void addPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        notifyItemInserted(publicaciones.indexOf(publicacion));
    }

    public void addPublicacion(Publicacion publicacion, int posicion) {
        publicaciones.add(posicion, publicacion);
        notifyItemInserted(posicion);
    }

    public void removePublicacion(int posicion) {
        publicaciones.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setPublicacion(Publicacion publicacion, int posicion) {
        publicaciones.set(posicion, publicacion);
        notifyItemChanged(posicion);
    }

}