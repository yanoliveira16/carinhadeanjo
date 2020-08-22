package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class agenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
    }

    String p1 = "";
    public void enviar(View view){
        Switch s1 = findViewById(R.id.s1);
        Switch s2 = findViewById(R.id.s2);
        if (s1.isChecked() == true){
            p1 = "Português | ";
        }
        if (s2.isChecked() == true){
            p1 += "Literatura | ";
        }
    }
}
