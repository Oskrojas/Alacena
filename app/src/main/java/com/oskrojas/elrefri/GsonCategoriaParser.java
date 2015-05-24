package com.oskrojas.elrefri;

/**
 * Created by oskrojas on 24/5/15.
 */

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GsonCategoriaParser {

    public List<Categoria> leerFlujoJson(InputStream in) throws IOException {
        // Nueva instancia de la clase Gson
        Gson gson = new Gson();

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Categoria> categorias = new ArrayList<>();

        // Iniciar el array
        reader.beginArray();

        while (reader.hasNext()) {
            // Lectura de objetos
            Categoria categoria = gson.fromJson(reader, Categoria.class);
            categorias.add(categoria);
        }


        reader.endArray();
        reader.close();
        return categorias;
    }
}

