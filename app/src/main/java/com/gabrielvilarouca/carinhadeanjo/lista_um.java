package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

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
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.List;

public class lista_um extends AppCompatActivity {
    List<String> feed2;
    public static  String onClick;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_um);

        feed2 = new ArrayList<>();
        View aa=findViewById(R.id.add);
        final TextView a1 = (TextView) findViewById(R.id.aluno_agenda);
        String onde_pegar;
        if (tela_de_carregamento.qual == "1"){
            a1.setText("AGENDA\n"+tela_de_alunos.onClick3);
            ImageView myImage = (ImageView) findViewById(R.id.imageView18);
            myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400));
            aa.setVisibility(View.VISIBLE);
            onde_pegar = tela_do_aluno_prof.id_aluno;
        }else{
            a1.setText("AGENDA\n"+tela_de_carregamento.nnomeAluno);
            ImageView myImage = (ImageView) findViewById(R.id.imageView18);
            myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno.my_image3,400));
            aa.setVisibility(View.INVISIBLE);
            onde_pegar = login_or_register.id;
        }

        myRef.child(onde_pegar).child("Agenda").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String key= snapshot.getKey();
                    feed2.add(key);
                }

                Collections.sort(feed2, new Comparator<String>() {

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

        /*View aa=findViewById(R.id.add);
        final TextView a1 = (TextView) findViewById(R.id.aluno_agenda);
        if (tela_de_carregamento.qual == "1"){
            a1.setText("AGENDA\n"+tela_de_alunos.onClick3);
            ImageView myImage = (ImageView) findViewById(R.id.imageView18);
            myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400));
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
            a1.setText("AGENDA\n"+tela_de_carregamento.nnomeAluno);
            ImageView myImage = (ImageView) findViewById(R.id.imageView18);
            myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno.my_image3,400));
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
        });*/

    }

    int itemCount;
    Integer id_do_button = 10000;
    public static Bitmap bitmap;
    public void adicionar_aofeed(){
        itemCount = feed2.size();
        while(itemCount != 0){
            itemCount -= 1;
            String data2 = feed2.get(itemCount);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.linear_listaum);
            id_do_button += 1;
            Button btnTag = new Button(lista_um.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText(data2);
            btnTag.setId(id_do_button);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    onClick = data2;
                    Intent intent = new Intent(getBaseContext(), lista_dois.class);
                    startActivity(intent);
                }
            });

            Drawable dr = getResources().getDrawable(R.drawable.close);
            dr = getResources().getDrawable(R.drawable.calendar_quatro);
            bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            Display display=getWindowManager().getDefaultDisplay();
            int width=display.getWidth();
            btnTag.setWidth(width);

            bSearch2.addView(btnTag);

            /*TextView txt1 = new TextView(lista_um.this);
            txt1.setText("---------------------------------------------------------------------------------");
            // txt1.setText("_______________________________________________________");
            txt1.setGravity(Gravity.CENTER | Gravity.CENTER);
            bSearch2.addView(txt1);*/


            ScrollView scroll = (ScrollView) findViewById(R.id.scroll_listaum);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(5, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(20);
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


    public void add_click (View view){
        Intent intent = new Intent(getBaseContext(), agenda.class);
        startActivity(intent);

    }

}



