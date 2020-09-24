package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_or_register extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("P5").child("onde_parou");

    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            id=user.getUid();
            myRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String nn = dataSnapshot.getValue(String.class);
                    if (nn.contains("01") == true) {
                        chamar_termo();
                    } else if (nn.contains("02") == true) {
                        chamar_foto();
                    } else {
                        chamar_carregar();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            View a1=findViewById(R.id.progressBar2);
            a1.setVisibility(View.INVISIBLE);

            View a2=findViewById(R.id.imageView12);
            a2.setVisibility(View.INVISIBLE);

            View a3=findViewById(R.id.imageView27);
            a3.setVisibility(View.INVISIBLE);
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
