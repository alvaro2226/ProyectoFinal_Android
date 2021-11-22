package com.example.proyectofinal_android.Internet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.example.proyectofinal_android.Activities.ActivityListaProductos;
import com.example.proyectofinal_android.Activities.ActivityLogin;
import com.example.proyectofinal_android.Pojos.Producto;
import com.example.proyectofinal_android.Util.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OperacionesDB extends AsyncTask<Void, Void, String> {
    public static final int COMPROBAR_USUARIO = 1;
    public static final int AÑADIR_USUARIO = 2;
    public static final int GET_PRODUCTOS = 3;
    public static final int TRAMITAR_PEDIDO = 4;
    public static List<String> lista;
    private ProgressDialog mProgressDialog;
    private Context context;
    private int accion = 0;
    private String usuario;
    private String contra;
    ArrayList<Producto> productos = null;
    private String resultado = null;

    private String calle = null;
    private String localidad = null;
    private String provincia = null;
    private String codigoPostal = null;
    private String pais = null;


    private String nombre = null;
    private String nombreUsuario = null;
    private String contraseña = null;
    private String email = null;
    private String apellidos = null;
    private String telefono = null;
    private String dni = null;

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



    /**
     * @param context
     * @param accion
     * @param calle
     * @param localidad
     * @param provincia
     * @param codigoPostal
     * @param pais
     * @param nombre
     * @param nombreUsuario
     * @param contraseña
     * @param email
     * @param apellidos
     * @param telefono
     * @param dni
     */
    //Constructor para la accion AÑADIR_USUARIO
    public OperacionesDB(Context context, int accion, String calle, String localidad, String provincia, String codigoPostal, String pais,
                         String nombre, String nombreUsuario, String contraseña, String email, String apellidos, String telefono, String dni) {
        this.context = context;
        this.accion = accion;
        this.calle = calle;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.email = email;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
    }

    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(context, "",
                "Conectando a la base de datos...");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

                        //Guarda el id del usuario para mas tarde
                        Statement st = conexion.createStatement();
                        ResultSet rs_id = st.executeQuery("SELECT usuario_id FROM usuario WHERE usuario_nombreUsuario = '" + usuario + "'" );
                        rs.first();
                        ActivityLogin.idUsuarioLogueado = rs.getInt(1);
                        context.startActivity(new Intent(context, ActivityListaProductos.class));


                    }else{
                        Log.e("Conexion BDD" , "No existe el usuario" );
                        resultado = "Inicio de sesión incorrecto";
                    }


                    break;

                case 2:
                    //REGISTRA EL USUARIO EN LA BASE DE DATOS

                    //añade la direccion
                    PreparedStatement psDireccion = conexion.prepareStatement(ConsultasDB.añadirDireccion);

                    psDireccion.setString(1, calle);
                    psDireccion.setString(2, localidad);
                    psDireccion.setString(3, provincia);
                    psDireccion.setString(4, codigoPostal);
                    psDireccion.setString(5, pais);

                    psDireccion.execute();

                    //añade el usuario

                    /*
                    "INSERT INTO `usuario` (`usuario_id`, `usuario_nombreUsuario`, `usuario_email`, `usuario_contraseña`, `usuario_nombre`, `usuario_Apellidos`, " +
                            "`usuario_telefono`, `usuario_fechaCreacion`, `usuario_tipoUsuario_id`, `usuario_direccion_id`, `usuario_dni`) " +
                            "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT direccion_id FROM direccion WHERE calle = ?), ?);";
*/

                    PreparedStatement pst_usuario = null;

                    pst_usuario = conexion.prepareStatement(ConsultasDB.añadirUsuario);

                    pst_usuario.setString(1, nombreUsuario);
                    pst_usuario.setString(2, email);
                    pst_usuario.setString(3, contraseña);
                    pst_usuario.setString(4, nombre);
                    pst_usuario.setString(5, apellidos);
                    pst_usuario.setString(6, telefono);


                    Date date = new Date();
                    pst_usuario.setTimestamp(7,  new Timestamp(date.getTime()));

                    //usuario tipo 3 cliente
                    pst_usuario.setInt(8, 3);
                    //la calle del cliente que e va a añadir
                    pst_usuario.setString(9, calle);

                    pst_usuario.setString(10, dni);

                    pst_usuario.executeUpdate();

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
                        producto.setRutaImagen(rs_productos.getString("producto_imagen"));
                        producto.setPrecio(rs_productos.getFloat("producto_precio"));
                        producto.setStock(rs_productos.getInt("producto_stock"));

                        //DESCARGA LA IMAGEN
                        if(producto.getRutaImagen() != null){

                            //Descarga un bitmap
                            Bitmap imagen = Utils.descargarImagen(producto.getRutaImagen());
                            producto.setImagen(imagen);

                        }


                        productos.add(producto);

                        Log.e("CONSULTA PRODUCTOS" , producto.toString() );

                    }

                    ActivityListaProductos.productos = productos;



                    break;


                case TRAMITAR_PEDIDO:

                    PreparedStatement pst_pedido = null;

                    pst_pedido = conexion.prepareStatement(ConsultasDB.añadirPedido);
//"INSERT INTO `pedido`(`pedido_id`, `pedido_fechaCreacion`, `pedido_usuario_id`," +
//            " `pedido_costesEnvio`, `pedido_fechaEnvioEstimada`, `pedido_fechaEnvioRealizado`, `pedido_estadoPedido`," +
//            " `pedido_metodoPago`, `pedido_pagado`, `pedido_empleadoAsignado`) " +
//            "VALUES (null,?,?,?,null,null,?,?,?,null)";
                    Date date2 = new Date();

                    pst_pedido.setTimestamp(1,  new Timestamp(date2.getTime()));
                    pst_pedido.setInt(2,ActivityLogin.idUsuarioLogueado);
                    pst_pedido.setFloat(3,0f);

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    Date result = cal.getTime();
                    java.sql.Date sqlDate = new java.sql.Date(result.getTime());

                    pst_pedido.setDate(4,sqlDate);
                    pst_pedido.setInt(5,2); //preparado
                    pst_pedido.setInt(6,3); //paga en tienda
                    pst_pedido.setBoolean(7,false);

                    pst_pedido.executeUpdate();

                    break;
                default:

                    Log.e("Conexion BDD" , "error" );
                    resultado = "Error";
            }


            conexion.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;

    }

    protected void onPostExecute(String resultado) {


        mProgressDialog.dismiss();



    }





}