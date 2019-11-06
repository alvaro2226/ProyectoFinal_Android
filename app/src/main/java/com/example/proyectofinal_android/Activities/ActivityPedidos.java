package com.example.proyectofinal_android.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.proyectofinal_android.R;

public class ActivityPedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        initViews();
    }

    private void initViews(){
        ListView lstOpciones;
        final String[] datos =
                new String[]{"Pedido 1","Pedido 2","Pedido 3","Pedido 4","Pedido 5"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, datos);

        lstOpciones = (ListView)findViewById(R.id.listView_Pedidos);

        lstOpciones.setAdapter(adaptador);
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent();
            }
        });
    }

    private void intent(){
        startActivity(new Intent(this,ActivityDetallesPedido.class));
    }
}
