package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.lyft.android.scissors.CropView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class entra extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String email,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        }

   //public void termos_click (View view){
     //   Intent intent = new Intent(getBaseContext(), termos_de_uso.class);
       // startActivity(intent);
   // }


    public void entrar_click(View view) {
        View a1=findViewById(R.id.entrar2);
        View a2=findViewById(R.id.imageView11);
        View a3=findViewById(R.id.progressBar3);

        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.VISIBLE);
        a3.setVisibility(View.VISIBLE);

        final EditText et2 = (EditText) findViewById(R.id.email3);
        email = et2.getText().toString();

        final EditText et3 = (EditText) findViewById(R.id.senha2);
        senha = et3.getText().toString();

        if (email.isEmpty() == true || email == "" || email == null) {
            errormsg = "Você precisa colocar um e-mail";
            erro();
        } else if (senha.isEmpty() == true || senha == "" || senha == null) {
            errormsg = "Você precis colocar uma senha";
            erro();


        } else {
            segundaParte();

        }


    }

    public void segundaParte() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            login_or_register.id =user.getUid();
                                Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                                startActivity(intent);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            errormsg="O e-mail ou a senha não foram digitados da maneira correta. Verifique as informações e tente novamente.";
                            erro();
                           //updateUI(null);
                        }

                        // ...
                    }
                });

    }
    String msg="";

public void esqueci_senha(View view) {

    AlertDialog.Builder builder = new AlertDialog.Builder(entra.this);
    builder.setTitle("Esqueci minha senha");

    // Set up the input
    final EditText input = new EditText(entra.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    builder.setView(input);

// Set up the buttons
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            email = input.getText().toString();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String emailAddress = email;
            if (emailAddress != null && emailAddress.contains("@") == true && emailAddress.contains(".") == true){
                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    AlertDialog alertDialog = new AlertDialog.Builder(entra.this).create();
                                    alertDialog.setTitle("E-mail enviado!");
                                    alertDialog.setMessage(msg);
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }else{
                                    errormsg="ERRO: Verifique seu e-mail e tente novamenta!";
                                    erro();
                                }
                            }
                        });
            }else{
                errormsg="Formato de e-mail incorreto!";
                erro();
            }
        }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });

    builder.show();
}


    String errormsg="";

    public void erro(){

        View a1=findViewById(R.id.entrar2);
        View a2=findViewById(R.id.imageView11);
        View a3=findViewById(R.id.progressBar3);

        a1.setVisibility(View.VISIBLE);
        a2.setVisibility(View.INVISIBLE);
        a3.setVisibility(View.INVISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(entra.this).create();
        alertDialog.setTitle("OPS");
        alertDialog.setMessage(errormsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void acessar_QRCode(View view){
        View a1=findViewById(R.id.entrar2);
        View a2=findViewById(R.id.imageView11);
        View a3=findViewById(R.id.progressBar3);

        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.VISIBLE);
        a3.setVisibility(View.VISIBLE);

        final EditText et2 = (EditText) findViewById(R.id.email3);
        email = et2.getText().toString();

        final EditText et3 = (EditText) findViewById(R.id.senha2);
        senha = et3.getText().toString();

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
            try {
                imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                int rotationDegree = 0;
                InputImage image = InputImage.fromBitmap(bitmap, rotationDegree);
                scanBarcodes(image);
            } catch (IOException e) {
                e.printStackTrace();
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

    private void scanBarcodes(InputImage image) {
        // [START set_detector_options]
        BarcodeScannerOptions options =
                new BarcodeScannerOptions.Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE,
                                Barcode.FORMAT_AZTEC)
                        .build();
        BarcodeScanner scanner = BarcodeScanning.getClient();
        Task<List<Barcode>> result = scanner.process(image)
                .addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                    @Override
                    public void onSuccess(List<Barcode> barcodes) {
                        for (Barcode barcode: barcodes) {
                            Rect bounds = barcode.getBoundingBox();
                            Point[] corners = barcode.getCornerPoints();

                            String rawValue = barcode.getRawValue();

                            int valueType = barcode.getValueType();
                            // See API reference for complete list of supported types
                            switch (valueType) {
                                case Barcode.TYPE_TEXT:
                                    String codigo = barcode.getDisplayValue().toString();
                                    login_or_register.id = codigo;
                                    Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                                    startActivity(intent);
                                    break;
                                default:
                                    errormsg = "Não foi possível fazer a leitura do QRCode na imagem que você escolheu. Tente novamente.";
                                    erro();
                                    break;
                            }
                        }
                        // [END get_barcodes]
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Task failed with an exception
                        // ...
                    }
                });
    }

}
