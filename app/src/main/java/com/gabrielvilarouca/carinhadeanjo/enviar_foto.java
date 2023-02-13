package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceContour;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceLandmark;
import com.lyft.android.scissors.CropView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;


import java.io.InputStream;
import java.util.List;

public class enviar_foto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_foto);

        final TextView a2 = (TextView) findViewById(R.id.text_informativo);
        a2.setText("FOTO DO ALUNO\nDeve contér um rosto visível!");

        //new AlertDialog.Builder(enviar_foto.this).setMessage("FOTO\nA foto deverá ser do seu filho(a), ou seja, do aluno(a) que frequenta a escola!").show();
    }

    public void escolher_imagem(View view){
        //Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //startActivityForResult(gallery, PICK_IMAGE);
        final TextView a2 = (TextView) findViewById(R.id.text_informativo);
        a2.setText("ABRINDO GALERIA, AGUARDE...");
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE);
    }

    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    String caminho;
    String msg_Caminho;

    CropView cropView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            final TextView a2 = (TextView) findViewById(R.id.text_informativo);
            a2.setText("PROCESSANDO FOTO...");
            try {
                View a = findViewById(R.id.imageView32);
                a.setVisibility(View.INVISIBLE);
                imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
               // bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                cropView = findViewById(R.id.crop_view);
                //cropView.setImageURI(imageUri);
                cropView.setImageBitmap(getRoundedCornerBitmap(bitmap, 400));
                a2.setText("ENVIO DE FOTO\nAgora, clique em enviar para que o aplicativo faça a analise.");
            } catch (IOException e) {
                e.printStackTrace();
                a2.setText("ERRO\nTente novamente ou entre em contato com o suporte.");
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

    public void enviar_foto(View view) throws FileNotFoundException {
        final TextView a2 = (TextView) findViewById(R.id.text_informativo);
        a2.setText("ANALISANDO FOTO...\nIsso pode demorar um pouco...");

        View b10 = findViewById(R.id.button10);
        b10.setVisibility(View.INVISIBLE);
        View b11 = findViewById(R.id.button11);
        b11.setVisibility(View.INVISIBLE);

        View a = findViewById(R.id.imc);
        a.setVisibility(View.VISIBLE);
        View b = findViewById(R.id.pgc);
        b.setVisibility(View.VISIBLE);

        try {
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            detectarRosto(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void detectarRosto(Bitmap bitmap){
        FaceDetector detector = FaceDetection.getClient();
        int rotationDegree = 0;
        InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);
        Task<List<Face>> result =
                detector.process(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<Face>>() {
                                    @Override
                                    public void onSuccess(List<Face> faces) {
                                        final TextView a2 = (TextView) findViewById(R.id.text_informativo);

                                        Log.d("ROSTINHO", "AQUI - " + (faces.isEmpty()));
                                        if (faces.isEmpty()){
                                            a2.setText("ERRO...\nRosto não foi detectado na foto.");
                                            tirar_carregamento();
                                        }else{
                                            a2.setText("SEGUNDA PARTE\nEnviando foto ao servidor...");
                                            caminho = "fotos_de_perfil/"+login_or_register.id +".png";
                                            msg_Caminho = "PRONTO\nVocê verá sua foto assim que abrir o aplicativo novamente!";
                                            enviar_servidor();
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        final TextView a2 = (TextView) findViewById(R.id.text_informativo);
                                        a2.setText("ERRO...\nRosto não foi detectado na foto.");
                                        tirar_carregamento();
                                    }
                                });
    }

    private StorageReference mStorageRef;
    public void enviar_servidor(){
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child(caminho);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                        //startActivity(intent);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(enviar_foto.this);
                        builder1.setMessage(msg_Caminho);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                       // Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                                        //startActivity(intent);
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        new AlertDialog.Builder(enviar_foto.this).setMessage("ERRO AO ENVIAR FOTO\n." + exception).show();
                        final TextView a2 = (TextView) findViewById(R.id.text_informativo);
                        a2.setText("ERRO AO ENVIAR FOTO\nNão foi possível fazer o envio da sua foto para o servidor.");
                        tirar_carregamento();
                    }
                });
    }

    public void tirar_carregamento(){
        View b10 = findViewById(R.id.button10);
        b10.setVisibility(View.VISIBLE);
        View b11 = findViewById(R.id.button11);
        b11.setVisibility(View.VISIBLE);

        View a = findViewById(R.id.imc);
        a.setVisibility(View.INVISIBLE);
        View b = findViewById(R.id.pgc);
        b.setVisibility(View.INVISIBLE);
    }

    /*@Override
    public void onBackPressed(){
       // new AlertDialog.Builder(enviar_foto.this).setMessage("VOCÊ NÃO PODE VOLTAR!\nO processo de cadastro já começou. Envie uma foto do aluno para continuar.").show();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(enviar_foto.this);
        builder1.setMessage("TEM CERTEZA DE QUE DESEJA VOLTAR?\nA foto é obrigatória!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "VOLTAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
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
    }*/
}