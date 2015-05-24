package com.oskrojas.elrefri;

/**
 * Created by oskrojas on 24/5/15.
 */


import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonCategoriaParser {

    public List<Categoria> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return leerArrayCategorias(reader);
        } finally {
            reader.close();
        }

    }



    public List<Categoria> leerArrayCategorias(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList<Categoria> categorias = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            categorias.add(leerCategoria(reader));
        }
        reader.endArray();
        return categorias;
    }

    public Categoria leerCategoria(JsonReader reader) throws IOException {
        // Variables locales
        String nombre = null;
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

                case "imagen":
                    imagen = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Categoria(nombre, imagen);
    }

}


