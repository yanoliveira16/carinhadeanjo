package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class agenda extends AppCompatActivity {

    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public static  String onClick4;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        final TextView a1 = (TextView) findViewById(R.id.nome_aluno_agenda);
        a1.setText(tela_de_carregamento.nnomeAluno);


        myRef.child(tela_de_carregamento.tturma).child("P2-1").child(tela_de_alunos.onClick3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }




    String p1 = "";
    public void enviar(View view){
        Switch s1 = findViewById(R.id.s1);
        Switch s2 = findViewById(R.id.s2);
        if (s1.isChecked() == true){
            p1 = "Português | ";
        }
        if (s2.isChecked() == true){
            p1 += "Literatura | ";
        }
    }
}
