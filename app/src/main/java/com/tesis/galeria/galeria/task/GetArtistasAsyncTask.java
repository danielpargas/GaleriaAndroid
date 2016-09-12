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
import com.tesis.galeria.galeria.adapter.ArtistaAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Artistas;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 4/6/2016.
 */
public class GetArtistasAsyncTask extends AsyncTask<Void, Void, Artistas> {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private ViewGroup v;

    private String mQuery;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;


    public GetArtistasAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v) {
        this.context = context;
        this.recycler = recycler;
        this.v = v;

        tvEmpty = (TextView) v.findViewById(R.id.tv_empty);
        btnEmpty = (Button) v.findViewById(R.id.btn_empty);
        wheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);

    }

    public GetArtistasAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v, String mQuery) {
        this.context = context;
        this.recycler = recycler;
        this.v = v;
        this.mQuery = mQuery;

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
    protected Artistas doInBackground(Void... params) {

        if (mQuery == null || mQuery.isEmpty()) {
            return ModelosDB.getArtistas();
        } else {
            return ModelosDB.getArtistas(mQuery);
        }
    }

    @Override
    protected void onPostExecute(Artistas artistas) {
        super.onPostExecute(artistas);
        wheel.setVisibility(View.GONE);
        if (artistas != null) {
            if (artistas.items != null) {
                if (artistas.items.size() > 0) {
                    Log.d("ARTISTAS", String.valueOf(artistas.items.size()));
                    recycler.setAdapter(new ArtistaAdapter(context, artistas.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_artistas);
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
