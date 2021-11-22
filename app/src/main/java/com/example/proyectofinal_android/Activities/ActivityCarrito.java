package com.example.proyectofinal_android.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proyectofinal_android.Adapters.AdapterCarrito;
import com.example.proyectofinal_android.Internet.OperacionesDB;
import com.example.proyectofinal_android.Pojos.Pedido;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import org.apache.commons.net.ntp.TimeStamp;

import java.util.ArrayList;

public class ActivityCarrito extends AppCompatActivity {

    private ListView listView;
    private AdapterCarrito adapter;
    static TextView textView_precioSubtotal;
    private Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        iniciarListView();

        generarPedido();

        textView_precioSubtotal = findViewById(R.id.textView_PrecioSubtotal);
        actualizarSubtotal();
    }

    private void generarPedido(){
        pedido.setFechaCreacion(TimeStamp.getCurrentTime());
        pedido.setUsuario_id(ActivityLogin.idUsuarioLogueado);
        pedido.setCostesEnvio(0f);
        pedido.setFechaEnvioEstimada(null);
        pedido.setFechaEnvioRealizado(null);
        pedido.setEstadoPedido(2); //preparado
        pedido.setMetodoPago(3); //en la tienda
        pedido.setPagado(false);
        pedido.setId_usuarioAsignado(0); // no hay usuario asignado

    }

    public void tramitarPedido (View view){

        new OperacionesDB(this, OperacionesDB.TRAMITAR_PEDIDO).execute();

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
    public static void actualizarSubtotal(){

        precioSubtotal = 0f;
        for (int i = 0 ; i < ActivityListaProductos.lineas.size(); i++){
            precioSubtotal = precioSubtotal + ActivityListaProductos.lineas.get(i).getPrecioTotal();
        }

        textView_precioSubtotal.setText(precioSubtotal+ "€");
    }
}
