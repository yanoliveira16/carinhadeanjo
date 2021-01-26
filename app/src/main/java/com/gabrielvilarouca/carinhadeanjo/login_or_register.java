package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_or_register extends AppCompatActivity {

    public static String id, versao;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef4 = database.child("P5");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("USER", "\n\n\n\n\n\n\naqui" + user.getUid());
        if (user != null) {
            id=user.getUid();
            myRef4.child("version_andr").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    versao = dataSnapshot.getValue(String.class);
                    Log.d("VERSION", "\n\n\n\n\n\n\naqui" + versao);
                    Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                    startActivity(intent);
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

    public void registrar_click(View view){
        Intent intent = new Intent(getBaseContext(), registrar.class);
        startActivity(intent);
    }
    public void entra_click(View view){
        Intent intent = new Intent(getBaseContext(), entra.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        AlertDialog alertDialog = new AlertDialog.Builder(login_or_register.this).create();
        alertDialog.setTitle("OPA");
        alertDialog.setMessage("Você não pode voltar!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
