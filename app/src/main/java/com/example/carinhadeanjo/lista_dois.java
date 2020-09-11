package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lista_dois extends AppCompatActivity {
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public static  String onClick2;
    ListView listView3;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3");

    public static Bitmap my_image2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dois);

        final TextView a1 = (TextView) findViewById(R.id.aluno_agenda2);

        View aa=findViewById(R.id.add1);
        if (tela_de_carregamento.qual.contains("1")==true){
            a1.setText(tela_de_alunos.onClick3);
            my_image2 = tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400);
            ImageView myImage = (ImageView) findViewById(R.id.imageView23);
            myImage.setImageBitmap(my_image2);
            aa.setVisibility(View.VISIBLE);
            myRef.child(tela_do_aluno_prof.id_aluno).child("Agenda").child(lista_um.onClick).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        // String value=snapshot.getValue().toString();
                        feed.add(key);
                        //feed.add(value);
                        listView3 = findViewById(R.id.listView3);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
                        gd.setCornerRadius(60.40f);
                        listView3.setBackground(gd);
                        listView3.setAdapter(arrayAdapter);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }else {
            a1.setText(tela_de_carregamento.nnomeAluno);
            my_image2 = tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno.my_image3,400);
            ImageView myImage = (ImageView) findViewById(R.id.imageView23);
            myImage.setImageBitmap(my_image2);
            aa.setVisibility(View.INVISIBLE);
            myRef.child(login_or_register.id).child("Agenda").child(lista_um.onClick).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        // String value=snapshot.getValue().toString();
                        feed.add(key);
                        //feed.add(value);
                        listView3 = findViewById(R.id.listView3);
                        GradientDrawable gd = new GradientDrawable();
                        gd.setShape(GradientDrawable.RECTANGLE);
                        gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
                        gd.setCornerRadius(60.40f);
                        listView3.setBackground(gd);
                        listView3.setAdapter(arrayAdapter);
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }



        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, feed);
        final ListView lv=(ListView)findViewById(R.id.listView3);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                onClick2 = (String) lv.getItemAtPosition(arg2);
                Intent intent = new Intent(getBaseContext(), lista_tres.class);
                startActivity(intent);

            }
        });


    }
    class StringNumberComparator implements Comparator<String>{


        public int compare(String strNumber1, String strNumber2) {

            //convert String to int first
            int number1 = Integer.parseInt( strNumber1 );
            int number2 = Integer.parseInt( strNumber2 );

            //compare numbers
            if( number1 > number2 ){
                return 1;
            }else if( number1 < number2 ){
                return -1;
            }else{
                return 0;
            }
        }

    }
    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // Convert ArrayList to object array
        Object[] objArr = arr.toArray();

        // Iterating and converting to String
        int i = 0;
        for (Object obj : objArr) {
            str[i++] = (String)obj;
        }

        return str;
    }

    public void add_click_dois (View view){
        Intent intent = new Intent(getBaseContext(), agenda.class);
        startActivity(intent);

    }
}
