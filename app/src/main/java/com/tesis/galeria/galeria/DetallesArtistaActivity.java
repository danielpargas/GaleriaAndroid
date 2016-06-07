package com.tesis.galeria.galeria;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.modelos.Artista;

public class DetallesArtistaActivity extends AppCompatActivity {

    private AppCompatActivity context;

    private TextView tvBiografia;
    private ImageView ivImagen;

    private FloatingActionButton fabObras;

    private Artista artista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_detalles_artista);
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
            artista = (Artista) bundle.getSerializable(Constantes.PARAM_DATOS_ARTISTA);
            if (ab != null) {
                ab.setTitle(artista.nombre + " " + artista.apellido);
            }
        }
        inicializarComponentes();

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_ARTISTA + artista.imagen)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivImagen);

        if (artista.biografia != null && !artista.biografia.isEmpty()) {
            tvBiografia.setText(Html.fromHtml(artista.biografia).toString());
        } else {
            tvBiografia.setText(R.string.sin_biografia);
        }
        fabObras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(android.R.id.content, ObraFragment.newInstance(artista.id), Constantes.FRAGMENT_OBRAS)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    private void inicializarComponentes() {

        ivImagen = (ImageView) findViewById(R.id.iv_imagen);
        tvBiografia = (TextView) findViewById(R.id.tv_biografia);

        fabObras = (FloatingActionButton) findViewById(R.id.fab_obras);
    }

}
