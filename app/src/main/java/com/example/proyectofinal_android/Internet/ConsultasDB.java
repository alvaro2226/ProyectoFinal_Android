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

    public static final String añadirLinea = "INSERT INTO `linea_pedido` (`linea_pedido_id`, `linea_pedido_producto_id`, `linea_pedido_cantidad`, `linea_pedido_pedido_id`, `linea_pedido_total`) " +
            "VALUES (NULL, ?, ?, ?, ?)";

    public static final String getPedidosUsuarioLogueado = "SELECT pedido_id, pedido_fechaCreacion FROM pedido WHERE pedido_usuario_id = ";

    public static final String getDatosUsuarioLogueado = "SELECT usuario.usuario_nombreUsuario, usuario.usuario_email,usuario.usuario_contraseña, " +
            "usuario.usuario_nombre, usuario.usuario_Apellidos, usuario.usuario_telefono, direccion.direccion_calle, " +
            "direccion.direccion_localidad, direccion.direccion_provincia, direccion.direccion_codigoPostal, direccion.direccion_pais " +
            "FROM usuario,direccion " +
            "WHERE usuario.usuario_direccion_id = direccion.direccion_id and usuario_id =";

    public static final String getDireccionEmpresa = "SELECT direccion.direccion_calle,direccion.direccion_localidad,direccion.direccion_provincia \n" +
            "FROM direccion, datos_empresa WHERE direccion.direccion_id = datos_empresa_id";

    public static String getLineasPedido = "SELECT producto.producto_nombre,producto.producto_descripcion,producto.producto_imagen,linea_pedido.linea_pedido_total " +
            "FROM producto, linea_pedido " +
            "WHERE producto.producto_id = linea_pedido.linea_pedido_producto_id AND linea_pedido_pedido_id =";

}
