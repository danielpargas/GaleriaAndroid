package com.tesis.galeria.galeria;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.modelos.Publicacion;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetallesPublicacionActivity extends AppCompatActivity {

    private AppCompatActivity mContext;

    private TextView tvCorreo;
    private TextView tvFechaInicio;
    private TextView tvTelefono;
    private TextView tvDescripcion;
    private TextView tvEstatus;

    private FloatingActionButton fabImagenes;

    private ImageView ivImagen;

    private LinearLayout lyTelefono;

    private CardView cvDescripcion;

    private Publicacion mPublicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_detalles_publicacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }

            });
        }

        Bundle bundle = getIntent().getExtras().getBundle(Constantes.PARAM_EXTRA);
        if (bundle != null) {
            mPublicacion = (Publicacion) bundle.getSerializable(Constantes.PARAM_DATOS_PUBLICACION);
        }

        inicializarComponentes();

        if (mPublicacion != null) {

            if (mPublicacion.correo != null && !mPublicacion.correo.isEmpty()) {
                tvCorreo.setText(mPublicacion.correo);
            } else {
                tvCorreo.setText(Utilidades.getNombreUsuario(mContext));
            }

            if (mPublicacion.telefono != null && !mPublicacion.telefono.isEmpty()) {
                tvTelefono.setText(mPublicacion.telefono);
            } else {
                lyTelefono.setVisibility(View.GONE);
            }

            if (mPublicacion.descripcion != null && !mPublicacion.descripcion.isEmpty()) {
                tvDescripcion.setText(mPublicacion.descripcion);
            } else {
                tvDescripcion.setText("No posee descripcion");
            }

            Picasso.with(mContext)
                    .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + mPublicacion.avaluo.obra.imagen)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(ivImagen);

            tvEstatus.setText(mPublicacion.estatusPublicacion.nombre);

            if (mPublicacion.avaluo.imagenesAvaluos != null && mPublicacion.avaluo.imagenesAvaluos.size() > 0) {
                fabImagenes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constantes.FRAGMENT_IMAGENES);
                        if (fragment == null) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .add(android.R.id.content, ImagenesFragment.newInstance(mPublicacion.avaluo.imagenesAvaluos), Constantes.FRAGMENT_IMAGENES)
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
            } else {
                fabImagenes.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void inicializarComponentes() {

        tvCorreo = (TextView) findViewById(R.id.tv_correo);
        tvDescripcion = (TextView) findViewById(R.id.tv_descripcion);
        tvFechaInicio = (TextView) findViewById(R.id.tv_fecha_inicio);
        tvTelefono = (TextView) findViewById(R.id.tv_telefono);
        tvEstatus = (TextView) findViewById(R.id.tv_estatus);

        lyTelefono = (LinearLayout) findViewById(R.id.ly_telefono);

        fabImagenes = (FloatingActionButton) findViewById(R.id.fabImagenes);
        ivImagen = (ImageView) findViewById(R.id.iv_imagen);
    }
}
