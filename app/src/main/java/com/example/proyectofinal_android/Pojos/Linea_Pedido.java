package com.example.proyectofinal_android.Pojos;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public class Linea_Pedido {

    private int linea_pedido_id;
    private int linea_pedido_producto_id;
    private int linea_pedido_cantidad;
    private float linea_precio_producto;
    private int linea_pedido_pedido_id;
    private Bitmap imagen;

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    private String nombreProducto;

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    private String linea_pedido_desc;

    public float getLinea_precio_producto() {
        return linea_precio_producto;
    }

    public String getLinea_pedido_desc() {
        return linea_pedido_desc;
    }

    public void setLinea_pedido_desc(String linea_pedido_desc) {
        this.linea_pedido_desc = linea_pedido_desc;
    }

    @NonNull
    @Override
    public String toString() {
        return "linea id = " + linea_pedido_id +
                "|| " + "producto id =" + linea_pedido_producto_id +
                "|| " + "pedido id =" + linea_pedido_pedido_id +
                "|| " + "nombre= " + nombreProducto +
                "|| " + "descripcion =" + linea_pedido_desc +
                "|| " + "cantidad = " + linea_pedido_cantidad +
                " || " + "precioTotal= " + this.getPrecioTotal();

    }

    public Linea_Pedido(int linea_pedido_id, int linea_pedido_producto_id, int linea_pedido_cantidad, float linea_precio_producto, int linea_pedido_pedido_id, String linea_pedido_desc, Bitmap imagen, String nombreProducto) {
        this.linea_pedido_id = linea_pedido_id;
        this.linea_pedido_producto_id = linea_pedido_producto_id;
        this.linea_pedido_cantidad = linea_pedido_cantidad;
        this.linea_precio_producto = linea_precio_producto;
        this.linea_pedido_pedido_id = linea_pedido_pedido_id;
        this.linea_pedido_desc = linea_pedido_desc;
        this.imagen = imagen;
        this.nombreProducto = nombreProducto;
    }

    public int getLinea_pedido_id() {
        return linea_pedido_id;
    }

    public void setLinea_pedido_id(int linea_pedido_id) {
        this.linea_pedido_id = linea_pedido_id;
    }

    public int getLinea_pedido_producto_id() {
        return linea_pedido_producto_id;
    }

    public void setLinea_precio_producto(float linea_precio_producto) {
        this.linea_precio_producto = linea_precio_producto;
    }

    public void setLinea_pedido_producto_id(int linea_pedido_producto_id) {
        this.linea_pedido_producto_id = linea_pedido_producto_id;
    }

    public int getLinea_pedido_cantidad() {
        return linea_pedido_cantidad;
    }

    public void setLinea_pedido_cantidad(int linea_pedido_cantidad) {
        this.linea_pedido_cantidad = linea_pedido_cantidad;
        precioTotal = linea_pedido_cantidad * linea_precio_producto;
    }

    public int getLinea_pedido_pedido_id() {
        return linea_pedido_pedido_id;
    }

    public void setLinea_pedido_pedido_id(int linea_pedido_pedido_id) {
        this.linea_pedido_pedido_id = linea_pedido_pedido_id;
    }

    float precioTotal;
    public float getPrecioTotal() {

        precioTotal = linea_pedido_cantidad * linea_precio_producto;
        return precioTotal;
    }

}
