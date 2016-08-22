package com.tesis.galeria.galeria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.db.ModelosDB;
import com.tesis.galeria.galeria.modelos.Ingreso;
import com.tesis.galeria.galeria.modelos.Usuario;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcesarAvaluoActivity extends AppCompatActivity {

    private FilePickerDialog dialog;
    private AppCompatActivity context;

    private static File archivoActual = null;

    private ImageView ivCerrar;

    private CardView cvArchivo;

    private TextView tvNombre;
    private TextView tvTamano;
    private TextView tvFecha;

    private TextInputLayout tilPrecio;

    private EditText etPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar_avaluo);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Procesar avalúo");
        }

        inicializarComponentes();
        inicializarEventos();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;//new String[]{"txt", "ptt"};

        dialog = new FilePickerDialog(ProcesarAvaluoActivity.this, properties);

        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                if (files != null && files.length > 0) {
                    archivoActual = new File(files[0]);
                    mostrarArchivo();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    public void inicializarComponentes() {

        tvNombre = (TextView) context.findViewById(R.id.tv_nombre);
        tvTamano = (TextView) context.findViewById(R.id.tv_tamano);
        tvFecha = (TextView) context.findViewById(R.id.tv_fecha);

        etPrecio = (EditText) context.findViewById(R.id.et_precio);

        tilPrecio = (TextInputLayout) context.findViewById(R.id.til_precio);

        cvArchivo = (CardView) context.findViewById(R.id.cv_archivo);

        ivCerrar = (ImageView) context.findViewById(R.id.iv_cerrar);
    }

    public void inicializarEventos() {
        ivCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarArchivo();
            }
        });

        cvArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        etPrecio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilPrecio.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void mostrarArchivo() {
        if (archivoActual != null) {
            cvArchivo.setVisibility(View.VISIBLE);
            actualizarArchivo();
        } else {
            ocultarArchivo();
        }
    }

    public void actualizarArchivo() {
        if (archivoActual != null) {
            tvNombre.setText(archivoActual.getName());
            tvTamano.setText(Utilidades.humanReadableByteCount(archivoActual.length(), false));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            tvFecha.setText(currentDateandTime);
        } else {
            ocultarArchivo();
        }
    }

    public void ocultarArchivo() {
        archivoActual = null;
        cvArchivo.setVisibility(View.GONE);
    }

    public boolean esValido() {

        boolean error = false;

        String precio = etPrecio.getText().toString();

        if (precio.isEmpty()) {
            tilPrecio.setError("El precio no puede estar en blanco");
            error = true;
        }

        if (archivoActual == null) {
            Snackbar.make(getCurrentFocus(), "Adjunte un archivo", Snackbar.LENGTH_LONG).show();
            error = true;
        }

        return !error;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (dialog != null) {   //Show dialog if the read permission has been granted.
                        dialog.show();
                    }
                } else {
                    //Permission has not been granted. Notify the user.
                    Toast.makeText(ProcesarAvaluoActivity.this, "Permission is Required for getting list of files", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_procesar_avaluo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_enviar) {

            if (esValido()) {

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarArchivo();
    }

}