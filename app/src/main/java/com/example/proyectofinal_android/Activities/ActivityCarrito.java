package com.example.proyectofinal_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.proyectofinal_android.Adapters.AdapterCarrito;
import com.example.proyectofinal_android.R;

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
        adapter = new AdapterCarrito(ActivityListaProductos.productos,getApplicationContext());
        listView.setAdapter(adapter);


    }
}
