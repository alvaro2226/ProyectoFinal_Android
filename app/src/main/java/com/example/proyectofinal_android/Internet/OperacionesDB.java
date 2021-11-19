package com.example.proyectofinal_android.Internet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.proyectofinal_android.Activities.ActivityListaProductos;
import com.example.proyectofinal_android.Adapters.AdapterProductos;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.Util.Utils;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperacionesDB extends AsyncTask<Void, Void, String> {
    public static final int COMPROBAR_USUARIO = 1;
    public static final int REGISTRAR_USUARIO = 2;
    public static final int GET_PRODUCTOS = 3;
    public static final int COMPROBAR_USUARIO4 = 4;
    public static List<String> lista;
    private ProgressDialog mProgressDialog;
    private Context context;
    private int accion = 0;
    private String usuario;
    private String contra;
    ArrayList<Producto> productos = null;
    private String resultado = null;
    private AdapterProductos adapter;

    public OperacionesDB(Context context, int accion) {
        this.context = context;
        this.accion = accion;
    }

    //Constructor para la accion COMPROBAR_USUARIO
    public OperacionesDB(Context context, int accion, String usuario, String contra) {
        this.context = context;
        this.accion = accion;
        this.usuario = usuario;
        this.contra = contra;
    }

    //Constructor para la accion GET_PRODUCTOS
    public OperacionesDB(Context context, int accion, ArrayList<Producto> productos) {
        this.context = context;
        this.accion = accion;
        this.productos = productos;
    }

    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Conectando a la base de datos...");
    }

    protected String doInBackground(Void... params) {
        try {

            lista = new ArrayList<String>();
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conexion = DriverManager.getConnection(Utils.url_DB, Utils.usuario_DB, Utils.contra_DB);
            Log.e("Conexion BDD" , Utils.url_DB + "\n Usuario " + Utils.usuario_DB + "\n Contraseña" +  Utils.contra_DB );
            switch (accion) {

                case 1:
                    //ACTIVITY LOGIN
                    //COMPRUEBA QUE EL USUARIO EXISTE EN LA BASE DE DATOS, SI EXISTE INICIA SESIÓN
                    Log.e("Conexion BDD" , "COMPROBAR_USUARIO \n Usuario:" + usuario + "\n Contraseña:" + contra );
                    PreparedStatement pst = conexion.prepareStatement(ConsultasDB.iniciarSesion);
                    pst.setString(1, contra);
                    pst.setString(2, usuario);
                    ResultSet rs = pst.executeQuery();

                    if (rs.first()) {
                        //EL USUARIO EXISTE
                        Log.e("Conexion BDD" , "El usuario existe" );
                        resultado = "Complete";
                        context.startActivity(new Intent(context, ActivityListaProductos.class));

                    }else{
                        Log.e("Conexion BDD" , "No existe el usuario" );
                        resultado = "Inicio de sesión incorrecto";
                    }


                    break;

                case 2:
                    //REGISTRA EL USUARIO EN LA BASE DE DATOS



                    break;

                case 3:
                    //HACE UNA CONSULTA DE LOS PRODUCTOS

                    Log.e("Conexion BDD" , "CONSULTA PRODUCTOS");

                    ResultSet rs_productos = null;

                    Statement st;
                    st = conexion.createStatement();

                    rs_productos = st.executeQuery(ConsultasDB.getProductos);

                    while(rs_productos.next()){

                        Producto producto = new Producto();

                        producto.setId(rs_productos.getInt("producto_id"));
                        producto.setNombre(rs_productos.getString("producto_nombre"));
                        producto.setDescripcion(rs_productos.getString("producto_descripcion"));
                        producto.setImagen(rs_productos.getString("producto_imagen"));
                        producto.setPrecio(rs_productos.getFloat("producto_precio"));
                        producto.setStock(rs_productos.getInt("producto_stock"));

                        productos.add(producto);

                        Log.e("CONSULTA PRODUCTOS" , producto.toString() );

                    }

                    ActivityListaProductos.productos = productos;



                    break;


                default:
                    Log.e("Conexion BDD" , "Resultado = default" );
                    resultado = "Error";
            }


            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultado;

    }

    protected void onPostExecute(String resultado) {


        mProgressDialog.dismiss();



    }





}