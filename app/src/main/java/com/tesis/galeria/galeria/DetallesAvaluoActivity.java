package com.tesis.galeria.galeria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.adapter.ImagenAdapter;
import com.tesis.galeria.galeria.modelos.Avaluo;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import org.lucasr.twowayview.widget.TwoWayView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetallesAvaluoActivity extends AppCompatActivity {

    private TextView tvTituloObra;
    private TextView tvFechaPeticion;
    private TextView tvFechaAvaluo;
    private TextView tvComentario;
    private TextView tvEstatus;

    private FloatingActionButton fabImagenes;

    private ImageView ivImagen;

    private LinearLayout lyFechaAvaluo;
    private CardView cvComentario;


    private AppCompatActivity context;

    private Avaluo avaluo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_detalles_avaluo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*
        if (Utilidades.poseeRol(context, Constantes.ROL_ADMIN) || Utilidades.poseeRol(context, Constantes.ROL_DIRECTOR) || Utilidades.poseeRol(context, Constantes.ROL_EMPLEADO)) {
            MenuItem miAvaluar = menu.findItem(R.id.action_avaluar);
            miAvaluar.setVisible(true);
        }
*/

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
            avaluo = (Avaluo) bundle.getSerializable(Constantes.PARAM_DATOS_AVALUO);
        }

        inicializarComponentes();
        if (avaluo != null) {
            tvTituloObra.setText(avaluo.obra.titulo);
            tvFechaPeticion.setText(avaluo.fichaAvaluo.fPeticion);

            if (avaluo.fichaAvaluo.fAvaluo != null && !avaluo.fichaAvaluo.fAvaluo.isEmpty()) {
                tvFechaAvaluo.setText(avaluo.fichaAvaluo.fAvaluo);
            } else {
                lyFechaAvaluo.setVisibility(View.GONE);
            }

            if (avaluo.comentario != null && !avaluo.comentario.isEmpty()) {
                tvComentario.setText(avaluo.comentario);
            } else {
                cvComentario.setVisibility(View.GONE);
            }

            Picasso.with(context)
                    .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_OBRA + avaluo.obra.imagen)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(ivImagen);

            tvEstatus.setText(avaluo.estatusAvaluo.nombre);

            if (avaluo.imagenesAvaluos != null && avaluo.imagenesAvaluos.size() > 0) {
                fabImagenes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constantes.FRAGMENT_IMAGENES);
                        if (fragment == null) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .add(android.R.id.content, ImagenesFragment.newInstance(avaluo.imagenesAvaluos), Constantes.FRAGMENT_IMAGENES)
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
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void inicializarComponentes() {

        tvTituloObra = (TextView) findViewById(R.id.tv_titulo_pbra);
        tvFechaPeticion = (TextView) findViewById(R.id.tv_fecha_peticion);
        tvFechaAvaluo = (TextView) findViewById(R.id.tv_fecha_avaluo);
        tvComentario = (TextView) findViewById(R.id.tv_comentario);
        tvEstatus = (TextView) findViewById(R.id.tv_estatus);

        fabImagenes = (FloatingActionButton) findViewById(R.id.fabImagenes);

        lyFechaAvaluo = (LinearLayout) findViewById(R.id.ly_fecha_avaluo);

        cvComentario = (CardView) findViewById(R.id.cv_comentario);

        ivImagen = (ImageView) findViewById(R.id.iv_imagen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalles_avaluo, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (avaluo != null && avaluo.estatusAvaluo != null && !avaluo.estatusAvaluo.nombre.toLowerCase().equals("avaluado") && (Utilidades.poseeRol(context, Constantes.ROL_ADMIN) || Utilidades.poseeRol(context, Constantes.ROL_DIRECTOR) || Utilidades.poseeRol(context, Constantes.ROL_EMPLEADO))) {
            MenuItem miAvaluar = menu.findItem(R.id.action_avaluar);
            if (miAvaluar != null) {
                miAvaluar.setVisible(true);
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_avaluar) {

            Intent intent = new Intent(context, ProcesarAvaluoActivity.class);

            intent.putExtra(Constantes.PARAM_DATOS_AVALUO, avaluo);

            context.startActivity(intent);
            context.finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
