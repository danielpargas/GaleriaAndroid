package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tesis.galeria.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by danie on 22/5/2016.
 */
public class NoticiaVH extends RecyclerView.ViewHolder {

    public TextView tvTitulo;
    public TextView tvResumen;
    public TextView tvFecha;
    public ImageView ivImagen;
    public SimpleDraweeView sdvImagen;
    public CircleImageView civImagenUsuario;

    public NoticiaVH(View itemView) {
        super(itemView);

        tvTitulo = (TextView) itemView.findViewById(R.id.tv_titulo);
        tvResumen = (TextView) itemView.findViewById(R.id.tv_resumen);
        tvFecha = (TextView) itemView.findViewById(R.id.tv_fecha);

        civImagenUsuario = (CircleImageView) itemView.findViewById(R.id.civ_imagen_usuario);
        //ivImagen = (ImageView) itemView.findViewById(R.id.iv_imagen);
        sdvImagen = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imagen);
    }
}
