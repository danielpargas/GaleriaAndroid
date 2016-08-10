package com.tesis.galeria.galeria.task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.adapter.PublicacionAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Publicaciones;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 9/8/2016.
 */
public class GetPublicacionesAsyncTask extends AsyncTask<String, Void, Publicaciones> {
    private AppCompatActivity context;
    private TwoWayView recycler;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;

    public GetPublicacionesAsyncTask(TwoWayView recycler, AppCompatActivity context, ViewGroup v) {
        this.recycler = recycler;
        this.context = context;

        tvEmpty = (TextView) v.findViewById(R.id.tv_empty);
        btnEmpty = (Button) v.findViewById(R.id.btn_empty);
        wheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (recycler.getAdapter() == null) {
            wheel.setVisibility(View.VISIBLE);
        }
        tvEmpty.setVisibility(View.GONE);
        btnEmpty.setVisibility(View.GONE);
    }

    @Override
    protected Publicaciones doInBackground(String... params) {
        return ModelosDB.getPublicacionesUsuario(params[0]);
    }

    @Override
    protected void onPostExecute(Publicaciones publicaciones) {
        super.onPostExecute(publicaciones);
        wheel.setVisibility(View.GONE);
        if (publicaciones != null) {
            if (publicaciones.items != null) {
                if (publicaciones.items.size() > 0) {
                    Log.d("PUBLICACIONES", String.valueOf(publicaciones.items.size()));
                    recycler.setAdapter(new PublicacionAdapter(context, publicaciones.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_publicaciones);
                }
            } else {
                tvEmpty.setText(context.getString(R.string.error));
                tvEmpty.setVisibility(View.VISIBLE);
                btnEmpty.setVisibility(View.VISIBLE);
            }
        } else {
            tvEmpty.setText(context.getString(R.string.error));
            tvEmpty.setVisibility(View.VISIBLE);
            btnEmpty.setVisibility(View.VISIBLE);
        }
    }
}
