package com.oscarbermejo.proyecto_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.oscarbermejo.proyecto_android.adaptadores.AdaptadorListaOpciones;
import com.oscarbermejo.proyecto_android.pojos.Opcion;

import java.util.ArrayList;

public class ListaInf extends AppCompatActivity {
    private Aplicacion aplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_inf);

        String tituloB = getIntent().getStringExtra("tipoMenu");

        //if ternario nos sirve para devolver dos valores dependiendo del if ?
        //  entra cuando la consulta sea cierta : cuando no sea correcta
        aplicacion = (Aplicacion)this.getApplication();

        //Lista seleccionada que vamos a insertar en el ListView
        ListView listaO = findViewById(R.id.listaOpciones);
        ArrayList<Opcion> lista = conseguirLista(tituloB);
        AdaptadorListaOpciones adaptador = new AdaptadorListaOpciones(this, lista);
        listaO.setAdapter(adaptador);
    }

    private ArrayList<Opcion> conseguirLista(String tituloB){
        ArrayList<Opcion> listaS = null;
        int[] ids = { //Ids de las diferentes opciones que tenemos por botones
                R.string.gradoMedioSuperior,
                R.string.fpBasica,
                R.string.innovacionFP,
                R.string.acreditacionDeCompe,
                R.string.normaFP,
                R.string.preguntas};

        for(int i = 0; i < ids.length; i++){
            String opcionS = getResources().getString(ids[i]);
            if(opcionS.equalsIgnoreCase(tituloB)) //Seleccionamos la lista que corresponde con el boton
                listaS = aplicacion.getListaAt(i);
        }

        return listaS;
    }
}