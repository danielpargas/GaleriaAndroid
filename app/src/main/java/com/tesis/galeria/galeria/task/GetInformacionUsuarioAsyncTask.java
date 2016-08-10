package com.tesis.galeria.galeria.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.tesis.galeria.galeria.Constantes;
import com.tesis.galeria.galeria.MainActivity;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Ingreso;
import com.tesis.galeria.galeria.modelos.Usuario;
import com.tesis.galeria.galeria.utilidades.Utilidades;

/**
 * Created by danie on 21/5/2016.
 */
public class GetInformacionUsuarioAsyncTask extends AsyncTask<Ingreso, Void, Usuario> {

    private AppCompatActivity context;
    private ProgressDialog progressDialog;

    public GetInformacionUsuarioAsyncTask(AppCompatActivity context) {
        this.context = context;
    }

    public GetInformacionUsuarioAsyncTask(AppCompatActivity context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Usuario doInBackground(Ingreso... params) {
        return ModelosDB.getInformacionUsuario(params[0].access_token);
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        super.onPostExecute(usuario);
        if (usuario != null) {
            if (Utilidades.setIngreso(usuario, context)) {
                progressDialog.hide();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setAction(Constantes.ACTION_INGRESO);
                context.finish();
                context.startActivity(intent);
            }
        }
        progressDialog.hide();
    }
}
