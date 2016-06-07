package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tesis.galeria.R;

/**
 * Created by danie on 29/5/2016.
 */
public class ObraVH extends RecyclerView.ViewHolder {

    public TextView tvTitulo;
    public TextView tvArtista;
    public ImageView ivImagen;

    public ObraVH(View itemView) {
        super(itemView);

        tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
        tvArtista = (TextView) itemView.findViewById(R.id.tv_artista);
        ivImagen = (ImageView) itemView.findViewById(R.id.iv_imagen);

    }
}
