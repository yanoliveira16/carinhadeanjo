package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class entrar_dois extends AppCompatActivity {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef2 = database.child("P3");
    private FirebaseAuth mAuth;
    String nnome, ssenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_dois);
    }

    public void entranovoclicou_click(View view){
        final EditText et2 = (EditText) findViewById(R.id.entrar_dois);
        String id_entrar = et2.getText().toString();

        View a1=findViewById(R.id.car1);
        View a2=findViewById(R.id.car2);

        a1.setVisibility(View.VISIBLE);
        a2.setVisibility(View.VISIBLE);

        myRef2.child(id_entrar).child("ID").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id_puxou = dataSnapshot.getValue(String.class);
                if (id_puxou != null){
                    login_or_register.id = id_puxou;
                    myRef2.child(id_entrar).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            nnome = dataSnapshot.getValue(String.class);
                            if (id_puxou != null){
                                myRef2.child(id_entrar).child("senha").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        ssenha = dataSnapshot.getValue(String.class);
                                        if (id_puxou != null){
                                            processo2();
                                        }else{
                                            errormsg="ERRO: Sua conta não esta apta para o login com o código.";
                                            erro();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }else{
                                errormsg="ERRO: Sua conta não esta apta para o login com o código.";
                                erro();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    errormsg="ERRO: ID incorreto!";
                    erro();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void processo2(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(nnome, ssenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            login_or_register.id =user.getUid();
                            Intent intent = new Intent(getBaseContext(), tela_de_carregamento.class);
                            startActivity(intent);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            errormsg="O e-mail ou a senha não foram digitados da maneira correta. Verifique as informações e tente novamente.";
                            erro();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    String errormsg="";

    public void erro(){
        View a1=findViewById(R.id.car1);
        View a2=findViewById(R.id.car2);

        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.INVISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(entrar_dois.this).create();
        alertDialog.setTitle("OPS");
        alertDialog.setMessage(errormsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}