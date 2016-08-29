package com.tesis.galeria.galeria.task;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.db.ModelosDB;

import java.io.File;

/**
 * Created by danie on 21/8/2016.
 */
public class ProcesarAvaluoAsyncTask extends AsyncTask<File, Void, Boolean> {

    private int mIdAvaluo;
    private String mPrecio;
    private String mMediaType;
    private AppCompatActivity mContext;

    private ProgressDialog progressDialog;
    private MaterialDialog dialog;


    public ProcesarAvaluoAsyncTask(int mIdAvaluo, String mPrecio, String mMediaType, AppCompatActivity mContext) {
        this.mIdAvaluo = mIdAvaluo;
        this.mPrecio = mPrecio;
        this.mMediaType = mMediaType;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(mContext.getString(R.string.mensaje_enviando_datos));
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel(true);
            }
        });
        progressDialog.show();


        dialog = new MaterialDialog.Builder(mContext)
                .theme(Theme.LIGHT)
                .title(R.string.error_comprobar_datos)
                .content(R.string.mensaje_error_enviar_datos)
                .positiveText(R.string.aceptar)
                .autoDismiss(true)
                .build();

    }

    @Override
    protected Boolean doInBackground(File... params) {
        return ModelosDB.procesarAvaluo(params[0], String.valueOf(mIdAvaluo), mPrecio, mMediaType);
    }

    @Override
    protected void onPostExecute(Boolean procesado) {
        super.onPostExecute(procesado);
        progressDialog.dismiss();

        if (procesado) {
            mContext.finish();
            Toast.makeText(mContext, "Avaluado exitosamente", Toast.LENGTH_LONG).show();
        } else {
            dialog.show();
        }
    }
}
