package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tela_da_professora extends AppCompatActivity {
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private FirebaseAuth mAuth;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");


    ListView listview_prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_da_professora);



            final TextView a1 = (TextView) findViewById(R.id.turma2);
            a1.setText(tela_de_carregamento.tturma);
             final TextView a2 = (TextView) findViewById(R.id.nome_prof);
            a2.setText(tela_de_carregamento.nnomeProfe);

            myRef.child(tela_de_carregamento.tturma).child("feed").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        // String key= snapshot.getKey();
                        String value=snapshot.getValue().toString();
                        // feed.add(key);
                        feed.add(value);
                        listview_prof = findViewById(R.id.listview_prof);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
                        //gd.setCornerRadius(80.50f);
                        gd.setCornerRadius(100);
                        listview_prof.setBackground(gd);
                        listview_prof.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, feed);
        new AlertDialog.Builder(tela_da_professora.this).setMessage("BETA\nO aplicativo ainda se encontra em desenvolvimento.\nConfira novidades e tutorais em http://escolacarinhadeanjodf.com/aplicativo").show();
    }

    public void sair_click2(View view) {
        sairDaqui();
    }

    @Override
    public void onBackPressed(){
        sairDaqui();
    }

    public void sairDaqui(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(tela_da_professora.this);
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


    public void tela_de_alunosClick (View view) {
        Intent intent = new Intent(getBaseContext(), tela_de_alunos.class);
        startActivity(intent);
    }
        public void agenda_turma (View view){
            Intent intent = new Intent(getBaseContext(), agenda_turma.class);
            //Intent intent = new Intent(getBaseContext(), enviar_foto.class);
            startActivity(intent);
    }
}
