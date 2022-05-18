package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class aviso_importante extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef5 = database.child("P2").child(tela_de_carregamento.tturma).child("aviso_turma");

    String link_do_clique, avi_string_cliques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aviso_importante);

        View aa2=findViewById(R.id.avi_button_view);
        aa2.setVisibility(View.INVISIBLE);

        View aaaa1=findViewById(R.id.avi_carrera1_view);
        aaaa1.setVisibility(View.VISIBLE);
        View aaaa2=findViewById(R.id.avi_carrera2_view);
        aaaa2.setVisibility(View.VISIBLE);

        View aaa2 =findViewById(R.id.avi_olho_view);
        if (tela_de_carregamento.qual == "1"){
            aaa2.setVisibility(View.VISIBLE);
        }else{
            aaa2.setVisibility(View.INVISIBLE);
        }

        final TextView a1 = (TextView) findViewById(R.id.avi_title_view);
        a1.setText(tela_de_carregamento.avi_texto);
        final TextView a2 = (TextView) findViewById(R.id.avi_txt_view);
        a2.setText("CARREGANDO..");

        buscar_foto();
    }

    public static Bitmap my_image_avi;
    public void buscar_foto(){
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("fotos_aviso/"+tela_de_carregamento.tturma+".png");
        try {
            final File localFile = File.createTempFile("Images", "png");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image_avi = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.avi_img_view);
                    myImage.setImageBitmap(getRoundedCornerBitmap(my_image_avi,0));
                    buscar_textos();
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
        ImageView myImage = (ImageView) findViewById(R.id.avi_img_view);
        Drawable dr = getResources().getDrawable(R.drawable.brake);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        my_image_avi = bitmap;
        myImage.setImageBitmap(getRoundedCornerBitmap(my_image_avi,400));

        final TextView a2 = (TextView) findViewById(R.id.avi_txt_view);
        a2.setText("CARREGANDO.....");
        buscar_textos();
    }

    public void buscar_textos(){
        myRef5.child("avi_link").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                link_do_clique = dataSnapshot.getValue(String.class);
                if (link_do_clique != null && link_do_clique != "" && link_do_clique.isEmpty() == false){
                    View aa2=findViewById(R.id.avi_button_view);
                    aa2.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef5.child("avi_texto").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String avi_string_texto = dataSnapshot.getValue(String.class);
                if (avi_string_texto == null || avi_string_texto == ""){
                    avi_string_texto = "-";
                }
                final TextView a2 = (TextView) findViewById(R.id.avi_txt_view);
                a2.setText(avi_string_texto);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef5.child("avi_cliques").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                avi_string_cliques = dataSnapshot.getValue(String.class);
                if (avi_string_cliques == null){
                    avi_string_cliques = "SEM VISUALIZAÇÕES";
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        View aaaa1=findViewById(R.id.avi_carrera1_view);
        aaaa1.setVisibility(View.INVISIBLE);
        View aaaa2=findViewById(R.id.avi_carrera2_view);
        aaaa2.setVisibility(View.INVISIBLE);
    }

    public void clique_do_clique(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link_do_clique));
        startActivity(browserIntent);
    }

    public void ver_visualizar(View view){
        new AlertDialog.Builder(aviso_importante.this).setMessage(avi_string_cliques).show();
    }

}