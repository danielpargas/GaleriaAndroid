package com.tesis.galeria.galeria;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.task.GetNoticiasAsyncTask;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticiasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticiasFragment extends Fragment {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    private static GetNoticiasAsyncTask noticiasAsyncTask;

    public NoticiasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NoticiasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticiasFragment newInstance() {
        NoticiasFragment fragment = new NoticiasFragment();
        //  Bundle args = new Bundle();

        //    fragment.setArguments(args);
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
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notcias, container, false);
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
            actionBar.setTitle(R.string.toolbar_noticias);
        }

        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();
        noticiasAsyncTask = new GetNoticiasAsyncTask(context, recycler, rootView);
        noticiasAsyncTask.execute();
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }

    public void cancelarAsyncTask() {
        if (noticiasAsyncTask != null && (noticiasAsyncTask.getStatus() == AsyncTask.Status.PENDING || noticiasAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            noticiasAsyncTask.cancel(true);
        }
    }

}
