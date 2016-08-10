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
import com.tesis.galeria.galeria.DetallesAsesoriaActivity;
import com.tesis.galeria.galeria.ViewHolder.AsesoriaVH;
import com.tesis.galeria.galeria.modelos.Asesoria;

import java.util.ArrayList;

/**
 * Created by danie on 9/8/2016.
 */
public class AsesoriaAdapter extends RecyclerView.Adapter<AsesoriaVH> {

    private AppCompatActivity context;
    private ArrayList<Asesoria> asesorias;

    public AsesoriaAdapter(AppCompatActivity context, ArrayList<Asesoria> asesorias) {
        this.context = context;
        this.asesorias = asesorias;
    }

    @Override
    public AsesoriaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.asesoria_row, parent, false);
        return new AsesoriaVH(v);
    }

    @Override
    public void onBindViewHolder(AsesoriaVH holder, int position) {
        final Asesoria asesoria = getAsesoria(position);

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
                Intent intent = new Intent(context, DetallesAsesoriaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_ASESORIA, asesoria);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        holder.tvInmueble.setText(asesoria.tipoInmueble.nombre);
        holder.tvFecha.setText(asesoria.fsolicitud);

        String estatus = asesoria.estatusAsesoria.nombre.toLowerCase();

        if (estatus.toLowerCase().equals("asesorado")) {
            Picasso.with(context)
                    .load(R.drawable.aceptado)
                    .into(holder.ivEstatus);
        } else if (estatus.equals("en espera") || estatus.equals("en proceso")) {
            Picasso.with(context)
                    .load(R.drawable.espera)
                    .into(holder.ivEstatus);
        } else if (estatus.equals(estatus.equals("rechazado"))) {
            Picasso.with(context)
                    .load(R.drawable.rechazado)
                    .into(holder.ivEstatus);
        }

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_ASESORIA + asesoria.imagenesAsesorias.get(0))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return asesorias.size();
    }

    public Asesoria getAsesoria(int posicion) {
        return asesorias.get(posicion);
    }

    public void addAsesoria(Asesoria asesoria) {
        asesorias.add(asesoria);
        notifyItemInserted(asesorias.indexOf(asesoria));
    }

    public void addAsesoria(Asesoria asesoria, int posicion) {
        asesorias.add(posicion, asesoria);
        notifyItemInserted(posicion);
    }

    public void removeAsesoria(int posicion) {
        asesorias.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setAsesoria(Asesoria asesoria, int posicion) {
        asesorias.set(posicion, asesoria);
        notifyItemChanged(posicion);
    }

}