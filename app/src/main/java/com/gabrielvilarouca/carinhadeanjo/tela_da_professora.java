package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class tela_da_professora extends AppCompatActivity {
    List<String> feed;
    List<String> feed2;
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

            final TextView a1 = (TextView) findViewById(R.id.turma2);
            a1.setText(tela_de_carregamento.tturma);
             final TextView a2 = (TextView) findViewById(R.id.nome_prof);
            a2.setText(tela_de_carregamento.nnomeProfe);

        //String versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        String versionName = String.valueOf(BuildConfig.VERSION_NAME);

        if (versionName.contains(tela_de_carregamento.versao) == false) {
            new AlertDialog.Builder(tela_da_professora.this).setMessage("NOVA ATUALIZAÇÃO DISPONÍVEL\n\nRecomendamos que atualize seu aplicativo antes do uso!\n\nVersão atual: " + versionName + "\nNova versão: " + tela_de_carregamento.versao).show();
        }

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
        if (tela_de_carregamento.tem_coordena.contains("tem")){
            //finish();
            Intent intent = new Intent(getBaseContext(), coordena.class);
            startActivity(intent);
        }else{
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
       if (tela_de_carregamento.tturma == null){
           /*new AlertDialog.Builder(tela_da_professora.this).setMessage("Não encontramos sua turma.\nTente fechar e abrir o aplicativo. Se isso não funcionar, tente sair da sua conta e entrar novamente!" +
                   "\nNão recomendamos que faça o uso do aplicativo com este erro.").show();*/
           call_newfeed();
       }else{
           call_newfeed();
       }
    }

    public void call_newfeed(){
        myRef.child(tela_de_carregamento.tturma).child("FEED").addListenerForSingleValueEvent(new ValueEventListener() {
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
    public static Bitmap bitmap;
    public void adicionar_aofeed(){
        itemCount = feed.size();
        while(itemCount != 0){
            itemCount -= 1;
            String data = feed.get(itemCount);
            String data2 = feed2.get(itemCount);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.linear_feed);
            id_do_button += 1;
            Button btnTag = new Button(tela_da_professora.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText("    "+data);
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
                    dr = getResources().getDrawable(R.drawable.exam);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("AGENDA")){
                    dr = getResources().getDrawable(R.drawable.folder);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("AVISO")){
                    dr = getResources().getDrawable(R.drawable.brake);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("REUNIÃO") || data.contains("EVENTO")){
                    dr = getResources().getDrawable(R.drawable.calendar);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("IMAGEM")){
                    dr = getResources().getDrawable(R.drawable.auto);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("SERVIDOR")){
                    dr = getResources().getDrawable(R.drawable.server);
                    btnTag.setTextColor(Color.parseColor("#E91E63"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else if(data.contains("ALUNO")){
                    dr = getResources().getDrawable(R.drawable.mortarboard);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }else{
                    dr = getResources().getDrawable(R.drawable.chat);
                    btnTag.setTextColor(Color.parseColor("#000000"));
                    bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
                }

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
            gd.setStroke(0, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(70);
            scroll.setBackground(gd);
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

    // Get a MemoryInfo object for the device's current memory status.
    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
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
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_feed);
        ll.removeAllViews();
        new_feed();
    }

    public void pdf_open(View view){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(tela_da_professora.this);
        builderSingle.setIcon(R.drawable.exam );
        builderSingle.setTitle("PDF's - Escolha qual deseja visualizar:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(tela_da_professora.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Cardápio");
        arrayAdapter.add("Calendário Escolar");
        arrayAdapter.add("Tema Gerador");
        arrayAdapter.add("Rotina Semanal");
        arrayAdapter.add("Avisos Gerais");
        arrayAdapter.add("Avisos da Turma");

        builderSingle.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                if (strName == "Cardápio"){
                    tela_de_carregamento.pdf_qualfile = "cardapio.pdf";
                }else if(strName == "Calendário Escolar"){
                    if(tela_de_carregamento.tturma.contains("Fundamental 2") || tela_de_carregamento.tturma.contains("Fundamental 3") || tela_de_carregamento.tturma.contains("Fundamental 4") ){
                        tela_de_carregamento.pdf_qualfile = "calendario2.pdf";
                    }else{
                        tela_de_carregamento.pdf_qualfile = "calendario1.pdf";
                    }
                }else if(strName == "Tema Gerador"){
                    tela_de_carregamento.pdf_qualfile = "tema_"+tela_de_carregamento.tturma + ".pdf";
                }else if(strName == "Rotina Semanal"){
                    tela_de_carregamento.pdf_qualfile = "rotina_"+tela_de_carregamento.tturma + ".pdf";
                }else if(strName == "Avisos Gerais"){
                    tela_de_carregamento.pdf_qualfile = "avisos.pdf";
                }else if(strName == "Avisos da Turma"){
                    tela_de_carregamento.pdf_qualfile = "avisos_"+tela_de_carregamento.tturma + ".pdf";
                }
                Intent intent = new Intent(getBaseContext(), view_pdf.class);
                startActivity(intent);
            }
        });
        builderSingle.show();
    }




}
