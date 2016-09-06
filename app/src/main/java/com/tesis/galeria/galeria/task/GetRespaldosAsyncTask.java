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
import com.tesis.galeria.galeria.adapter.RespaldoAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Resplados;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 23/7/2016.
 */
public class GetRespaldosAsyncTask extends AsyncTask<Void, Void, Resplados> {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private ViewGroup v;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;


    public GetRespaldosAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v) {
        this.context = context;
        this.recycler = recycler;
        this.v = v;

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
    protected Resplados doInBackground(Void... params) {
        return ModelosDB.getRespaldos();
    }

    @Override
    protected void onPostExecute(Resplados resplados) {
        super.onPostExecute(resplados);
        wheel.setVisibility(View.GONE);

        if (resplados != null) {
            if (resplados.items != null) {
                if (resplados.items.size() > 0) {
                    Log.d("RESPALDOS", String.valueOf(resplados.items.size()));
                    recycler.setAdapter(new RespaldoAdapter(context, resplados.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_respaldos);
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
