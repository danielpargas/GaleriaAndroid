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
import com.tesis.galeria.galeria.adapter.AsesoriaAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Asesorias;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 9/8/2016.
 */
public class GetAsesoriasAsyncTask extends AsyncTask<String, Void, Asesorias> {
    private AppCompatActivity context;
    private TwoWayView recycler;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;

    public GetAsesoriasAsyncTask(TwoWayView recycler, AppCompatActivity context, ViewGroup v) {
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
    protected Asesorias doInBackground(String... params) {
        return ModelosDB.getAsesoriasUsuario(params[0]);
    }

    @Override
    protected void onPostExecute(Asesorias asesorias) {
        super.onPostExecute(asesorias);
        wheel.setVisibility(View.GONE);
        if (asesorias != null) {
            if (asesorias.items != null) {
                if (asesorias.items.size() > 0) {
                    Log.d("ASESORIAS", String.valueOf(asesorias.items.size()));
                    recycler.setAdapter(new AsesoriaAdapter(context, asesorias.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_asesorias);
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
