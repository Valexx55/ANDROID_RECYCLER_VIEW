package com.example.vale.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;

    private ArrayList<Libro> datos;

    private AdapterLibros adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new ArrayList<Libro>();
        Libro libro_aux = null;

        for (int i = 0; i < 20; i++) {
            libro_aux = new Libro("Libro " + i, " Autor " + i);
            datos.add(libro_aux);
        }

        recView = (RecyclerView) findViewById(R.id.RecView);
        //recView.setHasFixedSize(true);//opcional, si sé que el tamaño no va a cambiar

        adaptador = new AdapterLibros(datos);


        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));
        //StaggeredGridLayoutManager para celdas de tamaño variable
        //recView.setLayoutManager(new StaggeredGridLayoutManager());


        //ITEM DECORATOR --> OPCIONAL

        recView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //
        //recView.setItemAnimator(new DefaultItemAnimator());

        registerForContextMenu(recView);

       // recView.setContextClickable(true);

    }

    public void aniadir (View v)
    {

        datos.add(1, new Libro("Nuevo", "Autor X"));
        adaptador.notifyItemInserted(1);
    }

    public void eliminar (View v)
    {
        datos.remove(1);
        adaptador.notifyItemRemoved(1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

       // MenuItem mi1 = menu.add(Menu.NONE, 1, 1, "INSERTAR");
        MenuItem mi2 = menu.add(Menu.NONE, 2, 2, "BORRAR");
        //MenuItem mi3 = menu.add(Menu.NONE, 3, 3, "MODIFICAR");
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        int position = info.position;

        Log.d("MENSAJE", "POS_TOCADA = " + position);

    }
}