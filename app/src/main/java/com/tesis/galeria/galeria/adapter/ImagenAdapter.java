package com.tesis.galeria.galeria.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.ViewHolder.ImagenVH;
import com.tesis.galeria.galeria.modelos.ImagenAvaluo;

import java.util.ArrayList;

/**
 * Created by danie on 26/5/2016.
 */
public class ImagenAdapter extends RecyclerView.Adapter<ImagenVH> {

    private AppCompatActivity context;
    private ArrayList<ImagenAvaluo> imagenes;

    public ImagenAdapter(AppCompatActivity context, ArrayList<ImagenAvaluo> imagenes) {
        this.context = context;
        this.imagenes = imagenes;
    }

    @Override
    public ImagenVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.imagen_row, parent, false);
        return new ImagenVH(v);
    }

    @Override
    public void onBindViewHolder(ImagenVH holder, int position) {
        ImagenAvaluo imagenAvaluo = getImagenAvaluo(position);

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_AVALUO + imagenAvaluo.nombre)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }

    public ImagenAvaluo getImagenAvaluo(int posicion) {
        return imagenes.get(posicion);
    }

    public void addImagenAvaluo(ImagenAvaluo imagenAvaluo) {
        imagenes.add(imagenAvaluo);
        notifyItemInserted(imagenes.indexOf(imagenAvaluo));
    }

    public void addimagenImagenAvaluo(ImagenAvaluo imagenAvaluo, int posicion) {
        imagenes.add(posicion, imagenAvaluo);
        notifyItemInserted(posicion);
    }

    public void removeAvaluo(int posicion) {
        imagenes.remove(posicion);
        notifyItemRemoved(posicion);
        notifyItemRangeChanged(posicion, getItemCount());
    }

    public void setImagenAvaluo(ImagenAvaluo imagenAvaluo, int posicion) {
        imagenes.set(posicion, imagenAvaluo);
        notifyItemChanged(posicion);
    }


}
