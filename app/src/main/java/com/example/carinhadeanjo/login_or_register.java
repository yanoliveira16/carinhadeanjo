package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_or_register extends AppCompatActivity {

    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            SharedPreferences sharedPref1 = getPreferences(Context.MODE_PRIVATE);
            int onde_parou = sharedPref1.getInt("onde", 0);
            id=user.getUid();
            Log.d("Aqui", "aqui porra " +onde_parou);
            if (onde_parou == 1){
                chamar_termo();
            }else if (onde_parou == 2){
                chamar_foto();
            }else{
                chamar_carregar();
            }

        }else{
            View a1=findViewById(R.id.progressBar2);
            a1.setVisibility(View.INVISIBLE);

            View a2=findViewById(R.id.imageView12);
            a2.setVisibility(View.INVISIBLE);
        }
    }

    public void chamar_termo(){
        Intent intent = new Intent(getBaseContext(), termos_de_uso.class);
        startActivity(intent);
    }
    public void chamar_foto(){
        Intent intent = new Intent(getBaseContext(), enviar_foto.class);
        startActivity(intent);
    }
    public void chamar_carregar(){
        Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
        startActivity(intent);
    }

    public void registrar_click(View view){
        Intent intent = new Intent(getBaseContext(), registrar.class);
        startActivity(intent);
    }
    public void entra_click(View view){
        Intent intent = new Intent(getBaseContext(), entra.class);
        startActivity(intent);
    }



}
