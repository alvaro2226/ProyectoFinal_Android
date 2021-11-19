package com.example.proyectofinal_android.Internet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasDB {


    public static final String iniciarSesion = "SELECT * FROM usuario WHERE usuario_contraseña=? AND usuario_nombreUsuario=?";
    public static final String getProductos =  "SELECT producto_id ,producto_nombre,"
            + "producto_descripcion ,"
            + "producto_precio ,"
            + "producto_imagen, "
            + "producto_stock FROM producto";

}
