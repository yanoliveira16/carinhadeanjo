package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
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
    String email_alunoprofe;
    String senha_alunoprofe;

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
                ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();
                TextView a1 = (TextView) findViewById(R.id.turma3);
                ppp();
                pegar_info_emailsenha();
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
                myRef2.child(id_aluno).child("ultimo_login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String ultimologa = dataSnapshot.getValue(String.class);
                        TextView abbbc1 = (TextView) findViewById(R.id.ultimologin);
                        abbbc1.setText("Último login do aluno no aplicativo:\n" +ultimologa);
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

    public void pegar_info_emailsenha(){
        myRef2.child(id_aluno).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email_alunoprofe = dataSnapshot.getValue(String.class);
                if (email_alunoprofe != null){
                    final TextView a1 = (TextView) findViewById(R.id.email_text);
                    a1.setText(email_alunoprofe);
                }else{
                    View emailvii1 =findViewById(R.id.email_view);
                    emailvii1.setVisibility(View.INVISIBLE);
                    View emailvii2 =findViewById(R.id.email_text);
                    emailvii2.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(id_aluno).child("senha").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                senha_alunoprofe = dataSnapshot.getValue(String.class);
                if(senha_alunoprofe != null){
                    final TextView a1 = (TextView) findViewById(R.id.senha_text);
                    a1.setText(senha_alunoprofe);
                }else{
                    View senhavii1 =findViewById(R.id.senha_view);
                    senhavii1.setVisibility(View.INVISIBLE);
                    View senhavii2 =findViewById(R.id.senha_text);
                    senhavii2.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        final TextView a1111 = (TextView) findViewById(R.id.id_text);
        a1111.setText(id_aluno);
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
                    ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();
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
                    ImageView myImage = (ImageView) findViewById(R.id.imageView31);
                    Drawable dr = getResources().getDrawable(R.drawable.new_profile);
                    Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
                    my_image = bitmap;
                    myImage.setImageBitmap(getRoundedCornerBitmap(my_image,400));

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

    // Get a MemoryInfo object for the device's current memory status.
    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
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


    public void agenda_click (View view){
        Intent intent = new Intent(getBaseContext(), lista_ano.class);
        startActivity(intent);
    }

    public void mudar(View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(tela_do_aluno_prof.this);
        builder1.setMessage("RECURSO EM DESENVOLVIMENTO\nEstamos com muitas novidades, e em breve iremos liberar mais recursos.");
        builder1.setCancelable(true);
    }

    public void chat_click(View view){
        Intent intent = new Intent(getBaseContext(), chat.class);
        startActivity(intent);
    }

    public void ve_emailesenha(View view){
        new AlertDialog.Builder(tela_do_aluno_prof.this).setMessage("INFORMAÇÕES DO ALUNO\n\nID:\n"+id_aluno +"\n\nEMAIL:\n" +email_alunoprofe + "\n\nSENHA:\n" +senha_alunoprofe).show();
    }


}