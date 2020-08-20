package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class termos_de_uso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos_de_uso);

    }
    public void aceitar_click(View view) {
        Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
        startActivity(intent);
    }
}
