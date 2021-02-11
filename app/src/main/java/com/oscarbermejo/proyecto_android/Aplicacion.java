package com.oscarbermejo.proyecto_android;

import android.app.Application;

import com.oscarbermejo.proyecto_android.pojos.Opcion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Aplicacion extends Application {
    private ArrayList<ArrayList> baseDatos;

    @Override
    public void onCreate() {
        super.onCreate();
        baseDatos = new ArrayList<>();
        crearOpcionesGradoMedio();
        crearOpcionesFPB();
    }

    public ArrayList<Opcion> getListaAt(int index){
        return baseDatos.get(index);
    }

    private void crearOpcionesGradoMedio(){
        Opcion[] opcionesE = {
                new Opcion( "Oferta educativa", new Opcion[] {
                        new Opcion( "Familias profesionales", null)
                }),

                new Opcion("Acceso a ciclos", new Opcion[]{
                        new Opcion( "Pruebas de Accceso", null),
                        new Opcion( "Curso Preparatorio y Curso " +
                                "de Formacion especifica para acceder a FP", null),
                }),

                new Opcion("FP a Distancia"),
                new Opcion("FP Dual"),
                new Opcion("Premios de FP"),
                new Opcion("Pruebas libres FP")
        };


        ArrayList<Opcion> listaGM = new ArrayList<>();
        for (int i = 0; i < opcionesE.length; i++) {
            listaGM.add(opcionesE[i]);
        }

        baseDatos.add(listaGM);
    }
    private void crearOpcionesFPB(){
        Opcion[] opciones = {
                new Opcion(R.drawable.richard, "Opcion 2", null)
        };

        ArrayList<Opcion> listaFP = new ArrayList<>();
        for (int i = 0; i < opciones.length; i++) {
            listaFP.add(opciones[i]);
        }

        baseDatos.add(listaFP);
    }
}
