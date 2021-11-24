package com.example.proyectofinal_android.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.proyectofinal_android.Adapters.AdapterProductos;
import com.example.proyectofinal_android.Internet.OperacionesDB;
import com.example.proyectofinal_android.Pojos.Linea_Pedido;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ActivityDetallesPedido extends AppCompatActivity {

    protected DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ListView listView;
    private AdapterProductos adapter;
    private SearchView editsearch;

    public static String calleEmpresa;
    public static String localidadEmpresa;
    public static String provinciaEmpresa;
    public static float precioTotal;
    public static ArrayList<Producto> productos = new ArrayList<>();

    TextView textView_idPedido;
    TextView textView_fechaPedido;
    TextView textView_calleEmpresa;
    TextView textView_localidadEmpresa;
    TextView textView_provinciaEmpresa;
    TextView textView_total;

    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalles_pedido);


        OperacionesDB db = new OperacionesDB(this, OperacionesDB.GET_DATOS_PEDIDO,getIntent().getIntExtra("idPedido",0));
        db.execute();

        try {
            db.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iniciarDrawer();

        iniciarComponentes();

        textView_idPedido.setText(String.valueOf(getIntent().getIntExtra("idPedido",0)));
        textView_fechaPedido.setText(getIntent().getStringExtra("fechaPedido"));
        textView_calleEmpresa.setText(calleEmpresa);
        textView_localidadEmpresa.setText(localidadEmpresa);
        textView_provinciaEmpresa.setText(provinciaEmpresa);
        textView_total.setText(precioTotal + " €");

        iniciarListView();
    }

    private void iniciarComponentes() {

        textView_idPedido = findViewById(R.id.textview_idPedido);
        textView_fechaPedido = findViewById(R.id.textview_fechaPedido);
         textView_calleEmpresa = findViewById(R.id.textview_calle2);
         textView_localidadEmpresa = findViewById(R.id.textview_localidad2);
         textView_provinciaEmpresa = findViewById(R.id.textview_provincia2);
         textView_total = findViewById(R.id.textview_total2);
         listView = findViewById(R.id.listView_detalles_pedido);
    }

    private void iniciarDrawer(){

         DrawerLayout drawer;
         ActionBarDrawerToggle toggle;

            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Toolbar toolbar = findViewById(R.id.toolbar_main);
            setSupportActionBar(toolbar);
            drawer = findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
            drawer.addDrawerListener(toggle);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true);

        }

    private void iniciarListView() {


        ArrayList<Producto> productosAux = new ArrayList<>();
        productosAux.addAll(productos);
        productos.clear();
        adapter = new AdapterProductos(productosAux, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("LISTVIEW ITEM CLICK", position + "");
                //iniciarIntentProducto(position);
            }
        });



    }




}
