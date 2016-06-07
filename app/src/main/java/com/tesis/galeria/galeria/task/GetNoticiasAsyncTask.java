package com.tesis.galeria.galeria.task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.adapter.NoticiaAdapter;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Lista.Noticias;
import com.tesis.galeria.galeria.modelos.Noticia;

import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by danie on 22/5/2016.
 */
public class GetNoticiasAsyncTask extends AsyncTask<Void, Void, Noticias> {

    private AppCompatActivity context;
    private TwoWayView recycler;

    private TextView tvEmpty;
    private Button btnEmpty;
    private ProgressWheel wheel;

    public GetNoticiasAsyncTask(AppCompatActivity context, TwoWayView recycler, ViewGroup v) {
        this.context = context;
        this.recycler = recycler;

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
    protected Noticias doInBackground(Void... params) {
        return ModelosDB.getNotcias();
    }

    @Override
    protected void onPostExecute(Noticias noticias) {
        super.onPostExecute(noticias);
        wheel.setVisibility(View.GONE);
        if (noticias != null) {
            if (noticias.items != null) {
                if (noticias.items.size() > 0) {
                    Log.d("NOTCIAS", String.valueOf(noticias.items.size()));
                    recycler.setAdapter(new NoticiaAdapter(context, noticias.items));
                    tvEmpty.setVisibility(View.GONE);
                    btnEmpty.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.sin_noticias);
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
