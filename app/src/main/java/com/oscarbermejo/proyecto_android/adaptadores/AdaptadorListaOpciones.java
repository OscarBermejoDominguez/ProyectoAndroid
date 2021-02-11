package com.oscarbermejo.proyecto_android.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oscarbermejo.proyecto_android.R;
import com.oscarbermejo.proyecto_android.pojos.Opcion;

import java.util.ArrayList;

public class AdaptadorListaOpciones extends BaseAdapter {

    private Context context;
    private ArrayList<Opcion> opciones;

    private static LayoutInflater inflater;

    public AdaptadorListaOpciones(Context context, ArrayList<Opcion> opciones){
        this.context = context;
        this.opciones = opciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String texto = opciones.get(position).getTitulo();
        Log.i("APLICACION", texto);
        int idIcono = opciones.get(position).getIcono();
        ArrayList<Opcion> subopciones = opciones.get(position).getopcionesE();

        int idLayout = 0;

        if(subopciones != null) //Comprobamos si hay lista de subopciones
            idLayout = R.layout.opcion_lista;
        else
            idLayout = R.layout.opcion_sin_lista;

        if(convertView == null){
            inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(idLayout, parent, false);
        }

        if(subopciones != null){
            ListView lista = convertView.findViewById(R.id.listSubopciones);
            AdaptadorListaSubOpciones adaptador = new AdaptadorListaSubOpciones(context, subopciones);
            lista.setAdapter(adaptador);
        }


        //Buscamos dentro del layout el view  correspondiente de cada uno de
        //los elementos de la lista, como son la imagen el titulo y el subtitulo
        ((TextView)convertView.findViewById(R.id.txtOpcion)).setText(texto);

        if(idIcono == 0)
            idIcono = R.drawable.tarta;

        ((ImageView)convertView.findViewById(R.id.imgOpcion)).setImageDrawable(
                convertView.getResources().getDrawable(idIcono));

        return convertView;
    }

    @Override
    public int getCount() {
        return opciones.size();
    }

    @Override
    public Object getItem(int position) {
        return opciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
