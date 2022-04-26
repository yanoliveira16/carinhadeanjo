package com.gabrielvilarouca.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class novo_aluno extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3");
    DatabaseReference myRef2 = database.child("USU");
    DatabaseReference myRef_feed = database.child("P2").child(tela_de_carregamento.tturma);
    String login, senha, nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_aluno);
    }

    public void clique_criar(View view){
        View a1=findViewById(R.id.img_car);
        a1.setVisibility(View.VISIBLE);

        Integer n1 = new Random().nextInt(6);
        if (n1 == 0){
            login = "a" + n1;
            senha = "g" + n1;
        }else if (n1 == 1){
            login = "b" + n1;
            senha = "h" + n1;
        }else if (n1 == 2){
            login = "c" + n1;
            senha = "i" + n1;
        }else if (n1 == 3){
            login = "d" + n1;
            senha = "j" + n1;
        }else if (n1 == 4){
            login = "e" + n1;
            senha = "k" + n1;
        }else {
            login = "f" + n1;
            senha = "m" + n1;
        }
        processo_2();
    }

    public void processo_2(){
        Integer n2 = new Random().nextInt(6);
        if (n2 == 0){
            login += "a" + n2;
            senha += "g" + n2;
        }else if (n2 == 1){
            login += "b" + n2;
            senha += "h" + n2;
        }else if (n2 == 2){
            login += "c" + n2;
            senha += "i" + n2;
        }else if (n2 == 3){
            login += "d" + n2;
            senha += "j" + n2;
        }else if (n2 == 4){
            login += "e" + n2;
            senha += "k" + n2;
        }else {
            login += "f" + n2;
            senha += "m" + n2;
        }
        processo_3();
    }

    public void processo_3(){
        Integer n3 = new Random().nextInt(999);
        login += n3 + "@carinhadeanjo.com";
        senha += n3 + "escola";

        final EditText et2 = (EditText) findViewById(R.id.nome_novo_aluno);
        nome = et2.getText().toString();

        Log.d("AQUI", "login " + login +" senha " +senha +" nome " + nome);

        processo_4();
    }

    public void processo_4(){
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(login, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            //login_or_register.id = uid;
                            myRef.child(uid).child("Nome Aluno").setValue(nome);
                            myRef.child(uid).child("email").setValue(login);
                            myRef.child(uid).child("senha").setValue(senha);
                            myRef.child(uid).child("Turma").setValue(tela_de_carregamento.tturma);
                            myRef.child(uid).child("ID").setValue(uid);
                            myRef.child(uid).child("faltas").setValue(0);
                            myRef.child(uid).child("AGENDAValor").setValue(0);
                            myRef2.child(uid).setValue("P3");

                            TextView a222 = (EditText) findViewById(R.id.infos_login);
                            a222.setText("NOVO ALUNO CRIADO\nInformações:\nlogin: " +login +"\nsenha: " +senha +"\nid: " +uid);

                            View a1=findViewById(R.id.img_car);
                            a1.setVisibility(View.INVISIBLE);

                            View a2=findViewById(R.id.textView39);
                            a2.setVisibility(View.INVISIBLE);

                            View a3=findViewById(R.id.nome_novo_aluno);
                            a3.setVisibility(View.INVISIBLE);

                            View a4=findViewById(R.id.button30);
                            a4.setVisibility(View.INVISIBLE);

                            View a5=findViewById(R.id.infos_login);
                            a5.setVisibility(View.VISIBLE);

                            enviar_ao_main_feed();

                            // updateUI(user);
                        } else {
                            errormsg="Erro ao registrar, tente novamente!";
                            erro();
                            //  updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void enviar_ao_main_feed(){
        myRef_feed.child("TOTAL_FEED").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer nn = dataSnapshot.getValue(Integer.class);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                if (nn == null){
                    nn = 1;
                    myRef_feed.child("TOTAL_FEED").setValue(nn);
                }else{
                    if(nn >= 95){
                        nn = 1;
                        myRef_feed.child("FEED").removeValue();
                        myRef_feed.child("FEED").child(nn +" - serve").setValue(currentDateandTime + " - SERVIDOR: Feed limpo!");
                        nn += 1;
                        myRef_feed.child("TOTAL_FEED").setValue(nn);
                    }else{
                        nn += 1;
                        myRef_feed.child("TOTAL_FEED").setValue(nn);
                    }
                }

                myRef_feed.child("FEED").child(nn +" - profe - ").setValue(currentDateandTime + " - ALUNO: O novo aluno "+nome+" foi adicionado a sua turma!" );

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    String errormsg="";
    public void erro(){
        View a1=findViewById(R.id.img_car);
        a1.setVisibility(View.INVISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(novo_aluno.this).create();
        alertDialog.setTitle("ERRO!");
        alertDialog.setMessage(errormsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}