package com.example.proyectofinal_android.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal_android.Internet.OperacionesDB;
import com.example.proyectofinal_android.Pojos.Pedido;
import com.example.proyectofinal_android.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ActivityPedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        initViews();
    }

    public static ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<String> fechas = new ArrayList<String>();

    private void initViews(){

        OperacionesDB thread = new OperacionesDB(this, OperacionesDB.GET_PEDIDO_USUARIO_LOGUEADO);

        thread.execute();
        try {
            thread.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < pedidos.size() ; i++){


            Timestamp timeStamp = pedidos.get(i).getFechaCreacionTimestamp();
            Date date = new Date(timeStamp.getTime());
            DateFormat dia = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat horas = new SimpleDateFormat("HH:mm");



                fechas.add("Tu pedido del día " + dia.format(date) + " a las " + horas.format(date));

                //Log.e("Pedidos", pedidos.get(i).getFechaCreacionTimestamp().toString());
        }

        ListView lstOpciones;

        String[] datos = new String[fechas.size()];
        datos = fechas.toArray(datos);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lstOpciones = (ListView)findViewById(R.id.listView_Pedidos);

        Context context = this;
        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Log.e("Pedido ", pedidos.get(i).getId() + " seleccionado");
                Intent intent = new Intent(context,ActivityDetallesPedido.class);
                intent.putExtra("idPedido", pedidos.get(i).getId());


                Timestamp timeStamp = pedidos.get(i).getFechaCreacionTimestamp();
                Date date = new Date(timeStamp.getTime());
                DateFormat dia = new SimpleDateFormat("dd-MM-yyyy");

                intent.putExtra("fechaPedido", dia.format(date));

                startActivity(intent);
            }
        });
    }

}
