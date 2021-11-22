package com.example.proyectofinal_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectofinal_android.Adapters.AdapterCarrito;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;

public class ActivityCarrito extends AppCompatActivity {

    private ListView listView;
    private AdapterCarrito adapter;
    static TextView textView_precioSubtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        iniciarListView();

        textView_precioSubtotal = findViewById(R.id.textView_PrecioSubtotal);
        actualizarSubtotal(INICIO);
    }

    private void iniciarListView() {

        listView = findViewById(R.id.listView_carrito);

        adapter = new AdapterCarrito(  ActivityListaProductos.lineas,getApplicationContext(),textView_precioSubtotal);
        listView.setAdapter(adapter);


    }



    private static float precioSubtotal = 0f;
    public static final int MENOS = 2;
    public static final int MAS = 3;
    public static final int ELIMINAR = 4;
    public static final int INICIO = 1;
    public static void actualizarSubtotal(int accion){

        switch(accion){
            case MENOS:

                for (int i = 0 ; i < ActivityListaProductos.lineas.size(); i++){
                    precioSubtotal = precioSubtotal - ActivityListaProductos.lineas.get(i).getLinea_precio_producto();
                }
                break;
            case MAS:

                for (int i = 0 ; i < ActivityListaProductos.lineas.size(); i++){
                    precioSubtotal = precioSubtotal + ActivityListaProductos.lineas.get(i).getLinea_precio_producto();
                }
                break;
            case ELIMINAR:

                precioSubtotal = 0f;

                break;

            case INICIO:
                for (int i = 0 ; i < ActivityListaProductos.lineas.size(); i++){
                    precioSubtotal = precioSubtotal + ActivityListaProductos.lineas.get(i).getPrecioTotal();
                }
                break;
        }


        textView_precioSubtotal.setText(precioSubtotal+ "");
    }
}
