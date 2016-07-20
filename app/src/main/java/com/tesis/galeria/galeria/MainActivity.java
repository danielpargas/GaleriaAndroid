package com.tesis.galeria.galeria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.db.ConexionDB;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView mNavigationView;
    private AppCompatActivity context;

    private static int itemSeleccionado = R.id.nav_noticias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        ConexionDB.getUnsafeOkHttpClient(context);

        if (!Utilidades.estaRegistrado(context)) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            context.finish();
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = mNavigationView.getHeaderView(0);

        TextView textView = (TextView) headerView.findViewById(R.id.tv_usuario);
        textView.setText(Utilidades.getNombreUsuario(context));

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_USUARIO + Utilidades.getImagenUsuario(context))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into((CircleImageView) headerView.findViewById(R.id.iv_imagen));

        mNavigationView.setNavigationItemSelectedListener(this);

        mNavigationView.setCheckedItem(itemSeleccionado);

        if (itemSeleccionado == R.id.nav_noticias) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, NoticiasFragment.newInstance(), Constantes.FRAGMENT_NOTCIAS)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        itemSeleccionado = id;

        Fragment fragment = null;

        if (id == R.id.nav_noticias) {
            fragment = new NoticiasFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, new NoticiasFragment(), Constantes.FRAGMENT_NOTCIAS)
                    .commit();
            // Handle the camera action
        } else if (id == R.id.nav_cuenta) {

            fragment = new PerfilFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragment, Constantes.FRAGMENT_PERFIL)
                    .commit();
        } else if (id == R.id.nav_servicios) {
            fragment = new CuentaFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragment, Constantes.FRAGMENT_CUENTA)
                    .commit();
        } else if (id == R.id.nav_obras) {
            fragment = ObraFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragment, Constantes.FRAGMENT_OBRAS)
                    .commit();
        }
        if (id == R.id.nav_artistas) {
            fragment = ArtistaFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragment, Constantes.FRAGMENT_ARTISTAS)
                    .commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_salir) {
            Utilidades.finalizarSesion(context);

            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
