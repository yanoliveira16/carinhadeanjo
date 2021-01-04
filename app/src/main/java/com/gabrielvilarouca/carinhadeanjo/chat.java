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
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class chat extends AppCompatActivity {

    List<String> feed;
    List<String> feed2;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRefchat = database.child("P5");

    String id_do_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        feed = new ArrayList<>();
        feed2 = new ArrayList<>();

        final TextView a18 = (TextView) findViewById(R.id.title_chat);
        a18.setText("CARREGANDO..");
        if (tela_de_carregamento.qual == "1"){
            id_do_chat = tela_do_aluno_prof.id_aluno;
            call_the_chat();
        }else{
            id_do_chat = login_or_register.id;
            call_the_chat();
        }

    }

    public void call_the_chat(){
        myRefchat.child("chat").child(id_do_chat).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    if (key != null){
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

                Collections.sort(feed2, new Comparator<String>() {

                    @Override
                    public int compare(String arg0, String arg1) {
                        SimpleDateFormat format = new SimpleDateFormat(
                                "dd-MM-yyyy HH:mm:ss");
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
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.ll_chat);
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
                    //
                }
            });

            Drawable dr = getResources().getDrawable(R.drawable.close);
            if (data.contains("p")){
                dr = getResources().getDrawable(R.drawable.teacher);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }else if(data.contains("a")){
                dr = getResources().getDrawable(R.drawable.alunos);
                btnTag.setTextColor(Color.parseColor("#000000"));
            }

            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            bSearch2.addView(btnTag);

            final TextView a18 = (TextView) findViewById(R.id.title_chat);
            if (tela_de_carregamento.qual == "1"){
                a18.setText("CHAT BETA\n"+tela_de_alunos.onClick3);
            }else{
                a18.setText("CHAT BETA");
            }
        }
    }

    public void enviar_chat(View view){
        final EditText et2 = (EditText) findViewById(R.id.chat_text);
        String nn = et2.getText().toString();
        if (nn == null || nn == "" || nn == " ") {
            errormsg = "Você precisa escrever uma mensagem";
            erro();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            if (tela_de_carregamento.qual == "1"){
                myRefchat.child("chat").child(id_do_chat).child(currentDateandTime).setValue("p - " + currentDateandTime + ": " +nn);
            }else{
                myRefchat.child("chat").child(id_do_chat).child(currentDateandTime).setValue("a - " + currentDateandTime + ": " +nn);
            }
            et2.setText("");
            LinearLayout lyt = (LinearLayout) findViewById(R.id.ll_chat);
            lyt.removeAllViews();
            call_the_chat();
        }
    }

    String errormsg = "";

    public void erro() {
        AlertDialog alertDialog = new AlertDialog.Builder(chat.this).create();
        alertDialog.setTitle("ERRO!");
        alertDialog.setMessage(errormsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}