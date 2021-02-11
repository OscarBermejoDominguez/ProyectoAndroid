package com.oscarbermejo.proyecto_android.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.oscarbermejo.proyecto_android.R;
import com.oscarbermejo.proyecto_android.pojos.Opcion;

import java.util.ArrayList;

class AdaptadorListaSubOpciones extends BaseAdapter {

    private Context context;
    private ArrayList<Opcion> opcionesE;

    private static LayoutInflater inflater;

    public AdaptadorListaSubOpciones(Context context, ArrayList<Opcion> opcionesE){
        this.context = context;
        this.opcionesE = opcionesE;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String texto = opcionesE.get(position).getTitulo();

        if(convertView == null){
            inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subopcion_lista, parent, false);
        }

        //Buscamos dentro del layout el view  correspondiente de cada uno de
        //los elementos de la lista, como son la imagen el titulo y el subtitulo
        if(texto != null)
            ((TextView)convertView.findViewById(R.id.txtSubopcion)).setText(texto);

        return convertView;
    }

    @Override
    public int getCount() {
        return opcionesE.size();
    }

    @Override
    public Object getItem(int position) {
        return opcionesE.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
