package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lista_ano extends AppCompatActivity {
    List<String> feed2;
    public static  String onClick;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ano);

        feed2 = new ArrayList<>();
        View aa=findViewById(R.id.add3);
        final TextView a1 = (TextView) findViewById(R.id.aluno_nomeano);
        String onde_pegar;
        if (tela_de_carregamento.qual == "1"){
            a1.setText("AGENDA\n"+tela_de_alunos.onClick3);
            ImageView myImage3_3 = (ImageView) findViewById(R.id.imageView20);
            myImage3_3.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400));
            aa.setVisibility(View.VISIBLE);
            onde_pegar = tela_do_aluno_prof.id_aluno;
        }else{
            a1.setText("AGENDA\n"+tela_de_carregamento.nnomeAluno);
            ImageView myImage3_3 = (ImageView) findViewById(R.id.imageView20);
            myImage3_3.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno.my_image3,400));
            aa.setVisibility(View.INVISIBLE);
            onde_pegar = login_or_register.id;
        }

        myRef.child(onde_pegar).child("AGENDA").addListenerForSingleValueEvent(new ValueEventListener() {
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
    }

    int itemCount;
    Integer id_do_button = 10000;
    public static Bitmap bitmap;
    public void adicionar_aofeed(){
        itemCount = feed2.size();
        while(itemCount != 0){
            itemCount -= 1;
            String data2 = feed2.get(itemCount);
            LinearLayout bSearch2 = (LinearLayout) findViewById(R.id.linear_listaano);
            id_do_button += 1;
            Button btnTag = new Button(lista_ano.this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText("    "+data2);
            btnTag.setId(id_do_button);
            btnTag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    onClick = data2;
                    Intent intent = new Intent(getBaseContext(), lista_um.class);
                    startActivity(intent);
                }
            });

            Drawable dr = getResources().getDrawable(R.drawable.close);
            dr = getResources().getDrawable(R.drawable.vintecalendar);
            bitmap = (getRoundedCornerBitmap(((BitmapDrawable) dr).getBitmap(),100));
            Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 140, 140, true));
            btnTag.setCompoundDrawablesWithIntrinsicBounds( d, null, null, null);

            btnTag.setBackgroundResource(0);
            btnTag.setGravity(Gravity.LEFT | Gravity.CENTER);

            Display display=getWindowManager().getDefaultDisplay();
            int width=display.getWidth();
            btnTag.setWidth(width);

            Typeface typeface = ResourcesCompat.getFont(this, R.font.quicksand_bold);
            btnTag.setTypeface(typeface);
            btnTag.setTextSize(20);

            bSearch2.addView(btnTag);


            ScrollView scroll = (ScrollView) findViewById(R.id.scroll_listaano);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(15, Color.argb(100, 0,0,0)); // border width and color
            //gd.setCornerRadius(80.50f);
            gd.setCornerRadius(60);
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


    public void home_click(View view){
        //professor
        if (tela_de_carregamento.qual == "1"){
            Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
            startActivity(intent);
        }else{ //não professor
            Intent intent = new Intent(getBaseContext(), tela_do_aluno.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){

        finish();

    }
}