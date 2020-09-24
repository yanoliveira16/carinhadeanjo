package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class termos_de_uso extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("P5").child("onde_parou");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso);

        myRef.child(login_or_register.id).setValue("01");
    }
    public void aceitar_click(View view) {
        Intent intent = new Intent(getBaseContext(), enviar_foto.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        AlertDialog alertDialog = new AlertDialog.Builder(termos_de_uso.this).create();
        alertDialog.setTitle("OPA");
        alertDialog.setMessage("Você não pode voltar pois já começou seu processo de cadastro!\nLeia e aceite os termos para continuar.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
