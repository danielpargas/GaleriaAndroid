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
import com.tesis.galeria.galeria.DetallesObraActivity;
import com.tesis.galeria.galeria.ViewHolder.ObraVH;
import com.tesis.galeria.galeria.modelos.Obra;

import java.util.ArrayList;

/**
 * Created by danie on 29/5/2016.
 */
public class ObraAdapter extends RecyclerView.Adapter<ObraVH> {

    private AppCompatActivity context;
    private ArrayList<Obra> obras;

    public ObraAdapter(AppCompatActivity context, ArrayList<Obra> obras) {
        this.context = context;
        this.obras = obras;
    }

    @Override
    public ObraVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.obra_row, parent, false);
        return new ObraVH(v);
    }

    @Override
    public void onBindViewHolder(ObraVH holder, int position) {
        final Obra obra = getObra(position);

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
                Intent intent = new Intent(context, DetallesObraActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_OBRA, obra);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        holder.tvTitulo.setText(obra.titulo.length() > 0 ? obra.titulo.substring(0, 13) + "..." : obra.titulo);
        holder.tvArtista.setText(obra.artista.nombre + " " + obra.artista.apellido);

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + obra.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return obras.size();
    }

    public Obra getObra(int posicion) {
        return obras.get(posicion);
    }

    public void addObra(Obra obra) {
        obras.add(obra);
        notifyItemInserted(obras.indexOf(obra));
    }

    public void addObra(Obra obra, int posicion) {
        obras.add(posicion, obra);
        notifyItemInserted(posicion);
    }

    public void removeObra(int posicion) {
        obras.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setObra(Obra obra, int posicion) {
        obras.set(posicion, obra);
        notifyItemChanged(posicion);
    }

}