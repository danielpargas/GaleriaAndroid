package com.tesis.galeria.galeria;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.modelos.Obra;
import com.tesis.galeria.galeria.task.GetObraAsyncTask;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetallesObraActivity extends AppCompatActivity {

    private AppCompatActivity mContext;
    private Obra obra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_obra);

        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }
            });
        }

        Bundle bundle = getIntent().getExtras().getBundle(Constantes.PARAM_EXTRA);
        if (bundle != null) {
            obra = (Obra) bundle.getSerializable(Constantes.PARAM_DATOS_OBRA);
            if (ab != null) {
                ab.setTitle(obra.titulo);
            }
        }
        new GetObraAsyncTask(mContext).execute(obra.id);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



}
