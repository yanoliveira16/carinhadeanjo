package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class tela_da_professora extends AppCompatActivity {
    List<String> feed;
    List<String> feed2;
    List<String> feed3;
    ArrayAdapter<String> arrayAdapter;
    private FirebaseAuth mAuth;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");


    ListView listview_prof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_da_professora);

        feed = new ArrayList<>();
        feed2 = new ArrayList<>();
        feed3 = new ArrayList<>();

            final TextView a1 = (TextView) findViewById(R.id.turma2);
            a1.setText(tela_de_carregamento.tturma);
             final TextView a2 = (TextView) findViewById(R.id.nome_prof);
            a2.setText(tela_de_carregamento.nnomeProfe);

        new_feed();

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

    Integer id_do_button = 5000;
    public void new_feed(){
        myRef.child(tela_de_carregamento.tturma).child("feed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    if (key.contains("aln") == false){
                        feed2.add(key);
                        feed.add(value);
                    }
                }
                //Collections.reverse(feed);
                //Collections.reverse(feed2);
                /*Collections.sort(feed, new Comparator<Item>() {
                    public int compare(Item o1, Item o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });*/

                Collections.sort(feed, new Comparator<String>() {

                    @Override
                    public int compare(String arg0, String arg1) {
                        SimpleDateFormat format = new SimpleDateFormat(
                                "dd-MM-yyyy");
                        int compareResult = 0;
                        try {
                            java.util.Date arg0Date = format.parse(arg0);
                            java.util.Date arg1Date = format.parse(arg1);
                            compareResult = arg0Date.compareTo(arg1Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            compareResult = arg0.compareTo(arg1);
                        }
                        return compareResult;
                    }
                });

                Collections.sort(feed2, new Comparator<String>() {

                    @Override
                    public int compare(String arg0, String arg1) {
                        SimpleDateFormat format = new SimpleDateFormat(
                                "dd");
                        int compareResult = 0;
                        try {
                            java.util.Date arg0Date = format.parse(arg0);
                            java.util.Date arg1Date = format.parse(arg1);
                            compareResult = arg0Date.compareTo(arg1Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            compareResult = arg0.compareTo(arg1);
                        }
                        return compareResult;
                    }
                });

                adicionar_aofeed();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



        int itemCount;
    public void adicionar_aofeed(){
        itemCount = feed.size();
        Log.d("AQUI", "AA " +itemCount);
        while(itemCount != 0){
            itemCount -= 1;
            String data = feed.get(itemCount);
            String data2 = feed2.get(itemCount);
            Log.d("AQUI", "AA2 " +itemCount);
            Log.d("AQUI 2", "AA3 " +data);
            Log.d("AQUI", "AA2 " +data2);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.linear_feed);
            id_do_button += 1;
            Button btnTag = new Button(tela_da_professora.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText(data);
            btnTag.setId(id_do_button);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    tela_de_carregamento.onClick19 = data;
                    tela_de_carregamento.key_feed = data2;
                    Intent intent = new Intent(getBaseContext(), click_feed.class);
                    startActivity(intent);
                }
            });

            Drawable dr = getResources().getDrawable(R.drawable.close);
            Log.d("FEED", "" + data2 +" - " + data);
            Log.d("FEED 1", "\natv: " + data2.contains("atv"));
            Log.d("FEED 1", "agd: " + data2.contains("agd"));
            Log.d("FEED 1", "avs: " + data2.contains("avs"));
            Log.d("FEED 1", "reun: " + data2.contains("reun"));
            Log.d("FEED 1", "outr: " + data2.contains("outr"));
            Log.d("FEED 1", "pic: " + data2.contains("pic"));
            Log.d("FEED 1", "serve: " + data2.contains("serve"));
            Log.d("FEED 1", "profe: " + data2.contains("profe"));
            Log.d("FEED 1", "ALUNO: " + data.contains("ALUNO"));
            if (data2.contains("atv")){
                dr = getResources().getDrawable(R.drawable.al_um);
            }else if(data2.contains("agd")){
                dr = getResources().getDrawable(R.drawable.al_dois);
            }else if(data2.contains("avs")){
                dr = getResources().getDrawable(R.drawable.al_tres);
            }else if(data2.contains("reun")){
                dr = getResources().getDrawable(R.drawable.calendar_quatro);
            }else if(data2.contains("outr")){
                dr = getResources().getDrawable(R.drawable.al_cinco);
            }else if(data2.contains("profe")){
                if (data.contains("ALUNO")){
                    dr = getResources().getDrawable(R.drawable.alunos);
                }else{
                    dr = getResources().getDrawable(R.drawable.message);
                }
            }else if(data2.contains("pic")){
                dr = getResources().getDrawable(R.drawable.picture);
            }else if(data2.contains("serve")){
                dr = getResources().getDrawable(R.drawable.database);
            }

            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            bSearch2.addView(btnTag);

            TextView txt1 = new TextView(tela_da_professora.this);
            txt1.setText("----------------------------------------------------------------------------");
            txt1.setGravity(Gravity.CENTER | Gravity.CENTER);
            bSearch2.addView(txt1);
        }
    }


    /*public void atualizar_feed(){
        myRef.child(tela_de_carregamento.tturma).child("feed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    feed2.add(key);
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

        final ListView lv=(ListView)findViewById(R.id.listview_prof);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                tela_de_carregamento.onClick19 = (String) lv.getItemAtPosition(arg2);
                tela_de_carregamento.key_feed = feed2.get(arg2);
                Intent intent = new Intent(getBaseContext(), click_feed.class);
                startActivity(intent);

            }
        });
    }*/

    public void novo_feed(View view){
        new AlertDialog.Builder(tela_da_professora.this).setMessage("EM BREVE").show();
    }



    public void att_feed(View view){
        arrayAdapter.clear();
        feed.clear();
        new_feed();
    }




}
