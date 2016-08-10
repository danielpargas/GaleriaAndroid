package com.tesis.galeria.galeria;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tesis.galeria.R;
import com.tesis.galeria.galeria.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaFragment extends Fragment {


    private AppCompatActivity context;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    public CuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
        //   Bundle args = new Bundle();
        //    args.putString(ARG_PARAM1, param1);
        //   args.putString(ARG_PARAM2, param2);
        //   fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //    mParam1 = getArguments().getString(ARG_PARAM1);
            //    mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_cuenta, container, false);

        context = (AppCompatActivity) getActivity();
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        inicializarViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                inicializarTabsIcon();
            }
        });

        ActionBar ab = context.getSupportActionBar();

        if (ab != null) {
            ab.setTitle("Cuenta");
        }

        return rootView;
    }

    private void inicializarTabsIcon() {
        RelativeLayout tabPerfil = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ((ImageView) tabPerfil.findViewById(R.id.iv_icon)).setImageResource(R.drawable.placeholder);
        ((TextView) tabPerfil.findViewById(R.id.tv_nombre)).setText("Avalúo");
        tabLayout.getTabAt(0).setCustomView(tabPerfil);

        RelativeLayout tabAvaluos = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ((ImageView) tabAvaluos.findViewById(R.id.iv_icon)).setImageResource(R.drawable.placeholder);
        ((TextView) tabAvaluos.findViewById(R.id.tv_nombre)).setText("Asesorias");
        tabLayout.getTabAt(1).setCustomView(tabAvaluos);

        RelativeLayout tabPublicaciones = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ((ImageView) tabPublicaciones.findViewById(R.id.iv_icon)).setImageResource(R.drawable.placeholder);
        ((TextView) tabPublicaciones.findViewById(R.id.tv_nombre)).setText("Publicaciones");
        tabLayout.getTabAt(2).setCustomView(tabPublicaciones);
    }

    private void inicializarViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(AvaluosFragment.newInstance(), "Avaluos");
        adapter.addFragment(AsesoriasFragment.newInstance(), "Asesorias");
        adapter.addFragment(PublicacionesFragment.newInstance(), "Publicaciones");

        viewPager.setAdapter(adapter);
    }

}
