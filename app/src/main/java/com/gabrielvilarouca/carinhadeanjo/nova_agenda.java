package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class nova_agenda extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_agenda);

        myRef.child(tela_de_carregamento.tturma).child("atividade").child("info").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String atv = dataSnapshot.getValue(String.class);
                final TextView a1 = (TextView) findViewById(R.id.atvs_geral);
                if (atv == null){
                    a1.setText("SEM DEVER REGISTRADO");
                }else{
                    a1.setText("- "+atv);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}