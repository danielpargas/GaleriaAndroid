package com.tesis.galeria.galeria.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.ViewHolder.RespaldoVH;
import com.tesis.galeria.galeria.modelos.Respaldo;

import java.util.ArrayList;

/**
 * Created by danie on 23/7/2016.
 */
public class RespaldoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AppCompatActivity context;
    private ArrayList<Respaldo> respaldos;

    private static final int RESPALDO = 0;

    public RespaldoAdapter(AppCompatActivity context, ArrayList<Respaldo> respaldos) {
        this.context = context;
        this.respaldos = respaldos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {
            case RESPALDO:
                view = LayoutInflater.from(context).inflate(R.layout.respaldo_row, parent, false);
                return new RespaldoVH(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.respaldo_row, parent, false);
                return new RespaldoVH(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RespaldoVH) {
            RespaldoVH vh = (RespaldoVH) holder;

            Respaldo respaldo = getRespaldo(position);

            vh.tvNombre.setText(respaldo.nombre);
            vh.tvFechaCreado.setText(respaldo.creado);
        }
    }


    public Respaldo getRespaldo(int id) {
        return respaldos.get(id);
    }

    @Override
    public int getItemCount() {
        return respaldos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return RESPALDO;
    }
}
