package com.tesis.galeria.galeria;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.task.IngresoAsyncTask;

public class LoginActivity extends AppCompatActivity {

    private AppCompatActivity context;
    private IngresoAsyncTask ingresoAsyncTask;

    private Button btnIngresar;

    private EditText etUsuario;
    private EditText etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        inicializarComponentes();
        iinicializarEventos();

    }


    public void inicializarComponentes() {
        btnIngresar = (Button) findViewById(R.id.btn_ingresar);

        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etClave = (EditText) findViewById(R.id.et_clave);
    }

    public void ingresar() {

        boolean error = false;

        String usuario = etUsuario.getText().toString().trim();
        String clave = etClave.getText().toString().trim();

        if (usuario.isEmpty()) {
            etUsuario.setError(getString(R.string.campo_vacio));
            error = true;
        }

        if (clave.isEmpty()) {
            etClave.setError(getString(R.string.campo_vacio));
            error = true;
        }

        if (!error) {
            cancelarAsyncTask();
            ingresoAsyncTask = new IngresoAsyncTask(context);
            ingresoAsyncTask.execute(usuario, clave);
        }
    }

    public void iinicializarEventos() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });
    }

    public void cancelarAsyncTask() {
        if (ingresoAsyncTask != null && (ingresoAsyncTask.getStatus() == AsyncTask.Status.PENDING || ingresoAsyncTask.getStatus() == AsyncTask.Status.PENDING)) {
            ingresoAsyncTask.cancel(true);
        }
    }


}
