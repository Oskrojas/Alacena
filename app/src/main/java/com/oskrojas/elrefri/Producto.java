package com.oskrojas.elrefri;

/**
 * Creado por Hermosa Programación el 18/01/2015.
 */
public class Producto {
    private String nombre;
    private String precio;
    private String imagen;

    public Producto(String nombre, String precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
