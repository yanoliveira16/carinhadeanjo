package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class view_pdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        /*WebView webView = findViewById(R.id.webview);
        webView.loadUrl(tela_de_carregamento.url_pdf);*/

        StorageReference ref = FirebaseStorage.getInstance().getReference().child(tela_de_carregamento.pdf_qualfile);
        try {
            final File localFile = File.createTempFile("application", "pdf");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    View a1=findViewById(R.id.pg_pdf);
                    a1.setVisibility(View.INVISIBLE);
                    PDFView pdfView = findViewById(R.id.pdfView);
                    pdfView.fromFile(localFile).load();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e.getMessage().contains("not exist at location")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(view_pdf.this);
                        builder1.setMessage("ERRO\nPDF ainda não enviado\nAguarde ou entre em contato com a escola!");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }else{
                        Toast.makeText(view_pdf.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}