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
import com.tesis.galeria.galeria.task.GetAvaluosAsyncTask;
import com.tesis.galeria.galeria.task.GetObrasAsyncTask;
import com.tesis.galeria.galeria.utilidades.Utilidades;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ObraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ObraFragment extends Fragment {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    private int idArtista = 0;
    private String mQuery;

    private static GetObrasAsyncTask getObrasAsyncTask;

    public ObraFragment() {
        // Required empty public constructor
    }


    private static final String ARG_ID_ARTISTA = "arg_id_artista";
    private static final String ARG_QUERY = "arg_query";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ObraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ObraFragment newInstance() {
        ObraFragment fragment = new ObraFragment();
        return fragment;
    }

    public static ObraFragment newInstance(String query) {
        ObraFragment fragment = new ObraFragment();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_QUERY, query);

        fragment.setArguments(bundle);

        return fragment;
    }

    public static ObraFragment newInstance(int idArtista) {
        ObraFragment fragment = new ObraFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ID_ARTISTA, idArtista);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (AppCompatActivity) getActivity();
        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
            idArtista = getArguments().getInt(ARG_ID_ARTISTA, 0);
        } else {
            idArtista = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_obra, container, false);

        recycler = (TwoWayView) rootView.findViewById(R.id.lista);
        btnEmpty = (Button) rootView.findViewById(R.id.btn_empty);

        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarAsyncTask(rootView);
            }
        });
        ActionBar actionBar = context.getSupportActionBar();

        if (actionBar != null) {
            if (mQuery == null) {
                actionBar.setTitle(R.string.toolbar_obras);
            } else {
                actionBar.setTitle(mQuery);
            }
        }

        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }

        return rootView;
    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();
        if (mQuery == null || mQuery.isEmpty()) {
            getObrasAsyncTask = new GetObrasAsyncTask(mQuery, context, recycler, rootView);
        } else if (idArtista > 0) {
            getObrasAsyncTask = new GetObrasAsyncTask(context, recycler, rootView, idArtista);
        } else {
            getObrasAsyncTask = new GetObrasAsyncTask(context, recycler, rootView);
        }
        getObrasAsyncTask.execute();
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }


    private void cancelarAsyncTask() {
        if (getObrasAsyncTask != null && (getObrasAsyncTask.getStatus() == AsyncTask.Status.PENDING || getObrasAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            getObrasAsyncTask.cancel(true);
        }
    }

}
