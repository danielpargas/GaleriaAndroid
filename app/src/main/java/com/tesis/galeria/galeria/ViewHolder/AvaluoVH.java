package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tesis.galeria.R;

/**
 * Created by danie on 26/5/2016.
 */
public class AvaluoVH extends RecyclerView.ViewHolder {
    public TextView tvTitulo;
    public TextView tvFecha;
    public ImageView ivImagen;
    public ImageView ivEstatus;


    public AvaluoVH(View itemView) {
        super(itemView);

        tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
        tvFecha = (TextView) itemView.findViewById(R.id.tv_fecha);
        ivImagen = (ImageView) itemView.findViewById(R.id.iv_imagen);
        ivEstatus = (ImageView) itemView.findViewById(R.id.iv_estatus);
    }
}
