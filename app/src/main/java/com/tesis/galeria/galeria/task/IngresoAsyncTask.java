package com.tesis.galeria.galeria.task;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Ingreso;

/**
 * Created by danie on 21/5/2016.
 */
public class IngresoAsyncTask extends AsyncTask<String, Void, Ingreso> {

    private AppCompatActivity context;
    private GetInformacionUsuarioAsyncTask usuarioAsyncTask;

    private ProgressDialog progressDialog;

    public IngresoAsyncTask(AppCompatActivity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(context.getString(R.string.mensaje_espera_ingreso));
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cancel(true);
            }
        });
        progressDialog.show();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        cancelarAsynkTask();
    }

    @Override
    protected Ingreso doInBackground(String... params) {

        String usuario = params[0];
        String clave = params[1];

        return ModelosDB.comprobarRegitroUsuario(usuario, clave);
    }

    @Override
    protected void onPostExecute(Ingreso ingreso) {
        super.onPostExecute(ingreso);
        if (ingreso != null) {
            if (ingreso.error == null) {
                if (ingreso.access_token != null && !ingreso.access_token.isEmpty()) {
                    Log.d("INGRESO", "Ingreso Exitoso");
                    progressDialog.setCancelable(false);
                    usuarioAsyncTask = new GetInformacionUsuarioAsyncTask(context, progressDialog);
                    usuarioAsyncTask.execute(ingreso);
                }
            } else {
                Log.d("INGRESO", ingreso.error_description);
            }
        }
    }

    private void cancelarAsynkTask() {
        if (usuarioAsyncTask != null && (usuarioAsyncTask.getStatus() == Status.PENDING || usuarioAsyncTask.getStatus() == Status.RUNNING)) {
            usuarioAsyncTask.cancel(true);
        }
    }

}
