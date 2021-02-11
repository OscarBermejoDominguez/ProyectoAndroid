package com.oscarbermejo.proyecto_android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textoTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        //conseguimos la orientacion de la pantalla
        int orientation = getResources().getConfiguration().orientation;
        //Si esta en horizontal la pantalla entonces ponemos una portada u otra
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            toolbar.setBackground(getResources().getDrawable(R.drawable.portada2));
        else
            toolbar.setBackground(getResources().getDrawable(R.drawable.portada));
        textoTitulo = findViewById(R.id.txtTitulo);

        //colocamos el logo de la rioja
        ((ImageView)findViewById(R.id.imagenLogo)).setImageResource(R.drawable.logo);



        toolbar.inflateMenu(R.menu.menu_principal);
        setSupportActionBar(toolbar);

        crearBotones();

        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    private void crearBotones(){
        int[] ids = {
                R.id.btnGMS,
                R.id.btnFPB,
                R.id.btnInnovacion,
                R.id.btnAcredita,
                R.id.btnNorma,
                R.id.btnPreguntas};

        //Crea los botones y les inserta una accion
        Button[] botones = new Button[ids.length];
        for (int i = 0; i < botones.length; i++){
            botones[i] = findViewById(ids[i]);
            final String titulo = botones[i].getText().toString();
            botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lanzarPantalla(titulo);
                }
            });
        }
    }

    /**
     * Crea el menu o toolbar
     * El toolbar de nuestra aplicación y lo inflamos
     * para que se muestre en nuestra pantalla principal
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Método que nos permite interactuar con el toolbar de nuestra aplicación
     * Dependiendo de que opción seleccionemos hará una cosa u otra
     *
     * @param item
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();


        switch (id){
            case 1:
                textoTitulo.setVisibility(View.INVISIBLE);
                break;
        }

//        //Pantalla que vamos a lanzar dependiendo de la opcion
//        Intent i = null;
//        //Dependiendo del item que seleccionemos la aplicación
//        // ejecutará una cosa u otra
//        switch (item.getItemId()) {
//            case R.id.menu_persona:
//                i = new Intent(this, ActivityPersona.class);
//                break;
//            case R.id.menu_formulario:
//                Toast.makeText(getApplicationContext(), "ESA ES LA ACTUAL PANTALLA", Toast.LENGTH_SHORT).show();
//        }
//        if(i != null)
//            lanzarPantalla(i);
        return true;
    }

    /**
     * Dependiendo del boton que seleccionemos lanzara una lista u otra
     * @param tituloB titulo del boton seleccionado
     */
    private void lanzarPantalla(String tituloB){
        Intent i = new Intent(this, ListaInf.class);
        i.putExtra("tipoMenu", tituloB);
        startActivity(i);
    }
}