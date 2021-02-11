package com.oscarbermejo.proyecto_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.oscarbermejo.proyecto_android.almacen.AlmacenSQLite;
import com.oscarbermejo.proyecto_android.fragment.FragmentGrados;
import com.oscarbermejo.proyecto_android.pojos.Grado;
import com.oscarbermejo.proyecto_android.viewPager.ViewPagerFamilias;

import java.util.ArrayList;

public class ActividadFamilias extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AlmacenSQLite almacenSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_familias);

        //Assign variable
        almacenSQLite = new AlmacenSQLite(this);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout_grados);

        ArrayList<ArrayList<Grado>> listaGrados = new ArrayList<>();

        listaGrados.add(almacenSQLite.getGrados());
        listaGrados.add(almacenSQLite.getGradosMedios());
        listaGrados.add(almacenSQLite.getGradosSuperiores());

        ArrayList<String> titulosOpciones = new ArrayList<>();
        titulosOpciones.add("TODOS");
        titulosOpciones.add("GRADO MEDIO");
        titulosOpciones.add("GRADO SUPERIOR");

        prepareViewPager(viewPager, listaGrados, titulosOpciones);

        tabLayout.setupWithViewPager(viewPager);





    }


    private void prepareViewPager(ViewPager viewPager, ArrayList<ArrayList<Grado>> listaGrados, ArrayList<String> titulosOpciones){
        MainAdaptaer adaptaer = new MainAdaptaer(getSupportFragmentManager());
        viewPager.setAdapter(adaptaer);

        for(int i = 0; i < listaGrados.size();i++){
            FragmentGrados fragmentGrados = new FragmentGrados(this, listaGrados.get(i));

            Bundle bundle = new Bundle();

            bundle.putString("title", titulosOpciones.get(i));

            fragmentGrados.setArguments(bundle);

            adaptaer.addFragment(fragmentGrados);
        }
        viewPager.setAdapter(adaptaer);
    }



    private class MainAdaptaer extends FragmentPagerAdapter {
        private ArrayList<FragmentGrados> opciones;

        public MainAdaptaer(@NonNull FragmentManager fm) {
            super(fm);
            opciones = new ArrayList<>();
        }

        public MainAdaptaer(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            opciones = new ArrayList<>();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position){
                case 0:
                    title = "Todos los grados";
                    break;
                case 1:
                    title = "Grados Medios";
                    break;
                case 2:
                    title = "Grados Superiores";
                    break;
            }
            return title;
        }

        public void addFragment(FragmentGrados fragmentGrados){
            opciones.add(fragmentGrados);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return opciones.get(position);
        }

        @Override
        public int getCount() {
            return opciones.size();
        }
    }
}

