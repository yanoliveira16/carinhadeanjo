package com.example.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tela_de_carregamento extends AppCompatActivity {
    public static String nnomePai, nnomeAluno, tturma, nnomeProfe;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("USU");
    DatabaseReference myRef2 = database.child("P3");
    DatabaseReference myRef3 = database.child("P4");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_carregamento);

        final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
        a1.setText("Estamos verificando suas informações nos servidores...");

        myRef.child(login_or_register.id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nn = dataSnapshot.getValue(String.class);
                if (nn.contains("P1") == true) {
                    String aa = "OPA! \n Cadastro ainda não aprovado. \n Aguarde e volte mais tarde!";
                    final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
                    a1.setText(aa);

                } else if (nn.contains("P3") == true) {
                    carregamento3();


                } else if (nn.contains("P4") == true) {
                    carregamento2();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void carregamento2() {
        myRef3.child(login_or_register.id).child("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nnomeProfe = dataSnapshot.getValue(String.class);
                myRef3.child(login_or_register.id).child("turma").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tturma = dataSnapshot.getValue(String.class);

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void carregamento3() {
        myRef2.child(login_or_register.id).child("Nome Pai").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nnomePai = dataSnapshot.getValue(String.class);
                myRef2.child(login_or_register.id).child("Nome Aluno").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        nnomeAluno = dataSnapshot.getValue(String.class);
                        myRef2.child(login_or_register.id).child("Turma").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                tturma = dataSnapshot.getValue(String.class);
                                Intent intent = new Intent(getBaseContext(), tela_do_aluno.class);
                                startActivity(intent);
                            }





                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void sair_click(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getBaseContext(), login_or_register.class);
        startActivity(intent);

    }



}




