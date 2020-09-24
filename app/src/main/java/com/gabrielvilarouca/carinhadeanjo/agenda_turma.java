package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class agenda_turma extends AppCompatActivity {
    public static String onClick4;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P5").child(tela_de_carregamento.tturma).child("AgendaTurma");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_turma);
        ArrayList<String> feed = new ArrayList<>();
        ArrayAdapter<String> arrayAdapter;
        TextView a1 = (TextView) findViewById(R.id.nome_aluno_agenda4);
        a1.setText(tela_de_carregamento.tturma);
    }

    String errormsg = "";

    public void erro() {
        AlertDialog alertDialog = new AlertDialog.Builder(agenda_turma.this).create();
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


    String atvs = "";

    public void enviar1(View view) {
        //View a1 = findViewById(R.id.progressBar4);
        //a1.setVisibility(View.VISIBLE);
        //View a2 = findViewById(R.id.imageView25);
       // a2.setVisibility(View.VISIBLE);

        Switch s1 = findViewById(R.id.s1);
        Switch s2 = findViewById(R.id.s2);
        Switch s3 = findViewById(R.id.s3);
        Switch s4 = findViewById(R.id.s4);
        Switch s5 = findViewById(R.id.s5);
        Switch s6 = findViewById(R.id.s6);
        Switch s7 = findViewById(R.id.s7);
        Switch s8 = findViewById(R.id.s8);
        Switch s9 = findViewById(R.id.s9);
        Switch s10 = findViewById(R.id.s10);
        Switch s11 = findViewById(R.id.s11);
        Switch s12 = findViewById(R.id.s12);
        Switch s13 = findViewById(R.id.s13);
        Switch s14 = findViewById(R.id.s14);
        Switch s15 = findViewById(R.id.s15);
        Switch s16 = findViewById(R.id.s16);
        Switch s17 = findViewById(R.id.s17);
        Switch s18 = findViewById(R.id.s18);
        Switch s19 = findViewById(R.id.s19);
        Switch s20 = findViewById(R.id.s20);
        Switch s21 = findViewById(R.id.s21);
        Switch s22 = findViewById(R.id.s22);
        Switch s23 = findViewById(R.id.s23);

        if (s1.isChecked() == true) {
            atvs = "Português | ";
        }
        if (s2.isChecked() == true) {
            atvs += "Linguagem | ";
        }
        if (s3.isChecked() == true) {
            atvs += "Matemática | ";
        }
        if (s4.isChecked() == true) {
            atvs += "Ciências | ";
        }
        if (s7.isChecked() == true) {
            atvs += "História | ";
        }
        if (s6.isChecked() == true) {
            atvs += "Geografia | ";
        }
        if (s5.isChecked() == true) {
            final EditText et2 = (EditText) findViewById(R.id.outro_texto);
            String nn = et2.getText().toString();
            if (nn.equals(null) == true) {
                errormsg = "Você precisa escrever ao menos uma atividade em Outro!";
                erro();
            } else {
                atvs += nn + " | ";
            }

        }
        if (s8.isChecked() == true) {
            atvs += "Natureza e Sociedade | ";
        }
        if (s9.isChecked() == true) {
            atvs += "Judo | ";
        }
        if (s10.isChecked() == true) {
            atvs += "Ballet | ";
        }
        if (s11.isChecked() == true) {
            atvs += "Inglês | ";
        }
        if (s12.isChecked() == true) {
            atvs += "Música | ";
        }
        if (s13.isChecked() == true) {
            atvs += "Teatro  | ";
        }
        if (s14.isChecked() == true) {
            atvs += "Recreação Dirigida | ";
        }
        if (s15.isChecked() == true) {
            atvs += "Coordenação Motora | ";
        }
        if (s16.isChecked() == true) {
            atvs += "Parque | ";
        }
        if (s17.isChecked() == true) {
            atvs += "Brinquedoteca | ";
        }
        if (s18.isChecked() == true) {
            atvs += "Educação Artística | ";
        }
        if (s19.isChecked() == true) {
            atvs += "Cozinha Experimental | ";
        }
        if (s20.isChecked() == true) {
            atvs += "Hora da Novidade | ";
        }
        if (s21.isChecked() == true) {
            atvs += "Jogos | ";
        }
        if (s22.isChecked() == true) {
            atvs += "Videoteca | ";
        }
        if (s23.isChecked() == true) {
            atvs += "Contação de História  | ";
        }

        enviar2();
    }

    String dever = "";
    Switch s30;
    Switch s31;
    Switch s33;


    public void enviar2() {

        s30 = findViewById(R.id.s30);
        s31 = findViewById(R.id.s31);
        s33 = findViewById(R.id.s33);


        if (s30.isChecked() == true) {
            dever = "Em folha";
        }
        if (s31.isChecked() == true) {
            final EditText et2 = (EditText) findViewById(R.id.outro_texto5);
            String nn = et2.getText().toString();
            if (nn.equals(null) == true) {
                errormsg = "Você precisa escrever ao menos um caderno";
                erro();
            } else {
                dever += nn + " | ";
            }

            if (s33.isChecked() == true) {
                final EditText et3 = (EditText) findViewById(R.id.outro_texto4);
                String nn2 = et3.getText().toString();
                if (nn.equals(null) == true) {
                    errormsg = "Você precisa escrever ao menos um livro";
                    erro();
                } else {
                    dever += nn + " | ";

                    if (s33.isChecked() == true) {
                        final EditText et4 = (EditText) findViewById(R.id.outro_texto2);
                        String nn3 = et3.getText().toString();
                        if (nn.equals(null) == true) {
                            errormsg = "Você precisa escrever ao menos uma Página";
                            erro();
                        } else {
                            dever += nn + " | ";
                        }
                    }
                }
            }
        }
        enviar3();
    }


    String aviso = "";

    public void enviar3() {
        final EditText et2 = (EditText) findViewById(R.id.outro_texto3);
        if (et2.equals(null) == false) {
            String nn = et2.getText().toString();
            aviso += nn + "";
        }
        enviar_servidor();
    }

    public void enviar_servidor(){

        myRef.child("atvs").setValue(atvs);
        myRef.child("dever").setValue(dever);
        myRef.child("aviso").setValue(aviso);

        Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
        startActivity(intent);
    }



}