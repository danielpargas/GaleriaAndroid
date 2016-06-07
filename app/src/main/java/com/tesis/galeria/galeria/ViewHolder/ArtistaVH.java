package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tesis.galeria.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by danie on 4/6/2016.
 */
public class ArtistaVH extends RecyclerView.ViewHolder {

    public CircleImageView civImagen;
    public TextView tvNombre;
    public TextView tvFecha;

    public ArtistaVH(View itemView) {
        super(itemView);

        civImagen = (CircleImageView) itemView.findViewById(R.id.civ_imagen);
        tvNombre = (TextView) itemView.findViewById(R.id.tv_nombre);
        tvFecha = (TextView) itemView.findViewById(R.id.tv_fecha);
    }
}
