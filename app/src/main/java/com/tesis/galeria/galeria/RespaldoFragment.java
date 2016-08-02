package com.tesis.galeria.galeria;


import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.servicios.AddRespaldoService;
import com.tesis.galeria.galeria.task.GetRespaldosAsyncTask;
import com.tesis.galeria.galeria.utilidades.NotificationUtils;

import org.lucasr.twowayview.widget.DividerItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RespaldoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RespaldoFragment extends Fragment {

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    FloatingActionButton fab = null;

    private static GetRespaldosAsyncTask getRespaldosAsyncTask;

    public RespaldoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RespaldoFragment.
     */

    public static RespaldoFragment newInstance() {
        RespaldoFragment fragment = new RespaldoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = (AppCompatActivity) getActivity();

        fab = (FloatingActionButton) context.findViewById(R.id.fab);

        if (fab != null) {
            fab.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.ic_add);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddRespaldoService.startActionRespaldo(context);
                }
            });

        }

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_respaldo, container, false);
        recycler = (TwoWayView) rootView.findViewById(R.id.lista);

        final Drawable divider = ContextCompat.getDrawable(context, R.drawable.divider);
        recycler.addItemDecoration(new DividerItemDecoration(divider));

        btnEmpty = (Button) rootView.findViewById(R.id.btn_empty);

        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarAsyncTask(rootView);
            }
        });
        ActionBar actionBar = context.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.toolbar_respaldos);
        }

        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (fab != null) {
            fab.setVisibility(View.GONE);
        }

    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();
        getRespaldosAsyncTask = new GetRespaldosAsyncTask(context, recycler, rootView);
        getRespaldosAsyncTask.execute();
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }


    private void cancelarAsyncTask() {
        if (getRespaldosAsyncTask != null && (getRespaldosAsyncTask.getStatus() == AsyncTask.Status.PENDING || getRespaldosAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            getRespaldosAsyncTask.cancel(true);
        }
    }


}
