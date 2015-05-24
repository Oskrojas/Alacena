package com.oskrojas.elrefri;

/**
 * Creado por Hermosa Programación el 18/01/2015.
 */
public class Categoria {
    private String nombre;
    private String imagen;

    public Categoria (String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String area) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}