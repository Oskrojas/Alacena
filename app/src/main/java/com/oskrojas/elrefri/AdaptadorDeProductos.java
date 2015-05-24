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
 * Creado por Hermosa Programación.
 */
public class AdaptadorDeProductos extends ArrayAdapter<Producto> {

    public AdaptadorDeProductos(Context context, List<Producto> objects) {
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
                    R.layout.grid_item_layout,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView nombreProducto = (TextView)v.findViewById(R.id.txt_nombre_producto);
        TextView precioProducto = (TextView)v.findViewById(R.id.txt_precio);
        ImageView imagenProducto = (ImageView)v.findViewById(R.id.image);


        //Obteniendo instancia de la Tarea en la posición actual
        Producto item = getItem(position);

        nombreProducto.setText(item.getNombre());
        precioProducto.setText(item.getPrecio());
        imagenProducto.setImageResource(convertirRutaEnId(item.getImagen()));

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