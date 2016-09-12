package com.tesis.galeria.galeria.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.DetallesAvaluoActivity;
import com.tesis.galeria.galeria.ViewHolder.AvaluoVH;
import com.tesis.galeria.galeria.modelos.Avaluo;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import java.util.ArrayList;

/**
 * Created by danie on 26/5/2016.
 */
public class AvaluoAdapter extends RecyclerView.Adapter<AvaluoVH> {

    private AppCompatActivity context;
    private ArrayList<Avaluo> avaluos;

    public AvaluoAdapter(AppCompatActivity context, ArrayList<Avaluo> avaluos) {
        this.context = context;
        this.avaluos = avaluos;
    }

    @Override
    public AvaluoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaluo_row, parent, false);
        return new AvaluoVH(v);
    }

    @Override
    public void onBindViewHolder(AvaluoVH holder, int position) {
        final Avaluo avaluo = getAvaluo(position);

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
                Intent intent = new Intent(context, DetallesAvaluoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_AVALUO, avaluo);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        holder.tvTitulo.setText(avaluo.obra.titulo.length() > 15 ? avaluo.obra.titulo.substring(0, 13) + "..." : avaluo.obra.titulo);
        holder.tvFecha.setText(avaluo.fichaAvaluo.fPeticion);

        String estatus = avaluo.estatusAvaluo.nombre.toLowerCase();

        if (estatus.toLowerCase().equals("avaluado")) {
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
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + avaluo.obra.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return avaluos.size();
    }

    public Avaluo getAvaluo(int posicion) {
        return avaluos.get(posicion);
    }

    public void addAvaluo(Avaluo avaluo) {
        avaluos.add(avaluo);
        notifyItemInserted(avaluos.indexOf(avaluo));
    }

    public void addAvaluo(Avaluo avaluo, int posicion) {
        avaluos.add(posicion, avaluo);
        notifyItemInserted(posicion);
    }

    public void removeAvaluo(int posicion) {
        avaluos.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setAvaluo(Avaluo avaluo, int posicion) {
        avaluos.set(posicion, avaluo);
        notifyItemChanged(posicion);
    }

}
