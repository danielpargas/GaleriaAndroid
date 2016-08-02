package com.tesis.galeria.galeria.task;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.db.ConexionDB;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.ChangePasswordBindingModel;

/**
 * Created by danie on 24/7/2016.
 */
public class CambiarClaveAsyncTask extends AsyncTask<ChangePasswordBindingModel, Void, Boolean> {

    private AppCompatActivity context;
    private ViewGroup v;
    private ProgressDialog dialog;

    public CambiarClaveAsyncTask(AppCompatActivity context, ViewGroup v) {
        this.context = context;
        this.v = v;


        dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.cambiando_clave));
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel(true);
            }
        });

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();

    }

    @Override
    protected Boolean doInBackground(ChangePasswordBindingModel... params) {
        return ModelosDB.cambiarClave(params[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dialog.dismiss();
    }
}
