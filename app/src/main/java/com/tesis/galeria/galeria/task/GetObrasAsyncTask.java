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
import com.tesis.galeria.galeria.adapter.ObraAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Obras;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 29/5/2016.
 */
public class GetObrasAsyncTask extends AsyncTask<Void, Void, Obras> {

    private int idArtista;

    private AppCompatActivity context;
    private TwoWayView recycler;
    private ViewGroup v;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;

    public GetObrasAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v, int idArtista) {
        this.context = context;
        this.recycler = recycler;
        this.v = v;
        this.idArtista = idArtista;

        tvEmpty = (TextView) v.findViewById(R.id.tv_empty);
        btnEmpty = (Button) v.findViewById(R.id.btn_empty);
        wheel = (ProgressWheel) v.findViewById(R.id.progress_wheel);
    }


    public GetObrasAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v) {
        this(context, recycler, v, 0);
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
    protected Obras doInBackground(Void... params) {
        if (idArtista > 0) {
            return ModelosDB.getObrasArtista(idArtista);
        } else {
            return ModelosDB.getObras();
        }
    }

    @Override
    protected void onPostExecute(Obras obras) {
        super.onPostExecute(obras);
        wheel.setVisibility(View.GONE);
        if (obras != null) {
            if (obras.items != null) {
                if (obras.items.size() > 0) {
                    Log.d("OBRAS", String.valueOf(obras.items.size()));
                    recycler.setAdapter(new ObraAdapter(context, obras.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_obras);
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
