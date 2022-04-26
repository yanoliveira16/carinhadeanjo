package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class gerenciar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar);
    }

    public void novo_aluno(View view){
        Intent intent = new Intent(getBaseContext(), novo_aluno.class);
        startActivity(intent);
    }

    public void teste(View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(gerenciar.this);
        builder1.setMessage("RECURSO EM DESENVOLVIMENTO\nEstamos com muitas novidades, e em breve iremos liberar mais recursos.");
        builder1.setCancelable(true);
    }
}