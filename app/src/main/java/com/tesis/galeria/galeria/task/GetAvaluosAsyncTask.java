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
import com.tesis.galeria.galeria.adapter.AvaluoAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Avaluos;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 26/5/2016.
 */
public class GetAvaluosAsyncTask extends AsyncTask<String, Void, Avaluos> {
    private AppCompatActivity context;
    private TwoWayView recycler;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;

    public GetAvaluosAsyncTask(TwoWayView recycler, AppCompatActivity context, ViewGroup v) {
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
    protected Avaluos doInBackground(String... params) {
        return ModelosDB.getAvaluosUsuario(params[0]);
    }

    @Override
    protected void onPostExecute(Avaluos avaluos) {
        super.onPostExecute(avaluos);
        wheel.setVisibility(View.GONE);
        if (avaluos != null) {
            if (avaluos.items != null) {
                if (avaluos.items.size() > 0) {
                    Log.d("AVALUOS", String.valueOf(avaluos.items.size()));
                    recycler.setAdapter(new AvaluoAdapter(context, avaluos.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_avaluos);
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
