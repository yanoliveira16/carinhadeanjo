package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
            id=user.getUid();
            Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
            startActivity(intent);

        }else{
            View a1=findViewById(R.id.progressBar2);
            a1.setVisibility(View.INVISIBLE);

            View a2=findViewById(R.id.imageView12);
            a2.setVisibility(View.INVISIBLE);
        }
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
