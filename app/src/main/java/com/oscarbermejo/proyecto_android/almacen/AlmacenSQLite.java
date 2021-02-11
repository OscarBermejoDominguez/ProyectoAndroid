package com.oscarbermejo.proyecto_android.almacen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.oscarbermejo.proyecto_android.pojos.Grado;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AlmacenSQLite extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String NOMBRE_DB = "db_ies";

    public AlmacenSQLite(Context context){super(context, NOMBRE_DB, null, VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("APLICACION", "Creando la tabla familias y grados");

        //Conseguimos las tablas de la base de datos
        String[] comandosTablas = getComandosCreacionTablas();
        //Ejecutamos los comandos que crean las tablas
        for(int i = 0; i < comandosTablas.length; i++){
            db.execSQL(comandosTablas[i]);
        }

        String[] datosFamilias = getDatosFamilia();
        //Insertamos los datos dentro de las familias profesionales
        for(int i = 0; i < datosFamilias.length; i++){
            db.execSQL(datosFamilias[i]);
        }

        String[] datosGrados = getComandosCreacionGrados();
        //Insertamos los datos dentro de los grados
        for(int i = 0; i < datosGrados.length; i++){
            db.execSQL(datosGrados[i]);
        }
        Log.i("APLICACION", "SE HA INSERTADO LAS TABLAS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Metodo que permite cambiar los datos de las tablas cuando queremos actualizarlas
        //   y tenemos algo mal
    }

    private String[] getComandosCreacionTablas(){
        String[] comandoCrearTablas = {
                //Crea la tabla familia profesional
                "CREATE TABLE Familia (idFamilia INTEGER, Nombre TEXT, PRIMARY KEY(idFamilia));"
                ,
                //Crea la tabla grado
            "CREATE TABLE Grado (idGrado INTEGER, Nombre TEXT, Descripcion TEXT, idFamilia INTEGER, esGradoSuperior INTEGER," +
                    "PRIMARY KEY(idGrado, Nombre), FOREIGN KEY(idFamilia) REFERENCES Familia (idFamilia))"};
        return comandoCrearTablas;
    }

    public ArrayList<Grado> getGrados(){
        String consulta = "select * from Grado";

        ArrayList<Grado> gradosO = getGradosConsulta(consulta);
        if(gradosO.size() == 0)
            Log.i("APLICACION", "ERROR NO HAY GRADOS");
        return gradosO;
    }



    public ArrayList<Grado> getGradosMedios(){
        String consulta = "select * from Grado where esGradoSuperior = 0";
        ArrayList<Grado> gradosO = getGradosConsulta(consulta);
        if(gradosO.size() == 0)
            Log.i("APLICACION", "ERROR NO HAY GRADOS MEDIOS");
        return gradosO;
    }

    public ArrayList<Grado> getGradosSuperiores(){
        String consulta = "select * from Grado where esGradoSuperior = 1";
        ArrayList<Grado> gradosO = getGradosConsulta(consulta);
        if(gradosO.size() == 0)
            Log.i("APLICACION", "ERROR NO HAY GRADOS SUPERIORES");
        return gradosO;
    }

    private ArrayList<Grado> getGradosConsulta (String consulta ){
        //ArrayList con todos los grados optenidos de la consulta
        ArrayList<Grado> gradosO = new ArrayList<>();

        //Optenemos la base de datos
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorGrados = db.rawQuery(consulta, null);
        if(cursorGrados.moveToFirst()){
            //Recorremos el cursor que nos devuelve la consulta
            do{
                //Comprobamos si es grado superior
                boolean esGradoSuperior = false;
                if(cursorGrados.getInt(4) == 1)
                    esGradoSuperior = true;
                //Creamos el grado actual en el puntero
                Grado gradoN = new Grado(cursorGrados.getInt(0),
                        cursorGrados.getString(1), cursorGrados.getString(2),
                        cursorGrados.getInt(3), esGradoSuperior);
                //insertamos el grado dentro de la lista
                gradosO.add(gradoN);
            }while(cursorGrados.moveToNext());
            cursorGrados.close(); //Cerramos el cursor
        }
        db.close();//Cerramos la base de datos
        return gradosO; //Devolvemos los grados
    }


    /**
     * Permite crear una serie de familias, dentro de la base de datos, que esten insertadas en
     * el array de String al inicio del codigo
     * @return String[] con los strings necesarios para crear las familias
     */
    private String[] getDatosFamilia(){
        String[] nombresFamilias = {"Actividades fisicas y deportivas",
                "Administracion y gestion",
                "Agraria", "Artes gráficas"};
        //Creamos el array de string que va contener todas las consultas para insertar
        /// datos dentro de las familias
        String[] salidasConsultas = new String[nombresFamilias.length];

        //Creamos las consultas con los nombres de las familias
        for(int i = 0; i< nombresFamilias.length; i++){
            salidasConsultas[i] = "INSERT INTO Familia VALUES ("+ (i+1) + ", '"+ nombresFamilias[i] + "')";
        }

        //Devolvemos una salida con los datos de las consultas
        return salidasConsultas;
    }

    /**
     * Permite insertar datos dentro de los grados de la base de datos
     * @return String[] con los comandos necesarios para insertar los
     * datos dentro de cada uno de los grados
     */
    private String[] getComandosCreacionGrados(){
        ///CREACION DE LOS DATOS DE LOS GRADOS///

        //Nombres de los grados
        String[] nombresGrados = {
                "Gui en el medio natural y de tiempo libre",
                "Acondicionamiento Fisico",
                "Enseñanza y Animacion Sociodeportiva"
        };

        //Descripciones que van a contener los grados, estas descripciones
        // van a corde con los nombres de lso grados
        String[] descripcionesGrados = {"" +
                "La competencia general de este título consiste en organizar " +
                "itinerarios y guiar grupos por entornos naturales de baja y media montaña, " +
                "terreno nevado tipo nórdico, cavidades de baja dificultad, barrancos de bajo riesgo," +
                " medio acuático e instalaciones de ocio y aventura, progresando a pie",
                "La competencia general de este título consiste en elaborar, coordinar, desarrollar y " +
                "evaluar programas de acondicionamiento físico para todo tipo de personas usuarias " +
                "y en diferentes espacios de práctica",
                "La competencia general de este título consiste en elaborar, gestionar y evaluar proyectos " +
                        "de animación físico-deportivos recreativos para todo tipo de usuarios, programando y"
        };

        //Relaciones de los grados con las familias profesionales
        Integer[] familiasGrados = {1,1,1};

        //Indica dentro de la base de datos si el grado actual es
        //    un grado medio o un grado superior
        Integer[] esGradoSuperior = {0,1,1};

        String[] salidaComandos = new String[nombresGrados.length];

        for(int i = 0; i < nombresGrados.length; i++){
            boolean esgradoSuperior = false;
            if(esGradoSuperior[i] == 1)
                esgradoSuperior = true;
            //Creamos los grados y los devolvemos por la salida de comandos
            salidaComandos[i] = new Grado((i+1), nombresGrados[i], descripcionesGrados[i], familiasGrados[i],  esgradoSuperior).getInserccion();
        }
        //Devolvemos todas las sentencias para insertar los datos de los grados
        return salidaComandos;
    }
}
