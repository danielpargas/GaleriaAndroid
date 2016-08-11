package com.tesis.galeria.galeria;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.task.GetAsesoriasAsyncTask;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AsesoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsesoriasFragment extends Fragment {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    private static GetAsesoriasAsyncTask getAsesoriasAsyncTask;

    public AsesoriasFragment() {
        // Required empty public constructor
    }

    public static AsesoriasFragment newInstance() {
        AsesoriasFragment fragment = new AsesoriasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (AppCompatActivity) getActivity();
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_avaluo, container, false);

        recycler = (TwoWayView) rootView.findViewById(R.id.lista);
        btnEmpty = (Button) rootView.findViewById(R.id.btn_empty);

        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarAsyncTask(rootView);
            }
        });
        /*
        ActionBar actionBar = context.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.toolbar_asesorias);
        }
*/
        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }
        return rootView;
    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();
        getAsesoriasAsyncTask = new GetAsesoriasAsyncTask(recycler, context, rootView);
        getAsesoriasAsyncTask.execute(Utilidades.getIdUsuario(context));
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }


    private void cancelarAsyncTask() {
        if (getAsesoriasAsyncTask != null && (getAsesoriasAsyncTask.getStatus() == AsyncTask.Status.PENDING || getAsesoriasAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            getAsesoriasAsyncTask.cancel(true);
        }
    }
}
