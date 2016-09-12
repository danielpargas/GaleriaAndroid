package com.tesis.galeria.galeria.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.DetallesArtistaActivity;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Obra;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by danie on 11/9/2016.
 */
public class GetObraAsyncTask extends AsyncTask<Integer, Void, Obra> {

    private AppCompatActivity mContext;

    private ImageView ivImagen;
    private TextView tvFechaElaboracion;
    private TextView tvTecnica;
    private TextView tvTipo;
    private TextView tvMedida;

    private CardView cvArtista;
    private TextView tvNombreArtista;
    private TextView tvFechaArtista;

    private CircleImageView civImagenArtista;

    public GetObraAsyncTask(AppCompatActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Obra doInBackground(Integer... params) {
        return ModelosDB.getObra(params[0]);
    }

    @Override
    protected void onPostExecute(final Obra obra) {
        super.onPostExecute(obra);

        inicializarComponentes();

        Picasso.with(mContext)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + obra.imagen)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(ivImagen);

        if (obra.felaboracion != null && !obra.felaboracion.isEmpty()) {
            tvFechaElaboracion.setText(obra.felaboracion);
        } else {
            tvFechaElaboracion.setText(mContext.getString(R.string.no_disponible));
        }

        tvTecnica.setText(obra.tecnica.nombre);
        tvTipo.setText(obra.tipo.nombre);

        if (obra.medida != null && !obra.medida.isEmpty()) {
            tvMedida.setText(obra.medida);
        } else {
            tvMedida.setText(mContext.getString(R.string.no_disponible));
        }

        Picasso.with(mContext)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_ARTISTA + obra.artista.imagen)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(civImagenArtista);

        cvArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetallesArtistaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_ARTISTA, obra.artista);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                mContext.startActivity(intent);
            }
        });

        tvNombreArtista.setText(obra.artista.nombre + " " + obra.artista.apellido);
        tvFechaArtista.setText(obra.artista.fecha);
    }

    private void inicializarComponentes() {

        ivImagen = (ImageView) mContext.findViewById(R.id.iv_imagen);
        tvFechaElaboracion = (TextView) mContext.findViewById(R.id.tv_fecha_elaboracion);
        tvTecnica = (TextView) mContext.findViewById(R.id.tv_tecnica);
        tvTipo = (TextView) mContext.findViewById(R.id.tv_tipo);
        tvMedida = (TextView) mContext.findViewById(R.id.tv_medida);

        cvArtista = (CardView) mContext.findViewById(R.id.cv_artista);
        tvNombreArtista = (TextView) mContext.findViewById(R.id.tv_nombre_artista);
        tvFechaArtista = (TextView) mContext.findViewById(R.id.tv_fecha_artista);

        civImagenArtista = (CircleImageView) mContext.findViewById(R.id.civ_imagen_artista);
    }
}
