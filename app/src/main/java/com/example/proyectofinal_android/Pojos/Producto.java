package com.example.proyectofinal_android.Pojos;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

/**
 * @author Álvaro Morcillo Barbero
 */
public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String rutaImagen;
    private int stock;
    private Bitmap imagen = null;


    public Producto(int id, String nombre, String descripcion, float precio, String rutaImagen, int stock, Uri imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
        this.stock = stock;
    }

    public Producto() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {

        return "-- Producto -- \n " +
                " || id: " + this.id +
                " || nombre: " + this.nombre +
                " || descripcion: " + this.descripcion +
                " || precio: " + this.precio +
                " || rutaImagen: " + this.rutaImagen +
                " || stock: " + this.stock;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
