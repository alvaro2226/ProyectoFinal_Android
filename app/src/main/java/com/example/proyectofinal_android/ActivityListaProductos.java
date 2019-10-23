package com.example.proyectofinal_android;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.proyectofinal_android.Pojos.Producto;

import java.util.ArrayList;

public class ActivityListaProductos extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private static AdapterProductos adapter;
    private SearchView editsearch;
    public  static  ArrayList<Producto> productosAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaproductos);

        productosAux = new ArrayList<>();

        iniciarListView();
        initViews();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initViews() {


        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        //supportActionBar.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeButtonEnabled(true)



    }

    private void iniciarListView(){

        listView = findViewById(R.id.listView);
        ArrayList<Producto> productos = new ArrayList<>();
        for (int i =0 ; i < 10 ; i++){
            Producto producto = new Producto();
            producto.setNombre("Nombre " + i);
            producto.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus vitae suscipit erat. Aenean vehicula gravida orci eu venenatis.");
            productos.add(producto);
            productosAux.add(producto);
        }

        adapter = new AdapterProductos(productos,getApplicationContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("LISTVIEW ITEM CLICK", position + "");
                iniciarIntentProducto(position);
            }
        });


    }

    private void iniciarIntentProducto(int position){
        Intent intent = new Intent(this,ActivityProducto.class);
        intent.putExtra("posicionProducto",position);
        startActivity(intent);
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        Log.e("Search: ", s);
        adapter.filter(s);
        return false;
    }
}
