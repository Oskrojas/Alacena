package com.oskrojas.elrefri;

/**
 * Created by oskrojas on 23/5/15.
 */

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonProductoParser {


    public List<Producto> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayProductos(reader);
        } finally {
            reader.close();
        }

    }



    public List<Producto> leerArrayProductos(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Producto> productos = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            productos.add(leerProducto(reader));
        }
        reader.endArray();
        return productos;
    }

    public Producto leerProducto(JsonReader reader) throws IOException {
        // Variables locales
        String nombre = null;
        String precio = null;
        String imagen = null;

        // Iniciar objeto
        reader.beginObject();

        /*
        Lectura de cada atributo
         */
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "nombre":
                    nombre = reader.nextString();

                    break;
                case "precio":
                    precio = reader.nextString();
                    break;
                case "imagen":
                    imagen = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Producto(nombre, precio, imagen);
    }

}

