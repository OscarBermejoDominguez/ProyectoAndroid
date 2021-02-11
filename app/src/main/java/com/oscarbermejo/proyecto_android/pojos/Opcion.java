package com.oscarbermejo.proyecto_android.pojos;

import java.util.ArrayList;

public class Opcion {

    private int icono; //Icono que representa la opcion actual
    private String titulo; //Titulo que tiene la opcion
    private ArrayList<Opcion> opcionesE; //SubopcionesE que puede tener la opcion

    public Opcion(String titulo, Opcion[] opcionesEE){
        this(titulo);
        if(opcionesEE != null){
            opcionesE = new ArrayList<>();
            for (int i = 0; i < opcionesEE.length; i++){
                opcionesE.add(opcionesEE[i]);
            }
        }
    }

    /**
     * Constructor que solo asigna un titulo a la opcion
     * @param titulo que va a tener la opcion de la lista
     */
    public Opcion(String titulo){
        this(0, titulo, null);
    }

    /**
     * Asigna un titulo y una serie de opcionesE que tiene ese titulo
     * @param titulo que va a representar la opcion de la lista
     * @param opcionesE que va a contener la opcion de la lista
     */
    public Opcion(int icono, String titulo, ArrayList<Opcion> opcionesE) {
        if(icono != 0) //Comprobamos si se ha insertado un icono en el constructor
            this.icono = icono;
        this.titulo = titulo;
        this.opcionesE = opcionesE;
    }


    public String getTituloAt(int index){
        return opcionesE.get(index).getTitulo();
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Opcion> getopcionesE() {
        return opcionesE;
    }

    public void setopcionesE(ArrayList<Opcion> opcionesE) {
        this.opcionesE = opcionesE;
    }

    @Override
    public String toString() {
        return "Opcion{" +
                "icono=" + icono +
                ", titulo='" + titulo + '\'' +
                ", opcionesE=" + opcionesE +
                '}';
    }
}
