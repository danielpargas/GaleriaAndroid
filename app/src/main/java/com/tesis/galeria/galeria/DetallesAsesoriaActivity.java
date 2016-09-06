package com.tesis.galeria.galeria;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.modelos.Asesoria;
import com.tesis.galeria.galeria.modelos.Publicacion;
import com.tesis.galeria.galeria.utilidades.Utilidades;

public class DetallesAsesoriaActivity extends AppCompatActivity {

    private AppCompatActivity mContext;

    private TextView tvCorreo;
    private TextView tvFechaSolicitud;
    private TextView tvFechaAsesoramiento;
    private TextView tvTelefono;
    private TextView tvInmueble;
    private TextView tvDireccion;
    private TextView tvEstatus;

    private FloatingActionButton fabImagenes;

    private ImageView ivImagen;

    private LinearLayout lyFechaAsesoramiento;

    private Asesoria mAsesoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_detalles_asesoria);


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
            mAsesoria = (Asesoria) bundle.getSerializable(Constantes.PARAM_DATOS_ASESORIA);
        }

        inicializarComponentes();

        if (mAsesoria != null) {

            tvCorreo.setText(Utilidades.getNombreUsuario(mContext));
            tvTelefono.setText(mAsesoria.telefono);
            tvDireccion.setText(mAsesoria.direccion);
            tvFechaSolicitud.setText(mAsesoria.fsolicitud);

            if (mAsesoria.fAsesoria != null && !mAsesoria.fAsesoria.isEmpty()) {
                tvFechaAsesoramiento.setText(mAsesoria.fAsesoria);
            } else {
                lyFechaAsesoramiento.setVisibility(View.GONE);
            }

            Picasso.with(mContext)
                    .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + mAsesoria.imagenesAsesoria.get(0))
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(ivImagen);

            tvEstatus.setText(mAsesoria.estatusAsesoria.nombre);
            tvInmueble.setText(mAsesoria.tipoInmueble.nombre);
            fabImagenes.setVisibility(View.GONE);
        }

    }

    private void inicializarComponentes() {

        tvCorreo = (TextView) findViewById(R.id.tv_correo);
        tvDireccion = (TextView) findViewById(R.id.tv_direccion);
        tvFechaSolicitud = (TextView) findViewById(R.id.tv_fecha_solicitud);
        tvFechaAsesoramiento = (TextView) findViewById(R.id.tv_fecha_asesoria);
        tvInmueble = (TextView) findViewById(R.id.tv_inmueble);
        tvTelefono = (TextView) findViewById(R.id.tv_telefono);
        tvEstatus = (TextView) findViewById(R.id.tv_estatus);

        lyFechaAsesoramiento = (LinearLayout) findViewById(R.id.ly_fecha_aesoria);

        fabImagenes = (FloatingActionButton) findViewById(R.id.fabImagenes);
        ivImagen = (ImageView) findViewById(R.id.iv_imagen);
    }

}
