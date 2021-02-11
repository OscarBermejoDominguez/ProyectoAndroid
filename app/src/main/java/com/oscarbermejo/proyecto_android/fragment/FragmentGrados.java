package com.oscarbermejo.proyecto_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oscarbermejo.proyecto_android.R;
import com.oscarbermejo.proyecto_android.adaptadores.AdaptadorGrados;
import com.oscarbermejo.proyecto_android.pojos.Grado;

import java.util.ArrayList;

public class FragmentGrados extends Fragment {

    //Lista de grados que va contener el gridlayout
    private ArrayList<Grado> grados;

    private Context context;

    public FragmentGrados(Context context )
    {
        //Se necesita un constructor vacio
    }

    /**
     * Permite crear un fragment con la lista de grados pasados
     * @param grados que queremos insertar en el fragment
     */
    public FragmentGrados(Context context,  ArrayList<Grado> grados) {
        this.grados = grados;
        this.context = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflamos la vista correspondiente
       return inflater.inflate(R.layout.lista_grados, container, false);


    }

    public void setGrados(ArrayList<Grado> grados) {
        this.grados = grados;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Optenemos el gridlayout que va a contener todos los grados
        GridView listaGrados = getView().findViewById(R.id.gridLayoutGrado);
        listaGrados.setNumColumns(2);
        listaGrados.setAdapter(new AdaptadorGrados(grados, getContext()));

        super.onViewCreated(view, savedInstanceState);
    }
}