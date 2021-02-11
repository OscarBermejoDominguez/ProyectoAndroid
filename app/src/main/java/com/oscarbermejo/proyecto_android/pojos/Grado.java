package com.oscarbermejo.proyecto_android.pojos;

public class Grado {
    private int idGrado;
    private String nombre, descripcion;
    private int familiaProfesional;
    private boolean gradoSuperior;

    public Grado() {

    }

    public Grado(int idGrado, String nombre, String descripcion, int familiaProfesional, boolean esGradoSuperior) {
        this.idGrado = idGrado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.familiaProfesional = familiaProfesional;
        this.gradoSuperior = esGradoSuperior;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(int familiaProfesional) {
        this.familiaProfesional = familiaProfesional;
    }

    public boolean isGradoSuperior() {
        return gradoSuperior;
    }

    public void setGradoSuperior(boolean esGradoSuperior) {
        this.gradoSuperior = esGradoSuperior;
    }

    public String getInserccion(){
        int esgradoSuperiorParser = 0;

        if(gradoSuperior)
            esgradoSuperiorParser = 1;

        return "INSERT INTO GRADO " +
                "VALUES ("+ idGrado +"" +
                ", '" + nombre  +"'" +
                ", '" + descripcion + "'" +
                "," + familiaProfesional + "" +
                ", "  +  esgradoSuperiorParser + ")";
    }
}
