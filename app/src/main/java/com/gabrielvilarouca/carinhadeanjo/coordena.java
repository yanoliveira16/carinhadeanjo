package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class coordena extends AppCompatActivity {

    List<String> feed;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");
    DatabaseReference myRef3 = database.child("P4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordena);

        feed = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                   // String value=snapshot.getValue().toString();
                    if (key != null){
                        feed.add(key);
                    }
                }

                adicionar_aofeed();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    int itemCount;
    Integer id_do_button = 8371;
    public void adicionar_aofeed(){
        itemCount = feed.size();
        while(itemCount != 0){
            itemCount -= 1;
            String data = feed.get(itemCount);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.ln_coordena);
            id_do_button += 1;
            Button btnTag = new Button(coordena.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText(data);
            btnTag.setId(id_do_button);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    tela_de_carregamento.qual = "1";
                    tela_de_carregamento.tturma = data;
                    Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
                    startActivity(intent);
                }
            });

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);
            bSearch2.addView(btnTag);
            ScrollView scroll = (ScrollView) findViewById(R.id.sc_coordena);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(40);
            scroll.setBackground(gd);
        }
    }

    public void sair_click2(View view) {
        sairDaqui();
    }

    @Override
    public void onBackPressed(){
        sairDaqui();
    }

    public void sairDaqui(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(coordena.this);
        builder1.setMessage("TEM CERTEZA QUE DESEJA SAIR?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "SAIR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getBaseContext(), login_or_register.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "CANCELAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void add_alunos(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gabrielvilarouca.com/adm_carinhadeanjo"));
        startActivity(browserIntent);
    }

}