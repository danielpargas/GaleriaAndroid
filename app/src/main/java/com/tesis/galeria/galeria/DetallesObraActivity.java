package com.tesis.galeria.galeria;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.modelos.Obra;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetallesObraActivity extends AppCompatActivity {

    private AppCompatActivity context;
    private Obra obra;

    private ImageView ivImagen;
    private TextView tvFechaElaboracion;
    private TextView tvTecnica;
    private TextView tvTipo;
    private TextView tvMedida;

    private CardView cvArtista;
    private TextView tvNombreArtista;
    private TextView tvFechaArtista;

    private CircleImageView civImagenArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_obra);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.finish();
                }
            });
        }

        Bundle bundle = getIntent().getExtras().getBundle(Constantes.PARAM_EXTRA);
        if (bundle != null) {
            obra = (Obra) bundle.getSerializable(Constantes.PARAM_DATOS_OBRA);
            if (ab != null) {
                ab.setTitle(obra.titulo);
            }
        }

        inicializarComponentes();

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + obra.imagen)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(ivImagen);

        if (obra.felaboracion != null && !obra.felaboracion.isEmpty()) {
            tvFechaElaboracion.setText(obra.felaboracion);
        } else {
            tvFechaElaboracion.setText(getString(R.string.no_disponible));
        }

        tvTecnica.setText(obra.tecnica.nombre);
        tvTipo.setText(obra.tipo.nombre);

        if (obra.medida != null && !obra.medida.isEmpty()) {
            tvMedida.setText(obra.medida);
        } else {
            tvMedida.setText(getString(R.string.no_disponible));
        }

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_ARTISTA + obra.artista.imagen)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(civImagenArtista);

        cvArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesArtistaActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constantes.PARAM_DATOS_ARTISTA, obra.artista);

                intent.putExtra(Constantes.PARAM_EXTRA, bundle);
                context.startActivity(intent);
            }
        });

        tvNombreArtista.setText(obra.artista.nombre + " " + obra.artista.apellido);
        tvFechaArtista.setText(obra.artista.fecha);
    }


    private void inicializarComponentes() {

        ivImagen = (ImageView) findViewById(R.id.iv_imagen);
        tvFechaElaboracion = (TextView) findViewById(R.id.tv_fecha_elaboracion);
        tvTecnica = (TextView) findViewById(R.id.tv_tecnica);
        tvTipo = (TextView) findViewById(R.id.tv_tipo);
        tvMedida = (TextView) findViewById(R.id.tv_medida);

        cvArtista = (CardView) findViewById(R.id.cv_artista);
        tvNombreArtista = (TextView) findViewById(R.id.tv_nombre_artista);
        tvFechaArtista = (TextView) findViewById(R.id.tv_fecha_artista);

        civImagenArtista = (CircleImageView) findViewById(R.id.civ_imagen_artista);
    }

}
