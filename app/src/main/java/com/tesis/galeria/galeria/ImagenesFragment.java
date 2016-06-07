package com.tesis.galeria.galeria;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.adapter.ImagenAdapter;
import com.tesis.galeria.galeria.modelos.ImagenAvaluo;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImagenesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImagenesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGENES_AVALUO = "imagenes_avaluo";

    // TODO: Rename and change types of parameters
    private ArrayList<ImagenAvaluo> imagenesAvaluos;
    private TwoWayView recycler;
    private ImagenAdapter adapter;
    private AppCompatActivity context;

    public ImagenesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imagenesAvaluos Imagenes del Avaluo
     * @return A new instance of fragment ImagenesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImagenesFragment newInstance(ArrayList<ImagenAvaluo> imagenesAvaluos) {
        ImagenesFragment fragment = new ImagenesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGENES_AVALUO, imagenesAvaluos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = (AppCompatActivity) getActivity();

        if (getArguments() != null) {
            imagenesAvaluos = (ArrayList<ImagenAvaluo>) getArguments().getSerializable(ARG_IMAGENES_AVALUO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_imagenes, container, false);

        recycler = (TwoWayView) rootView.findViewById(R.id.lista);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (imagenesAvaluos != null) {
            adapter = new ImagenAdapter(context, imagenesAvaluos);
            recycler.setAdapter(adapter);
        }
    }
}
