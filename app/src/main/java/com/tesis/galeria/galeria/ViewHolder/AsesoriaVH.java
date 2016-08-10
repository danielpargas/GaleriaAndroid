package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tesis.galeria.R;

/**
 * Created by danie on 9/8/2016.
 */
public class AsesoriaVH extends RecyclerView.ViewHolder {

    public TextView tvInmueble;
    public TextView tvFecha;
    public ImageView ivImagen;
    public ImageView ivEstatus;

    public AsesoriaVH(View itemView) {
        super(itemView);

        tvInmueble = (TextView) itemView.findViewById(R.id.tv_inmueble);
        tvFecha = (TextView) itemView.findViewById(R.id.tv_fecha);
        ivImagen = (ImageView) itemView.findViewById(R.id.iv_imagen);
        ivEstatus = (ImageView) itemView.findViewById(R.id.iv_estatus);

    }

}
