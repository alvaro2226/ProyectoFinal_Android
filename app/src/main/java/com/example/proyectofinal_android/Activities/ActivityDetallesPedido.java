package com.example.proyectofinal_android.Activities;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.proyectofinal_android.Adapters.AdapterProductos;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;

public class ActivityDetallesPedido extends AppCompatActivity {

    protected DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private static AdapterProductos adapter;
    private SearchView editsearch;
    public static ArrayList<Producto> productosAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalles_pedido);

        productosAux = new ArrayList<>();

        iniciarListView();
        //iniciarDrawer();



    }
    private void iniciarDrawer(){

         DrawerLayout drawer;
         ActionBarDrawerToggle toggle;

            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Toolbar toolbar = findViewById(R.id.toolbar_main);
            setSupportActionBar(toolbar);
            drawer = findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
            drawer.addDrawerListener(toggle);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true);

        }

/*
    protected void iniciarListView2(){
        listView = findViewById(R.id.listView_DetallesPedido);
        adapter = new AdapterProductos(ActivityListaProductos.productosAux, getApplicationContext());
        listView.setAdapter(adapter);
    }

 */

    private void iniciarListView() {

        listView = findViewById(R.id.listView_detalles_pedido);
        ArrayList<Producto> productos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();
            producto.setNombre("Nombre " + i);
            producto.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae suscipit erat. Aenean vehicula gravida orci eu venenatis.");
            productos.add(producto);
            productosAux.add(producto);
        }
        adapter = new AdapterProductos(productos, getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("LISTVIEW ITEM CLICK", position + "");
                //iniciarIntentProducto(position);
            }
        });


    }




}
