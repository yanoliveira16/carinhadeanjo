package com.example.carinhadeanjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class entra extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String email,senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        }

   //public void termos_click (View view){
     //   Intent intent = new Intent(getBaseContext(), termos_de_uso.class);
       // startActivity(intent);
   // }


    public void entrar_click(View view) {
        View a1=findViewById(R.id.entrar2);
        View a2=findViewById(R.id.imageView11);
        View a3=findViewById(R.id.progressBar3);

        a1.setVisibility(View.INVISIBLE);
        a2.setVisibility(View.VISIBLE);
        a3.setVisibility(View.VISIBLE);

        final EditText et2 = (EditText) findViewById(R.id.email3);
        email = et2.getText().toString();

        final EditText et3 = (EditText) findViewById(R.id.senha2);
        senha = et3.getText().toString();

        if (email.isEmpty() == true || email == "" || email == null) {
            errormsg = "Você precisa colocar um e-mail";
            erro();
        } else if (senha.isEmpty() == true || senha == "" || senha == null) {
            errormsg = "Você precis colocar uma senha";
            erro();


        } else {
            segundaParte();

        }


    }

    public void segundaParte() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, senha)
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
                            errormsg="Erro, e-mail ou senha incorretos!";
                            erro();
                           //updateUI(null);
                        }

                        // ...
                    }
                });

    }




    String errormsg="";

    public void erro(){

        View a1=findViewById(R.id.entrar2);
        View a2=findViewById(R.id.imageView11);
        View a3=findViewById(R.id.progressBar3);

        a1.setVisibility(View.VISIBLE);
        a2.setVisibility(View.INVISIBLE);
        a3.setVisibility(View.INVISIBLE);

        AlertDialog alertDialog = new AlertDialog.Builder(entra.this).create();
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
