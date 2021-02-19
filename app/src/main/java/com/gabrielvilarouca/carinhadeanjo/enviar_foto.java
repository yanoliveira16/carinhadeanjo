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
import com.google.android.gms.vision.face.FaceDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lyft.android.scissors.CropView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;


import java.io.InputStream;

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
                a2.setText("FOTO DO ALUNO\nCertifique-se de que o rosto esteja visível!");
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

        setUpFaceDetector(imageUri);
    }


    private void setUpFaceDetector(Uri selectedImage) throws FileNotFoundException {
        com.google.android.gms.vision.face.FaceDetector faceDetector = new
                FaceDetector.Builder(getApplicationContext()).setTrackingEnabled(false)
                .build();
        if(!faceDetector.isOperational()){
            new android.app.AlertDialog.Builder(this).setMessage("Could not set up the face detector!").show();
            return;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable=true;

        InputStream ims = getContentResolver().openInputStream(selectedImage);

        Bitmap myBitmap = BitmapFactory.decodeStream(ims);

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Face> faces = faceDetector.detect(frame);

        Log.d("TEST", "Num faces = " + faces.size());

        detectedResponse(myBitmap, faces);
    }


    public void detectedResponse(Bitmap myBitmap, SparseArray<Face> faces) {
        Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE);

        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

        for(int i=0; i<faces.size(); i++) {
            Face thisFace = faces.valueAt(i);
            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
            float x2 = x1 + thisFace.getWidth();
            float y2 = y1 + thisFace.getHeight();
            tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
        }

        cropView = findViewById(R.id.crop_view);
        cropView.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));

        if (faces.size() < 1) {
            final TextView a2 = (TextView) findViewById(R.id.text_informativo);
            a2.setText("ERRO\nNenhum rosto foi detectado.");

            AlertDialog.Builder builder1 = new AlertDialog.Builder(enviar_foto.this);
            builder1.setMessage("ERRO\nNenhum rosto foi detectado.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "AJUDAR",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.escolacarinhadeanjodf.com/erro-foto"));
                            startActivity(browserIntent);
                            //caminho = "fotos_com_erro/" +login_or_register.id +".png";
                            //msg_Caminho = "PRONTO\nCriamos e enviamos um relatório de erro.";
                            enviar_servidor();
                        }
                    });

            builder1.setNegativeButton(
                    "Tentar novamente",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            View b10 = findViewById(R.id.button10);
            b10.setVisibility(View.VISIBLE);
            View b11 = findViewById(R.id.button11);
            b11.setVisibility(View.VISIBLE);

            View a = findViewById(R.id.imc);
            a.setVisibility(View.INVISIBLE);
            View b = findViewById(R.id.pgc);
            b.setVisibility(View.INVISIBLE);
        }
        else if (faces.size() == 1) {
           // new AlertDialog.Builder(this).setMessage("Rosto detectado\nQue coisa lindaaaa.").show();
            final TextView a2 = (TextView) findViewById(R.id.text_informativo);
            a2.setText("SEGUNDA PARTE\nEnviando foto ao servidor...");
            caminho = "fotos_de_perfil/"+login_or_register.id +".png";
            msg_Caminho = "PRONTO\nVocê verá sua foto assim que abrir o aplicativo novamente!";
            enviar_servidor();
        }
        else if (faces.size() > 1) {
            //new AlertDialog.Builder(this).setMessage("Mais de um rosto foi detectado.").show();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(enviar_foto.this);
            builder1.setMessage("ERRO\nMais de um rosto foi detectado na foto!");
            builder1.setCancelable(true);

            builder1.setNegativeButton(
                    "Tentar novamente",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
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
                        View b10 = findViewById(R.id.button10);
                        b10.setVisibility(View.VISIBLE);
                        View b11 = findViewById(R.id.button11);
                        b11.setVisibility(View.VISIBLE);

                        View a = findViewById(R.id.imc);
                        a.setVisibility(View.INVISIBLE);
                        View b = findViewById(R.id.pgc);
                        b.setVisibility(View.INVISIBLE);
                    }
                });
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