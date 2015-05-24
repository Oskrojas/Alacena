package com.oskrojas.elrefri;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Tienda extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    ListView lista;
    ArrayAdapter adaptador;
    HttpURLConnection con;




    MyDBAdapter dbAdapter;
    ListView list;


    private ArrayList<Integer> allProductos;

    private String nombre;
    private Integer precio;
    private Integer cantidad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.activity_tienda, container, false);
        setUpViews();

        Conexion();


        return parentView;




    }

    public void Conexion(){
         /*
        Comprobar la disponibilidad de la Red
         */
        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                new JsonTask().
                        execute(
                                new URL("http://sigp.herokuapp.com/api/Productos/?format=json"));
            } else {
                Toast.makeText(parentView.getContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void setUpViews() {
        MenuActivity parentActivity = (MenuActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();


        // add gesture operation's ignored views
        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.carro_view);
        resideMenu.addIgnoredView(ignored_view);

        gridView = (GridView) parentView.findViewById(R.id.gridView);


        ////Gridview con Touch en cada item y desplegando un AlertDialog
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ejemplo();
                createAutoDialog();
            }
        });


        //SQLITE
        dbAdapter = new MyDBAdapter(parentView.getContext());
        dbAdapter.open();
    }


    private Dialog createAutoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(parentView.getContext());

        builder.setIcon(R.drawable.icon_calendar);

        builder.setTitle("Desea agregarlo?");
        builder.setMessage("Seleccione la cantidad que desea adquirir (en unidades) y luego si está seguro de agregar este producto al carrito, presione Agregar.");

        final NumberPicker np = new NumberPicker(parentView.getContext());
        np.setMaxValue(20);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
        builder.setView(np);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //SQLITE
           //     nombre = nombre.getText().toString();
           //     precio = Integer.valueOf(precio.getText().toString());
                cantidad = Integer.valueOf(np.getValue());

                dbAdapter.insertProducto(name.getText().toString(),
                        Integer.valueOf(price.getText().toString()),
                        Integer.valueOf(np.getValue()));


                Toast.makeText(parentView.getContext(), "Se realizó la compra de todos tus productos", Toast.LENGTH_LONG).show();


                dialog.dismiss();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.show();
    }




    public class JsonTask extends AsyncTask<URL, Void, List<Producto>>{

        @Override
        protected List<Producto> doInBackground(URL... urls) {
            List<Producto> productos = null;

            try {

                // Establecer la conexión
                con = (HttpURLConnection)urls[0].openConnection();
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);

                // Obtener el estado del recurso
                int statusCode = con.getResponseCode();

                if(statusCode!=200) {
                    productos = new ArrayList<>();
                    productos.add(new Producto("Error",null,null));

                } else {

                    // Parsear el flujo con formato JSON
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    // JsonAnimalParser parser = new JsonAnimalParser();
                    GsonProductoParser parser = new GsonProductoParser();

                    productos = parser.leerFlujoJson(in);


                }

            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                con.disconnect();
            }
            return productos;
        }

        @Override
        protected void onPostExecute(List<Producto> productos) {
            /*
            Asignar los objetos de Json parseados al adaptador
             */
            if(productos!=null) {
                adaptador = new AdaptadorDeProductos(parentView.getContext(), productos);
                gridView.setAdapter(adaptador);
            }else{
                Toast.makeText(
                        parentView.getContext(),
                        "Ocurrió un error de Parsing Json",
                        Toast.LENGTH_SHORT)
                        .show();
            }


        }
    }

}