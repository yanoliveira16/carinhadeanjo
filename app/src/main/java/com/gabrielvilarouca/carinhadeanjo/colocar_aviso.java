package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class colocar_aviso extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef5 = database.child("P2").child(tela_de_carregamento.tturma).child("aviso_turma");

    String titulo, textoo, linkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocar_aviso);

        final TextView a1 = (TextView) findViewById(R.id.put_avi_turma);
        a1.setText(tela_de_carregamento.tturma);

        View aa2=findViewById(R.id.put_carrega1);
        aa2.setVisibility(View.INVISIBLE);
        View aa3=findViewById(R.id.put_carrega2);
        aa3.setVisibility(View.INVISIBLE);
    }

    public void enviar(View view){
        View aa2=findViewById(R.id.put_carrega1);
        aa2.setVisibility(View.VISIBLE);
        View aa3=findViewById(R.id.put_carrega2);
        aa3.setVisibility(View.VISIBLE);
        View aa4=findViewById(R.id.put_avi_img);
        aa4.setVisibility(View.INVISIBLE);
        final EditText et = (EditText) findViewById(R.id.put_avi_title);
        titulo = et.getText().toString();
        final EditText et2 = (EditText) findViewById(R.id.put_avi_texto);
        textoo = et2.getText().toString();
        Log.d("AQUI AVISO","-"+titulo+"-");
        if (titulo.isEmpty() == false){
            enviar2();
        }else{
            erro_msg = "VOCÊ NÃO PODE ENVIAR UM AVISO SEM TÍTULO!";
            erro();
        }
    }

    public void enviar2(){
        final EditText et3 = (EditText) findViewById(R.id.put_avi_link);
        linkin = et3.getText().toString();
        if (linkin.isEmpty() == false){
            if (linkin.contains("https://") != false){
                myRef5.child("avi_title").setValue(titulo);
                myRef5.child("avi_texto").setValue(textoo);
                myRef5.child("avi_link").setValue(linkin);
                enviar3();
            }else{
                erro_msg = "VOCÊ NÃO PODE ENVIAR UM LINK SEM O https:// NO COMEÇO!";
                erro();
            }
        }else{
            myRef5.child("avi_title").setValue(titulo);
            myRef5.child("avi_texto").setValue(textoo);
            myRef5.child("avi_link").setValue(linkin);
            enviar3();
        }
    }

    private static final int PICK_IMAGE = 1;
    private StorageReference mStorageRef;
    Uri imageUri;
    boolean tem_img = false;
    public void enviar3(){
        if (tem_img == false){
            imageUri = Uri.parse("android.resource://com.gabrielvilarouca.carinhadeanjo/" + R.drawable.brake);
        }
        ImageView myImage = (ImageView) findViewById(R.id.put_img_view);
        myImage.setImageURI(imageUri);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child("fotos_aviso/"+tela_de_carregamento.tturma+".png");
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        sucesso();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        erro_msg = "NÃO FOI POSSÍVEL ENVIAR A IMAGEM \n " + exception;
                        erro();
                    }
                });
    }



    public void buscar_img(View view){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            tem_img = true;
            ImageView myImage = (ImageView) findViewById(R.id.put_img_view);
            myImage.setImageURI(imageUri);
            Button button = (Button) findViewById(R.id.put_avi_img);
            button.setText("ESCOLHER OUTRA IMAGEM");
        }else{
            erro_msg = "NÃO FOI POSSÍVEL PEGAR A IMAGEM";
            erro();
        }
    }

    public void sucesso(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(colocar_aviso.this);
        builder1.setMessage("ENVIADO\nO AVISO FOI ENVIADO COM SUCESSO. REINICIE O APP PARA VER NA TELA DE INÍCIO COMO FICOU.");
        builder1.setCancelable(true);

        builder1.setNegativeButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    String erro_msg;
    public void erro(){
        View aa2=findViewById(R.id.put_carrega1);
        aa2.setVisibility(View.INVISIBLE);
        View aa3=findViewById(R.id.put_carrega2);
        aa3.setVisibility(View.INVISIBLE);
        View aa4=findViewById(R.id.put_avi_img);
        aa4.setVisibility(View.VISIBLE);
        new AlertDialog.Builder(colocar_aviso.this).setMessage(erro_msg).show();
    }
}