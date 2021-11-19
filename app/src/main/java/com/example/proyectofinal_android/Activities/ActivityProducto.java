package com.example.proyectofinal_android.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

public class ActivityProducto extends AppCompatActivity {


    TextView producto_nombre;
    TextView producto_desc;
    TextView producto_precio;
    TextView producto_stock;
    Button botonAñadirCarro;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        producto = (Producto) getIntent().getSerializableExtra("producto");

        producto_nombre = findViewById(R.id.textView_productoNombre);
        producto_desc = findViewById(R.id.textView_productoDescripcion);
        producto_precio = findViewById(R.id.textView_productoPrecio);
        producto_stock = findViewById(R.id.textView_productoStock);
        botonAñadirCarro = findViewById(R.id.botonAñadirProductoCarro);

        producto_nombre.setText(producto.getNombre());
        producto_desc.setText(producto.getDescripcion());
        producto_precio.setText(producto.getPrecio() + "€");

        if(producto.getStock() > 0){
            producto_stock.setText("Disponible");
            botonAñadirCarro.setEnabled(true);
        }else{
            producto_stock.setText("No disponible");
            botonAñadirCarro.setEnabled(false);
        }

    }

    public void iniciarIntentAtras(View v) {
        startActivity(new Intent(this, ActivityListaProductos.class));
    }
}
