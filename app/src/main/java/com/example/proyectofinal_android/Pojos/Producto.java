package com.example.proyectofinal_android.Pojos;

import java.io.Serializable;

/**
 * @author Álvaro Morcillo Barbero
 */
public class Producto implements Serializable {

    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String imagen;
    private int stock;


    public Producto(int id, String nombre, String descripcion, float precio, String imagen, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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
                " || rutaImagen: " + this.imagen +
                " || stock: " + this.stock;
    }

}
