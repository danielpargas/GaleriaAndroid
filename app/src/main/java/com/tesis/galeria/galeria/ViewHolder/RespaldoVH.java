package com.tesis.galeria.galeria.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tesis.galeria.R;

/**
 * Created by danie on 23/7/2016.
 */
public class RespaldoVH extends RecyclerView.ViewHolder {

    public TextView tvNombre;
    public TextView tvFechaCreado;

    public RespaldoVH(View itemView) {
        super(itemView);

        tvNombre = (TextView) itemView.findViewById(R.id.tv_nombre);
        tvFechaCreado = (TextView) itemView.findViewById(R.id.tv_fecha_creado);
    }
}
