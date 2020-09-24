package com.example.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registrar extends AppCompatActivity {
    private FirebaseAuth mAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("P1");
    DatabaseReference myRef2 = database.getReference("USU");


    String email,senha,nomeDoResp,nomeDoAluno,turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        View a1=findViewById(R.id.carr1);
        a1.setVisibility(View.INVISIBLE);

        View a2=findViewById(R.id.carr2);
        a2.setVisibility(View.INVISIBLE);
    }

    public void cadastrar_click(final View view){
        View a1=findViewById(R.id.carr1);
        a1.setVisibility(View.VISIBLE);

        View a2=findViewById(R.id.carr2);
        a2.setVisibility(View.VISIBLE);

        final EditText et2 = (EditText) findViewById(R.id.email);
        email = et2.getText().toString();

        final EditText et3 = (EditText) findViewById(R.id.senha);
         senha = et3.getText().toString();

        final EditText et4 = (EditText) findViewById(R.id.nome_do_resp);
        nomeDoResp = et4.getText().toString();

        final EditText et5 = (EditText) findViewById(R.id.nome_do_aluno);
        nomeDoAluno = et5.getText().toString();

        final EditText et6 = (EditText) findViewById(R.id.turma);
        turma = et6.getText().toString();

        if (email.isEmpty()==true || email=="" || email==null){
        errormsg="Você precisa colocar um e-mail";
        erro();
        } else if (email.contains("@")==false){
            errormsg="Por favor, digite um e-mail valido.";
            erro();
        }else if (senha.isEmpty()==true || senha=="" || senha==null){
            errormsg="Você precis colocar uma senha";
            erro();
        }else if (senha.length() <=6 ){
            errormsg="Sua senha deverá conter mais de 6 digitos!";
            erro();
        }else if (nomeDoResp.isEmpty()==true || nomeDoResp=="" || nomeDoResp==null){
            errormsg="Por favor, digite o nome do responsável!";
            erro();
        }else if (nomeDoAluno.isEmpty()==true || nomeDoAluno=="" || nomeDoAluno==null){
        errormsg="Por favor, digite o nome do aluno!";
        erro();
        }else if (turma.isEmpty()==true || turma=="" || turma==null){
            errormsg="Por favor, digite a turma!";
            erro();



    }else {
            segundaParte();

        }

    }
public void segundaParte(){


        mAuth = FirebaseAuth.getInstance();
    mAuth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {




                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {



                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        String uid = user.getUid();
                        login_or_register.id = uid;
                        myRef.child(uid).child("Nome Pai").setValue(nomeDoResp);
                        myRef.child(uid).child("Nome Aluno").setValue(nomeDoAluno);
                        myRef.child(uid).child("Email").setValue(email);
                        myRef.child(uid).child("Turma").setValue(turma);
                        myRef.child(uid).child("ID").setValue(uid);
                        myRef2.child(uid).setValue("P1");


                        Intent intent = new Intent(getBaseContext(), termos_de_uso.class);
                        startActivity(intent);

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

String errormsg="";

    public void erro(){
        View a1=findViewById(R.id.carr1);
        a1.setVisibility(View.INVISIBLE);

        View a2=findViewById(R.id.carr2);
        a2.setVisibility(View.INVISIBLE);
        AlertDialog alertDialog = new AlertDialog.Builder(registrar.this).create();
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
