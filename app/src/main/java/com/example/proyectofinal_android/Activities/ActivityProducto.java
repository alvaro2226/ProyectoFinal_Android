package com.example.proyectofinal_android.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.security.ProtectionDomain;

public class ActivityProducto extends AppCompatActivity {


    TextView producto_nombre;
    TextView producto_desc;
    TextView producto_precio;
    TextView producto_stock;
    Button botonAñadirCarro;
    ImageView imagen;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        intent = getIntent();

        producto_nombre = findViewById(R.id.textView_productoNombre);
        producto_desc = findViewById(R.id.textView_productoDescripcion);
        producto_precio = findViewById(R.id.textView_productoPrecio);
        producto_stock = findViewById(R.id.textView_productoStock);
        botonAñadirCarro = findViewById(R.id.botonAñadirProductoCarro);
        imagen = findViewById(R.id.imageView_productoImagen);


        producto_nombre.setText(intent.getStringExtra("producto_nombre"));
        producto_desc.setText(intent.getStringExtra("producto_desc"));
        producto_precio.setText(intent.getFloatExtra("producto_precio", 0) + " €");

        if(intent.getIntExtra("producto_stock", 0) > 0){
            producto_stock.setText("Disponible");
            botonAñadirCarro.setEnabled(true);
        }else{
            producto_stock.setText("No disponible");
            botonAñadirCarro.setEnabled(false);
        }

        if (ActivityListaProductos.imagenProductoSeleccionado !=null){
            imagen.setImageBitmap(ActivityListaProductos.imagenProductoSeleccionado);
        }


    }

    public void iniciarIntentAtras(View v) {
        startActivity(new Intent(this, ActivityListaProductos.class));
    }
}
