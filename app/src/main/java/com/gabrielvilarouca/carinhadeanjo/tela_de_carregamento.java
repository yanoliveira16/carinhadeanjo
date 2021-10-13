package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static String nnomePai, nnomeAluno, tturma, nnomeProfe, qual, onClick19, key_feed, tem_coordena, pdf_qualfile, versao;
    public static Integer faltar_no_total;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("USU");
    DatabaseReference myRef2 = database.child("P3");
    DatabaseReference myRef3 = database.child("P4");
    DatabaseReference myRef4 = database.child("P5");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_carregamento);

        View a3=findViewById(R.id.button4);
        a3.setVisibility(View.INVISIBLE);

        myRef4.child("version_andr").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                versao = dataSnapshot.getValue(String.class);
                primeira_chamada();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* SharedPreferences sharedPref1 = getPreferences(Context.MODE_PRIVATE);
        int onde_parou = sharedPref1.getInt("onde", 0);
        if (onde_parou == 1){
            Intent intent = new Intent(getBaseContext(), termos_de_uso.class);
            startActivity(intent);
        }else if (onde_parou == 2){
            Intent intent = new Intent(getBaseContext(), enviar_foto.class);
            startActivity(intent);
        }else{
            primeira_chamada();
        }*/

    }

    public void primeira_chamada(){
        /*SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("porra", 0);
        editor.apply();*/


        Log.d("AQUI","CARREGANDO " + login_or_register.id);

        final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
        a1.setText("Estamos verificando suas informações...");

        if(login_or_register.id == null){
            String aa = "Cadastro não encontrado!\nEntre em contato com a escola.";
            a1.setText(aa);
            View a2=findViewById(R.id.progressBar);
            a2.setVisibility(View.INVISIBLE);

            View a3=findViewById(R.id.button4);
            a3.setVisibility(View.VISIBLE);
        }else{
            call();
        }
    }

    public void call(){
        myRef.child(login_or_register.id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nn = dataSnapshot.getValue(String.class);
                if(nn == null){
                    String aa = "Cadastro não encontrado!\nEntre em contato com a escola.";
                    final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
                    a1.setText(aa);
                    View a2=findViewById(R.id.progressBar);
                    a2.setVisibility(View.INVISIBLE);

                    View a3=findViewById(R.id.button4);
                    a3.setVisibility(View.VISIBLE);
                }else if (nn.contains("P1") == true) {
                    String aa = "OPA! \n Cadastro ainda não aprovado.\nAguarde e volte mais tarde!";
                    final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
                    a1.setText(aa);
                    View a2=findViewById(R.id.progressBar);
                    a2.setVisibility(View.INVISIBLE);

                    View a3=findViewById(R.id.button4);
                    a3.setVisibility(View.VISIBLE);

                } else if (nn.contains("P3") == true) {
                    carregamento3();
                } else if (nn.contains("P4") == true) {
                    carregamento2();
                }else if (nn.contains("TODOS") == true) {
                    tem_coordena = "tem";
                    qual = "1";
                    Intent intent = new Intent(getBaseContext(), coordena.class);
                    startActivity(intent);
                }else{
                    String aa = "ERRO\nNão encontramos suas informações!";
                    final TextView a1 = (TextView) findViewById(R.id.texto_carregamento);
                    a1.setText(aa);
                    View a2=findViewById(R.id.progressBar);
                    a2.setVisibility(View.INVISIBLE);

                    View a3=findViewById(R.id.button4);
                    a3.setVisibility(View.VISIBLE);
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
                        qual = "1";
                        tturma = dataSnapshot.getValue(String.class);
                        Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
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
                                qual = "2";
                                tturma = dataSnapshot.getValue(String.class);

                                myRef2.child(login_or_register.id).child("faltas").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        faltar_no_total = dataSnapshot.getValue(Integer.class);
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed(){
        AlertDialog alertDialog = new AlertDialog.Builder(tela_de_carregamento.this).create();
        alertDialog.setTitle("OPA");
        alertDialog.setMessage("Você não pode voltar pois estamos fazendo o processamento dos seus dados");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void sair_click(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getBaseContext(), login_or_register.class);
        startActivity(intent);

    }



}




