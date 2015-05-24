package com.oskrojas.elrefri;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;


public class MenuUsuario extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        // Crear adView.
        /*
        adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-6679026257254412/6255225289");
        adView.setAdSize(AdSize.BANNER);
*/
        //JSON
        MyPreferences pref = new MyPreferences(MenuUsuario.this);

        if (pref.isFirstTime()) {
            Toast.makeText(MenuUsuario.this, "Hola " + pref.getUserName(),
                    Toast.LENGTH_LONG).show();
            pref.setOld(true);
        } else
            Toast.makeText(MenuUsuario.this,
                    "Bienvenido de nuevo " + pref.getUserName(), Toast.LENGTH_LONG)
                    .show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton carro = (FloatingActionButton) findViewById(R.id.btn_carro);
        carro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuario.this, Carro.class);
                startActivity(intent);
            }
        });

        CircleImageView tienda = (CircleImageView) findViewById(R.id.img_tienda);
        tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuario.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        CircleImageView pedidos = (CircleImageView) findViewById(R.id.img_pedidos);
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuario.this, Pedidos.class);
                startActivity(intent);
            }
        });

        CircleImageView cliente = (CircleImageView) findViewById(R.id.img_user);
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUsuario.this, Cliente.class);
                startActivity(intent);
            }
        });




/*

        Cursor fila2 = bd.rawQuery("select estado from Estado where id='"+1+"'", null);
        if (fila2.moveToFirst()) {
            String est=fila2.getString(0);
            if(est.equals("Jugando")){
                //Toast.makeText(context, "si",Toast.LENGTH_SHORT).show();
                carro.setEnabled(false);
                String winner="";
                Cursor fila3 = bd.rawQuery("select ganador from ganadores where id='"+1+"'", null);
                if (fila3.moveToFirst()) {
                    String nombre=fila3.getString(0);
                    if(!nombre.equals("")){
                        Cursor recorrido = bd.rawQuery("select ganador from ganadores", null);
                        while(recorrido.moveToNext()){
                            winner=recorrido.getString(0);
                        }
                    }
                }
                estado.setText(winner+", se ganó "+saldo_general(cab)+" Bs.");
                listar(cab);listar_ganadores();
                if(num("ganadores","ganador")==num("jugadores","nombre,saldo")){
                    sortear.setText("Terminar");
                }
            }else{
                //Toast.makeText(context, "no",Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Partida Terminada.", Toast.LENGTH_SHORT).show();
                carro.setEnabled(true);
                listar(cab);listar_ganadores();
            }
        }else{
            //Toast.makeText(context, "no",Toast.LENGTH_SHORT).show();
            carro.setEnabled(true);
        }
*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement

        int id = item.getItemId();


        if (id == R.id.action_acerca) {
            Intent intent = new Intent(MenuUsuario.this, Acerca.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_ayuda) {
            Intent intent = new Intent(MenuUsuario.this, Ayuda.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_salir) {
            finish();
            System.runFinalization();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
