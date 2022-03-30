package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class click_feed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_feed);

        final TextView aa = (TextView) findViewById(R.id.textView10);
        aa.setText(tela_de_carregamento.onClick19);
        ImageView img= (ImageView) findViewById(R.id.imageView34);
        if (tela_de_carregamento.key_feed.contains("atv")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.exam);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("agd")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.folder);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("avs")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.brake);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("reun")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.calendar);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("outr")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.mortarboard);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("profe")){
            if(tela_de_carregamento.onClick19.contains("ALUNO")){
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.mortarboard);
                img.setImageBitmap(bitmap);
            }else{
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.chat);
                img.setImageBitmap(bitmap);
            }
        }else if (tela_de_carregamento.key_feed.contains("pic")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.auto);
            img.setImageBitmap(bitmap);
        }else if (tela_de_carregamento.key_feed.contains("serve")){
            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.database);
            img.setImageBitmap(bitmap);
        }
    }

}