package com.oscarbermejo.proyecto_android.viewPager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.oscarbermejo.proyecto_android.almacen.AlmacenSQLite;
import com.oscarbermejo.proyecto_android.fragment.FragmentGrados;
import com.oscarbermejo.proyecto_android.pojos.Grado;

import java.util.ArrayList;

public class ViewPagerFamilias extends FragmentPagerAdapter {
    private AlmacenSQLite almacenSQLite;
    private Context context;
    public ViewPagerFamilias(FragmentManager fm, AlmacenSQLite almacenSQLite, Context context){
        super(fm);
        this.almacenSQLite = almacenSQLite;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ArrayList<Grado> gradosO  = new ArrayList<>();

        switch (position) {
            case 0://Todos los grados
                gradosO = almacenSQLite.getGrados();
                break;
            case 1://Solo grados superiores
                gradosO = almacenSQLite.getGradosMedios();
                break;
            case 2://Solo grados superiores
                gradosO = almacenSQLite.getGradosSuperiores();
                break;
        }

        return new FragmentGrados( context, gradosO);
    }

    @Override
    public int getCount() {
        return 3;
    }


}
