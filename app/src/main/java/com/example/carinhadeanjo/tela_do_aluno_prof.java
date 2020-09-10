package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class tela_do_aluno_prof extends AppCompatActivity {

    public static String prof;
    public static String id_aluno;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_do_aluno_prof);

        myRef.child(tela_de_carregamento.tturma).child("P2-1").child(tela_de_alunos.onClick3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id_aluno = dataSnapshot.getValue(String.class);
                TextView a1 = (TextView) findViewById(R.id.turma3);
                a1.setText(tela_de_alunos.onClick3);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        }



    public void agenda_click (View view){
        prof = "prof2";
        Intent intent = new Intent(getBaseContext(), lista_um.class);
        startActivity(intent);

    }



}