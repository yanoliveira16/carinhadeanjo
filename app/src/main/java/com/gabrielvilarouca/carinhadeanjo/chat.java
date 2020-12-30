package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class chat extends AppCompatActivity {

    List<String> feed;
    List<String> feed2;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P5").child(tela_de_carregamento.tturma).child("chats");
    DatabaseReference myRef3 = database.child("P3");

    String id_do_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final TextView a18 = (TextView) findViewById(R.id.title_chat);
        a18.setText("CARREGANDO...");

        if (tela_de_carregamento.qual == "1"){
            myRef3.child(tela_do_aluno_prof.id_aluno).child("id_chat").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    id_do_chat = dataSnapshot.getValue(String.class);
                    call_the_chat();
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            id_do_chat = login_or_register.id;
            call_the_chat();
        }
    }

    public void call_the_chat(){
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

                adicionar_aofeed();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    int itemCount;
    Integer id_do_button = 5240;
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
            Button btnTag = new Button(chat.this);
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
            if (data.contains("ATIVIDADE")){
                dr = getResources().getDrawable(R.drawable.al_um);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("AGENDA")){
                dr = getResources().getDrawable(R.drawable.al_dois);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("AVISO")){
                dr = getResources().getDrawable(R.drawable.al_tres);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("REUNIÃO") || data.contains("EVENTO")){
                dr = getResources().getDrawable(R.drawable.calendar_quatro);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("IMAGEM")){
                dr = getResources().getDrawable(R.drawable.picture);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("SERVIDOR")){
                dr = getResources().getDrawable(R.drawable.database);
                btnTag.setTextColor(Color.parseColor("#E91E63"));
            }else if(data.contains("ALUNO")){
                dr = getResources().getDrawable(R.drawable.alunos);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else{
                dr = getResources().getDrawable(R.drawable.message);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }

            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            bSearch2.addView(btnTag);

            /*TextView txt1 = new TextView(tela_da_professora.this);
            txt1.setText("-------------------------------------");
           // txt1.setText("_______________________________________________________");
            txt1.setGravity(Gravity.CENTER | Gravity.CENTER);
            bSearch2.addView(txt1);*/


            ScrollView scroll = (ScrollView) findViewById(R.id.scfeed);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(15);
            scroll.setBackground(gd);
        }
    }
}