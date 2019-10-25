package com.example.proyectofinal_android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.proyectofinal_android.Adapters.AdapterProductos;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;

public class ActivityListaProductos extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private static AdapterProductos adapter;
    private SearchView editsearch;
    public static ArrayList<Producto> productosAux;
    private boolean activityActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaproductos);

        productosAux = new ArrayList<>();

        setNavigationViewListener();
        iniciarListView();
        initViews();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initViews() {

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        drawer.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        editsearch.clearFocus();


    }

    private void iniciarListView() {

        listView = findViewById(R.id.listView);
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
                iniciarIntentProducto(position);
            }
        });


    }

    private void iniciarIntentProducto(int position) {
        Intent intent = new Intent(this, ActivityProducto.class);
        intent.putExtra("posicionProducto", position);
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

        Log.e("Menu","BotonCarrito");
        startActivity(new Intent(this, ActivityCarrito.class));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Log.e("Menu","Drawer");

        switch (menuItem.getItemId()) {

            case R.id.menu_verCuenta: {

                Log.e("Menu","Ver cuenta");
                startActivity(new Intent(this, ActivityRegistro.class));

                break;
            }

            case R.id.menu_verPedidos: {
                Log.e("Menu","Ver pedidos");

                    startActivity(new Intent(this, ActivityPedidos.class));
                break;
            }

        }
        //close navigation drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        activityActual = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        activityActual = false;
    }
}
