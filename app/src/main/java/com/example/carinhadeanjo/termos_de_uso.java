package com.example.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class termos_de_uso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("porra", 1);
        editor.apply();


        SharedPreferences sharedPref1 = getPreferences(Context.MODE_PRIVATE);
        int onde_parou = sharedPref1.getInt("onde", 0);
        Log.d("Aqui", "aqui porra " +onde_parou);

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
