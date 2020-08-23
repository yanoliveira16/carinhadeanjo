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
import java.util.Comparator;

public class tela_de_alunos extends AppCompatActivity {
    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView_alunos;
    public static  String onClick3;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_alunos);



        myRef.child(tela_de_carregamento.tturma).child("P2-1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    // String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    // feed.add(key);
                    feed.add(value);
                    listView_alunos = findViewById(R.id.listView_alunos);
                    GradientDrawable gd = new GradientDrawable();
                    gd.setShape(GradientDrawable.RECTANGLE);
                    gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
                    //gd.setCornerRadius(80.50f);
                    gd.setCornerRadius(150);
                    listView_alunos.setBackground(gd);
                    listView_alunos.setAdapter(arrayAdapter);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, feed);
        final ListView lv=(ListView)findViewById(R.id.listView_alunos);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                onClick3 = (String) lv.getItemAtPosition(arg2);
                Intent intent = new Intent(getBaseContext(), lista_dois.class);
                startActivity(intent);

            }
        });


    }
    class StringNumberComparator implements Comparator<String> {


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
}
