package com.example.proyectofinal_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.proyectofinal_android.Adapters.AdapterCarrito;
import com.example.proyectofinal_android.Adapters.AdapterProductos;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;

public class ActivityCarrito extends AppCompatActivity {

    private ListView listView;
    private AdapterCarrito adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        iniciarListView();
    }

    private void iniciarListView() {

        listView = findViewById(R.id.listView_carrito);
        adapter = new AdapterCarrito(ActivityListaProductos.productosAux,getApplicationContext());
        listView.setAdapter(adapter);


    }
}
