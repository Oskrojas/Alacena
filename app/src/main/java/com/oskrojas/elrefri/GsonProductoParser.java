package com.oskrojas.elrefri;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por Oscar Rojas
 */
public class GsonProductoParser {


    public List<Producto> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Producto> productos = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Producto producto = gson.fromJson(reader, Producto.class);
            productos.add(producto);
        }


        reader.endArray();
        reader.close();
        return productos;
    }
}
