package com.oskrojas.elrefri;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


public class Acerca extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String version = pinfo.versionName;
        TextView versionText = (TextView) findViewById(R.id.versionName);
        versionText.setText("Versión: " + version);


        CircleImageView btn_facebook = (CircleImageView) findViewById(R.id.img_face);

        btn_facebook.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com/oskrojas");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        CircleImageView btn_info = (CircleImageView) findViewById(R.id.img_info);

        btn_info.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.tic-srl.net/prog3-9/oscar.html#contact");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        CircleImageView btn_mail = (CircleImageView) findViewById(R.id.img_mail);

        btn_mail.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "oskrojas@icloud.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Billetes App");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(intent, "Enviar un mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Acerca.this, "No existen clientes de Email instalados.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}