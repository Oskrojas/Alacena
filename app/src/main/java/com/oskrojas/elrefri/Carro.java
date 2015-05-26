package com.oskrojas.elrefri;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Carro extends ActionBarActivity {

    MyDBAdapter dbAdapter;
    ListView list;

    private ArrayList<Integer> allProductos;

    private String nombre;
    private Integer precio;
    private Integer Cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton scan = (FloatingActionButton) findViewById(R.id.btn_scan_float);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Carro.this, Scanner.class);
                startActivity(intent);
            }
        });


        FloatingActionButton comprar = (FloatingActionButton) findViewById(R.id.btn_pagar_float);
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAutoDialog();
            }
        });



        list = (ListView) findViewById(R.id.lista_carro);

        dbAdapter = new MyDBAdapter(Carro.this);
        dbAdapter.open();


        loadList();
    }


    private Dialog createAutoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Agregar Producto");
        builder.setMessage("");

        final EditText name = new EditText(this);
        final EditText price = new EditText(this);
        price.setInputType(InputType.TYPE_CLASS_NUMBER);
        name.setHint("Nombre del auto");
        price.setHint("Ultimo digito de la placa");

        LinearLayout lay = new LinearLayout(this);
        lay.setOrientation(LinearLayout.VERTICAL);
        lay.addView(name);
        lay.addView(price);
        builder.setView(lay);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nombre = name.getText().toString();
                precio = Integer.valueOf(price.getText().toString());

                dbAdapter.insertProducto(name.getText().toString(),
                        Integer.valueOf(price.getText().toString()));

                Toast.makeText(getBaseContext(), "Se agregó el auto: " + nombre + "\ncon placa que termina en: " + precio, Toast.LENGTH_LONG).show();

                loadList();


                // cargarPlacas();

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

    private Dialog createEliminarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar automovil");
        builder.setMessage("Esta seguro de eliminar este auto?");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dbAdapter.deleteProducto();
                loadList();

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


    /*
    private Dialog createAutoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar compra");
        builder.setMessage("Al pulsar Confirmar Compra, usted esta aceptando el pago y envio de cada uno de los productos seleccionados en el carrito.  \nEsta seguro de realizar la compra?.");

        builder.setPositiveButton("Confirmar Compra", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                nombre = name.getText().toString();
                digito = Integer.valueOf(digit.getText().toString());

                dbAdapter.insertAuto(name.getText().toString(),
                        Integer.valueOf(digit.getText().toString()));



                Toast.makeText(getBaseContext(), "Se realizó la compra de todos tus productos", Toast.LENGTH_LONG).show();


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

*/


    public void loadList() {
        ArrayList<String> allProductos = new ArrayList<String>();
        allProductos = dbAdapter.selectAllProductos();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Carro.this, android.R.layout.simple_list_item_1,
                allProductos);
        list.setAdapter(adapter);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ayuda) {
            Intent intent = new Intent(Carro.this, Ayuda.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_eliminar) {
            Intent intent = new Intent(Carro.this, Ayuda.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_atras) {
            finish();
            System.runFinalization();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
