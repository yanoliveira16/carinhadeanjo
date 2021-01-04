package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class tela_do_aluno_prof extends AppCompatActivity {

    public static String id_aluno;

    private StorageReference mStorageRef;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P2");
    DatabaseReference myRef2 = database.child("P3");
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_do_aluno_prof);

        myRef.child(tela_de_carregamento.tturma).child("P2-1").child(tela_de_alunos.onClick3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id_aluno = dataSnapshot.getValue(String.class);
                TextView a1 = (TextView) findViewById(R.id.turma3);
                ppp();
                myRef2.child(id_aluno).child("faltas").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int faltas = dataSnapshot.getValue(Integer.class);
                        a1.setText(tela_de_alunos.onClick3 + "\nFaltas: "+ faltas);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
                myRef2.child(id_aluno).child("recado_profe").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String recado = dataSnapshot.getValue(String.class);
                        TextView a1 = (EditText) findViewById(R.id.recado_profis);
                        a1.setText(recado);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public static Bitmap my_image;
    public void ppp(){
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("fotos_de_perfil/"+id_aluno+".png");
        try {
            final File localFile = File.createTempFile("Images", "png");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.imageView31);
                    if (myImage != null){
                        myImage.setImageBitmap(getRoundedCornerBitmap(my_image,400));
                    }else{
                        new AlertDialog.Builder(tela_do_aluno_prof.this).setMessage("Erro ao buscar foto do aluno!").show();
                    }
                    View a1=findViewById(R.id.hgf1);
                    a1.setVisibility(View.INVISIBLE);

                    View a2=findViewById(R.id.hgf2);
                    a2.setVisibility(View.INVISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(tela_do_aluno_prof.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(tela_do_aluno_prof.this).setMessage("ALUNO SEM FOTO!").show();
                    View a1=findViewById(R.id.hgf1);
                    a1.setVisibility(View.INVISIBLE);

                    View a2=findViewById(R.id.hgf2);
                    a2.setVisibility(View.INVISIBLE);
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

    public void enviar(View view){
        final EditText et2 = (EditText) findViewById(R.id.recado_profis);
        myRef2.child(id_aluno).child("recado_profe").setValue(et2.getText().toString());
        new AlertDialog.Builder(tela_do_aluno_prof.this).setMessage("Enviado com sucesso!").show();
    }



    public void agenda_click (View view){
        Intent intent = new Intent(getBaseContext(), lista_um.class);
        startActivity(intent);

    }

    public void chat_click(View view){
        Intent intent = new Intent(getBaseContext(), chat.class);
        startActivity(intent);
    }



}