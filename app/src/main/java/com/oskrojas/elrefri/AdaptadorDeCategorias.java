package com.oskrojas.elrefri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by oskrojas on 24/5/15.
 */
public class AdaptadorDeCategorias extends ArrayAdapter<Categoria> {

    public AdaptadorDeCategorias(Context context, List<Categoria> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View v = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo
            v = inflater.inflate(
                    R.layout.lista_item_categoria,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView nombreCategoria = (TextView)v.findViewById(R.id.txt_categoria);
        ImageView imagenCategoria = (ImageView)v.findViewById(R.id.imagenCategoria);


        //Obteniendo instancia de la Tarea en la posición actual
        Categoria item = getItem(position);

        nombreCategoria.setText(item.getNombre());
     //   imagenCategoria.setImageResource(convertirRutaEnId(item.getImagen()));

        //Devolver al ListView la fila creada
        return v;

    }

    /**
     * Este método nos permite obtener el Id de un drawable a través
     * de su nombre
     * @param nombre  Nombre del drawable sin la extensión de la imagen
     *
     * @return Retorna un tipo int que representa el Id del recurso
     */
    private int convertirRutaEnId(String nombre){
        Context context = getContext();
        return context.getResources()
                .getIdentifier(nombre, "drawable", context.getPackageName());
    }
}
