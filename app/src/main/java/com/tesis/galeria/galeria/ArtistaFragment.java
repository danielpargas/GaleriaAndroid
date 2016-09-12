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
import com.tesis.galeria.galeria.task.GetArtistasAsyncTask;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistaFragment extends Fragment {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    private String mQuery;

    private static GetArtistasAsyncTask getArtistasAsyncTask;

    private static final String ARG_QUERY = "arg_query";

    public ArtistaFragment() {
        // Required empty public constructor
    }

    public static ArtistaFragment newInstance() {
        ArtistaFragment fragment = new ArtistaFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    public static ArtistaFragment newInstance(String query) {
        ArtistaFragment fragment = new ArtistaFragment();

        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (AppCompatActivity) getActivity();
        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_artista, container, false);

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
            actionBar.setTitle(R.string.toolbar_artistas);
        }

        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }

        return rootView;
    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();

        if (mQuery == null || mQuery.isEmpty()) {
            getArtistasAsyncTask = new GetArtistasAsyncTask(context, recycler, rootView);
        } else {
            getArtistasAsyncTask = new GetArtistasAsyncTask(context, recycler, rootView, mQuery);
        }
        getArtistasAsyncTask.execute();
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }


    private void cancelarAsyncTask() {
        if (getArtistasAsyncTask != null && (getArtistasAsyncTask.getStatus() == AsyncTask.Status.PENDING || getArtistasAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            getArtistasAsyncTask.cancel(true);
        }
    }

}
