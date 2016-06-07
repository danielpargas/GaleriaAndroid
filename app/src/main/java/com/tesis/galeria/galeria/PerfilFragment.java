package com.tesis.galeria.galeria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.galeria.R;
import com.tesis.galeria.galeria.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private TextView tvCorreo;
    private TextView tvTelefono;

    private ImageView ivImagen;

    private AppCompatActivity context;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_perfil, container, false);


        ivImagen = (ImageView) rootView.findViewById(R.id.iv_imagen);
        tvCorreo = (TextView) rootView.findViewById(R.id.tv_correo);
        tvTelefono = (TextView) rootView.findViewById(R.id.tv_telefono);

        Picasso.with(context)
                .load(Constantes.DOMINIO + Constantes.URL_IMAGEN_USUARIO + Utilidades.getImagenUsuario(context))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivImagen);

        tvCorreo.setText(Utilidades.getNombreUsuario(context));
        tvTelefono.setText(getString(R.string.no_disponible));

        return rootView;
    }

}
