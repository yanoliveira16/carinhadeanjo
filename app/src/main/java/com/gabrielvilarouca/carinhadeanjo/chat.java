package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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
    DatabaseReference myRef_feed = database.child("P2").child(tela_de_carregamento.tturma);
    DatabaseReference refchat = database.child("P3");
    String id_aluno_chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        feed = new ArrayList<>();
        feed2 = new ArrayList<>();

        final TextView a18 = (TextView) findViewById(R.id.title_chat);
        if (tela_de_carregamento.qual == "1"){
            id_aluno_chat = tela_do_aluno_prof.id_aluno;
            a18.setText("CHAT\n"+tela_de_alunos.onClick3);
            call_the_chat();
        }else{
            id_aluno_chat = login_or_register.id;
            a18.setText("CHAT COM A PROFESSORA");
            call_the_chat();
        }

    }

    public void call_the_chat(){
        refchat.child(id_aluno_chat).child("CHAT").addListenerForSingleValueEvent(new ValueEventListener() {
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
    public static Bitmap bitmap;
    public void adicionar_aofeed(){
        itemCount = feed.size();
        while(itemCount != 0){
            itemCount -= 1;
            String data = feed.get(itemCount);
            String data2 = feed2.get(itemCount);


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
            if (data.startsWith("p")){
                dr = getResources().getDrawable(R.drawable.aaazul);
                btnTag.setTextColor(Color.parseColor("#003FC1"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),400));
            }else if(data.startsWith("a")){
                btnTag.setTextColor(Color.parseColor("#000000"));
                if (tela_de_carregamento.qual == "1"){
                    bitmap = (getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400));
                }else{
                    bitmap = (getRoundedCornerBitmap(tela_do_aluno.my_image3,400));
                }
            }
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            bSearch2.addView(btnTag);

            /*TextView txt1 = new TextView(chat.this);
            txt1.setText("--------------------------------");
            txt1.setGravity(Gravity.CENTER | Gravity.CENTER);
            bSearch2.addView(txt1);*/

            final TextView a18 = (TextView) findViewById(R.id.title_chat);
            if (tela_de_carregamento.qual == "1"){
                a18.setText("CHAT\n"+tela_de_alunos.onClick3);
            }else{
                a18.setText("CHAT COM A PROFESSORA");
            }
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
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
                refchat.child(id_aluno_chat).child("CHAT").child(currentDateandTime).setValue("p - " + currentDateandTime + ": " +nn);
            }else{
                refchat.child(id_aluno_chat).child("CHAT").child(currentDateandTime).setValue("a - " + currentDateandTime + ": " +nn);
            }
            enviar_ao_main_feed();
        }
    }

    public void enviar_ao_main_feed(){
        myRef_feed.child("TOTAL_FEED").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer nn = dataSnapshot.getValue(Integer.class);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                if (nn == null){
                    nn = 1;
                    myRef_feed.child("TOTAL_FEED").setValue(nn);
                }else{
                    if(nn >= 95){
                        nn = 1;
                        myRef_feed.child("FEED").removeValue();
                        myRef_feed.child("FEED").child(nn +" - serve").setValue(currentDateandTime + " - SERVIDOR: Feed limpo!");
                        nn += 1;
                        myRef_feed.child("TOTAL_FEED").setValue(nn);
                    }else{
                        nn += 1;
                        myRef_feed.child("TOTAL_FEED").setValue(nn);
                    }
                }

                if (tela_de_carregamento.qual == "1"){
                    myRef_feed.child("FEED").child(nn +" - aln - " + id_aluno_chat).setValue(currentDateandTime + " - MSG: Nova mensagem no CHAT");
                }else{
                    myRef_feed.child("FEED").child(nn +" - profe - " + id_aluno_chat).setValue(currentDateandTime + " - MSG: Nova mensagem no CHAT com " + tela_de_carregamento.nnomeAluno);
                }

                new AlertDialog.Builder(chat.this).setMessage("Enviado com sucesso!").show();
                final EditText et2 = (EditText) findViewById(R.id.chat_text);
                et2.setText("");
                LinearLayout lyt = (LinearLayout) findViewById(R.id.ll_chat);
                lyt.removeAllViews();
                call_the_chat();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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