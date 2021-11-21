package com.example.proyectofinal_android.Internet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasDB {


    public static final String iniciarSesion = "SELECT * FROM usuario WHERE usuario_contraseña=? AND usuario_nombreUsuario=?";
    public static final String getProductos = "SELECT producto_id ,producto_nombre,"
            + "producto_descripcion ,"
            + "producto_precio ,"
            + "producto_imagen, "
            + "producto_stock FROM producto";

    public static final String añadirUsuario = "INSERT INTO `usuario` (`usuario_id`, `usuario_nombreUsuario`, `usuario_email`, `usuario_contraseña`, `usuario_nombre`, `usuario_Apellidos`," +
            "                            `usuario_telefono`, `usuario_fechaCreacion`, `usuario_tipoUsuario_id`, `usuario_direccion_id`, `usuario_dni`) " +
            "                            VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT direccion_id FROM direccion WHERE direccion_calle = ?), ?);";

    public static final String añadirDireccion = "INSERT INTO direccion VALUES (null,?, ?, ?, ?, ?);";

    /*
    INSERT INTO direccion VALUES (null,"cuenca 5", "tobarra", "albacete", "02500", "españa");
INSERT INTO usuario VALUES (null,"alvaro2226","ema@ema.com","contraseña","alvaro","morcillo barbero","654434",TimeStamp()	,3,SELECT direccion_id FROM direccion WHERE calle = cuenca 5,"774534353");

     */
}
