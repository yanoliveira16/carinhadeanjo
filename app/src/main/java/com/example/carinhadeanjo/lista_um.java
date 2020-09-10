package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class lista_um extends AppCompatActivity {
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView2;
    public static  String onClick;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_um);



        View aa=findViewById(R.id.add);
        if (tela_do_aluno_prof.prof.contains("prof2")==true){
         aa.setVisibility(View.VISIBLE);
            myRef.child(tela_do_aluno_prof.id_aluno).child("Agenda").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        // String value=snapshot.getValue().toString();
                        feed.add(key);
                        //feed.add(value);
                        listView2 = findViewById(R.id.listview2);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
                        gd.setCornerRadius(60.40f);
                        listView2.setBackground(gd);
                        listView2.setAdapter(arrayAdapter);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }else {
            aa.setVisibility(View.INVISIBLE);
            myRef.child(login_or_register.id).child("Agenda").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        // String value=snapshot.getValue().toString();
                        feed.add(key);
                        //feed.add(value);
                        listView2 = findViewById(R.id.listview2);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
                        gd.setCornerRadius(60.40f);
                        listView2.setBackground(gd);
                        listView2.setAdapter(arrayAdapter);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        final TextView a1 = (TextView) findViewById(R.id.aluno_agenda);
        a1.setText(tela_de_alunos.onClick3);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, feed);
        final ListView lv=(ListView)findViewById(R.id.listview2);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                onClick = (String) lv.getItemAtPosition(arg2);
                Intent intent = new Intent(getBaseContext(), lista_dois.class);
                startActivity(intent);

            }
        });



    }
    public void add_click (View view){
        Intent intent = new Intent(getBaseContext(), agenda.class);
        startActivity(intent);

    }

}



