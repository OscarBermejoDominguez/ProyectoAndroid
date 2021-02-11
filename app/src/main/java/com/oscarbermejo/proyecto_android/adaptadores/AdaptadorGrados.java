package com.oscarbermejo.proyecto_android.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oscarbermejo.proyecto_android.R;
import com.oscarbermejo.proyecto_android.pojos.Grado;

import java.util.ArrayList;

public class AdaptadorGrados extends BaseAdapter {
    //Grados que va contener la vista
    private ArrayList<Grado> grados;
    //Contexto que viene de la clase que crea el adaptador
    private Context context;

    public AdaptadorGrados(ArrayList<Grado> grados, Context context) {
        this.grados = grados;
        this.context = context;
    }

    @Override
    public int getCount() {
        return grados.size();
    }

    @Override
    public Object getItem(int position) {
        return grados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //optenemos el inflador que nos permite cargar el layout de los grados
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Conseguimos el layout del grado que vamos a insertar en la lista
        int idLayout = R.layout.layout_grado;
        //Ponemos en el convert view el grado actual
        convertView = inflater.inflate(idLayout, parent, false);

        Grado gradoA = grados.get(position);

        //Comprobamos si el grado es un grado superior o un grado medio
        String textoGrado  = "";
        if(gradoA.isGradoSuperior())
            textoGrado = "GRADO SUPERIOR";
        else
            textoGrado = "GRADO MEDIO";

        ((TextView)convertView.findViewById(R.id.textoEsGrado)).setText(textoGrado);

        //Seteamos el titulo y la descripcion del grado
        ((TextView)convertView.findViewById(R.id.tituloGrado)).setText(gradoA.getNombre());
        ((TextView)convertView.findViewById(R.id.descripcionGrado)).setText(gradoA.getDescripcion());

        return convertView;
    }
}
