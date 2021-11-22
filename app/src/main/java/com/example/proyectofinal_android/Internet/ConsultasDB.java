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

    public static final String añadirPedido = "INSERT INTO `pedido`(`pedido_id`, `pedido_fechaCreacion`, `pedido_usuario_id`," +
            " `pedido_costesEnvio`, `pedido_fechaEnvioEstimada`, `pedido_fechaEnvioRealizado`, `pedido_estadoPedido`," +
            " `pedido_metodoPago`, `pedido_pagado`, `pedido_empleadoAsignado`) " +
            "VALUES (null,?,?,?,?,null,?,?,?,null)";
}
