package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class tela_do_aluno extends AppCompatActivity {
    List<String> feed;
    List<String> feed2;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");


    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_do_aluno);

        feed = new ArrayList<>();
        feed2 = new ArrayList<>();

        final TextView a1 = (TextView) findViewById(R.id.aluno);
        a1.setText(tela_de_carregamento.nnomeAluno);

        final TextView a2 = (TextView) findViewById(R.id.turma);
        a2.setText(tela_de_carregamento.tturma+" - "+tela_de_carregamento.faltar_no_total+" faltas");

        String versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        //String versionName = String.valueOf(BuildConfig.VERSION_NAME);

        if (versionCode.contains("12") == false) {
            new AlertDialog.Builder(tela_do_aluno.this).setMessage("Atualize seu aplicativo!").show();
        }

        new_feed();

       // new AlertDialog.Builder(tela_do_aluno.this).setMessage("BETA\nO aplicativo ainda se encontra em desenvolvimento.\nConfira novidades e tutorais em http://escolacarinhadeanjodf.com/aplicativo").show();

    }

    public static Bitmap my_image3;
    public void ppp(){
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("fotos_de_perfil/"+login_or_register.id+".png");
        try {
            final File localFile = File.createTempFile("Images", "png");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image3 = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.imageView13);
                    myImage.setImageBitmap(getRoundedCornerBitmap(my_image3,400));
                    View a1=findViewById(R.id.kkkl1);
                    a1.setVisibility(View.INVISIBLE);

                    View a2=findViewById(R.id.kkkl2);
                    a2.setVisibility(View.INVISIBLE);

                    View a3=findViewById(R.id.sair);
                    a3.setVisibility(View.VISIBLE);
                   
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(tela_do_aluno.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    coloque_foto();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
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

    public void coloque_foto(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(tela_do_aluno.this);
        builder1.setMessage("A FOTO É OBRIGATÓRIA\nColoque uma foto do seu filho(a).\nA foto deve ser adequada e possuir um rosto!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "COLOCAR FOTO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getBaseContext(), enviar_foto.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "DEPOIS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("AQUI","DEPOIS CLICK");
                        ImageView myImage = (ImageView) findViewById(R.id.imageView13);
                        Drawable dr = getResources().getDrawable(R.drawable.new_profile);
                        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                        my_image3 = bitmap;
                        myImage.setImageBitmap(getRoundedCornerBitmap(my_image3,400));

                        View a1=findViewById(R.id.kkkl1);
                        a1.setVisibility(View.INVISIBLE);

                        View a2=findViewById(R.id.kkkl2);
                        a2.setVisibility(View.INVISIBLE);

                        View a3=findViewById(R.id.sair);
                        a3.setVisibility(View.VISIBLE);
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void sair_click3(View view) {
        sairDaqui();
    }

    @Override
    public void onBackPressed(){
        sairDaqui();
    }

    public void sairDaqui(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(tela_do_aluno.this);
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

    public void lista_um_click(View view){
        Intent intent = new Intent(getBaseContext(), lista_um.class);
        startActivity(intent);
    }

    Integer id_do_button = 5000;
    public void new_feed(){
        Log.d("AQUI", "AAAAAAAAA");
        myRef.child(tela_de_carregamento.tturma).child("feed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    if (key.contains("aln") == true && key.contains(login_or_register.id) == true || key.contains("prof") == false){
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
        Log.d("AQUI", "AA " +itemCount);
        while(itemCount != 0){
            itemCount -= 1;
            String data = feed.get(itemCount);
            String data2 = feed2.get(itemCount);
            Log.d("AQUI", "feed" +data);
            Log.d("AQUI", "feed2" +data2);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.linear_feed_aluno);
            id_do_button += 1;
            Button btnTag = new Button(tela_do_aluno.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText(data);
            btnTag.setId(id_do_button);
            btnTag.setTextColor(Color.parseColor("#E91E63"));
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
            if (data.contains("ATIVIDADE") == true){
                dr = getResources().getDrawable(R.drawable.al_um);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("AGENDA") == true){
                dr = getResources().getDrawable(R.drawable.al_dois);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("AVISO") == true){
                dr = getResources().getDrawable(R.drawable.al_tres);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("REUNIÃO") == true || data.contains("EVENTO") == true){
                dr = getResources().getDrawable(R.drawable.calendar_quatro);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("IMAGEM") == true){
                dr = getResources().getDrawable(R.drawable.picture);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("SERVIDOR") == true){
                dr = getResources().getDrawable(R.drawable.database);
                btnTag.setTextColor(Color.parseColor("#E91E63"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else if(data.contains("ALUNO") == true){
                dr = getResources().getDrawable(R.drawable.alunos);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }else{
                dr = getResources().getDrawable(R.drawable.message);
                btnTag.setTextColor(Color.parseColor("#000000"));
                bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            }

            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            bSearch2.addView(btnTag);

            /*TextView txt1 = new TextView(tela_do_aluno.this);
            txt1.setText("---------------------------------------------------------------------------------");
            // txt1.setText("_______________________________________________________");
            txt1.setGravity(Gravity.CENTER | Gravity.CENTER);
            bSearch2.addView(txt1);*/


            ScrollView scroll = (ScrollView) findViewById(R.id.scroll_aluno_feed);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(70);
            scroll.setBackground(gd);
        }
        if (carregado == false) {
            carregado = true;
            ppp();
        }
    }

    boolean carregado = false;
   /* public void atualizar_feed(){
        myRef.child(tela_de_carregamento.tturma).child("feed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    String value=snapshot.getValue().toString();
                    // feed.add(key);
                    Log.d("FEED", "key: " + key.contains("profe") +"| aa: "+key.contains("aln") + " "+ key.contains(login_or_register.id));
                    if (key.contains("profe")==false || key.contains("aln")==true && key.contains(login_or_register.id)==true){
                        feed.add(value);
                        Log.d("FEED 2", "ENTROU: "+ value);
                    }
                    listView = findViewById(R.id.listView);
                    GradientDrawable gd = new GradientDrawable();
                    gd.setShape(GradientDrawable.RECTANGLE);
                    gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
                    //gd.setCornerRadius(80.50f);
                    gd.setCornerRadius(80);
                    listView.setBackground(gd);
                    listView.setAdapter(arrayAdapter);
                }
                //Collections.sort(feed);
                //Collections.reverse(feed);
                ppp();
                //Log.d("FEED", "feed: " + feed +"| aa: "+arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, feed);
    }*/

    public void att_feed(View view){
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_feed_aluno);
        ll.removeAllViews();
        new_feed();
    }

    public void chat_click(View view){
        Intent intent = new Intent(getBaseContext(), chat.class);
        startActivity(intent);
    }

    public void pdf_open(View view){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(tela_do_aluno.this);
        builderSingle.setIcon(R.drawable.new_paste);
        builderSingle.setTitle("PDF's - Escolha qual deseja vê:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(tela_do_aluno.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Cardápio");
        arrayAdapter.add("Calendário Escolar");
        arrayAdapter.add("Tema Gerador");
        arrayAdapter.add("Rotina Semanal");
        arrayAdapter.add("Avisos Gerais");

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
                    tela_de_carregamento.pdf_qualfile = "rotina"+tela_de_carregamento.tturma + ".pdf";
                }else if(strName == "Avisos Gerais"){
                    tela_de_carregamento.pdf_qualfile = "avisos.pdf";
                }
                Intent intent = new Intent(getBaseContext(), view_pdf.class);
                startActivity(intent);
            }
        });
        builderSingle.show();
    }



        }




