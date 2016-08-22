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
import com.tesis.galeria.galeria.utilidades.Utilidades;

import org.lucasr.twowayview.widget.TwoWayView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvaluosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvaluosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private AppCompatActivity context;
    private TwoWayView recycler;
    private Button btnEmpty;

    private boolean mTodos = false;

    private static final String PARAM_CARGAR_TODOS = "param_cargar_todos";


    private static GetAvaluosAsyncTask getAvaluosAsyncTask;

    public AvaluosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AvaluosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvaluosFragment newInstance() {
        AvaluosFragment fragment = new AvaluosFragment();
        //Bundle args = new Bundle();
        //fragment.setArguments(args);
        return fragment;
    }

    public static AvaluosFragment newInstance(boolean todos) {
        AvaluosFragment fragment = new AvaluosFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(PARAM_CARGAR_TODOS, todos);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = (AppCompatActivity) getActivity();
        if (getArguments() != null) {
            mTodos = getArguments().getBoolean(PARAM_CARGAR_TODOS, false);
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
            actionBar.setTitle(R.string.toolbar_avaluos);
        }
*/
        if (recycler.getAdapter() == null) {
            iniciarAsyncTask(rootView);
        }
        return rootView;
    }

    public void iniciarAsyncTask(ViewGroup rootView) {
        cancelarAsyncTask();
        getAvaluosAsyncTask = new GetAvaluosAsyncTask(recycler, context, rootView);
        if (!mTodos) {
            getAvaluosAsyncTask.execute(Utilidades.getIdUsuario(context));
        } else {
            getAvaluosAsyncTask.execute();
        }
    }

    public void iniciarAsyncTask() {
        iniciarAsyncTask((ViewGroup) getView());
    }


    private void cancelarAsyncTask() {
        if (getAvaluosAsyncTask != null && (getAvaluosAsyncTask.getStatus() == AsyncTask.Status.PENDING || getAvaluosAsyncTask.getStatus() == AsyncTask.Status.RUNNING)) {
            getAvaluosAsyncTask.cancel(true);
        }
    }

}
