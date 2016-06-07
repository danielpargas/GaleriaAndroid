package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.tesis.galeria.R;

/**
 * Created by danie on 26/5/2016.
 */
public class ImagenVH extends RecyclerView.ViewHolder {

    public ImageView ivImagen;

    public ImagenVH(View itemView) {
        super(itemView);
        ivImagen = (ImageView) itemView.findViewById(R.id.iv_imagen);
    }
}
