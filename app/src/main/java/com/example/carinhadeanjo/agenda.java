package com.example.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class agenda extends AppCompatActivity {

    ArrayList<String> feed = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public static String onClick4;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = database.child("P3").child(tela_do_aluno_prof.id_aluno).child("Agenda");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ImageView myImage = (ImageView) findViewById(R.id.imageView17);
        myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image,400));

        TextView a1 = (TextView) findViewById(R.id.nome_aluno_agenda);
        a1.setText(tela_de_alunos.onClick3);

    }

    String errormsg = "";

    public void erro() {

        AlertDialog alertDialog = new AlertDialog.Builder(agenda.this).create();
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

    public void enviar(View view) {
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
        if (s5.isChecked() == true) {
            atvs += "História | ";
        }
        if (s6.isChecked() == true) {
            atvs += "Geografia | ";
        }
        if (s7.isChecked() == true) {
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
    }

    String atvs2 = "";
    Switch s24;
    Switch s25;

    public void enviar2(View view) {

        s24 = findViewById(R.id.s24);
        s25 = findViewById(R.id.s25);


        if (s24.isChecked()==true) s25.setChecked(false);{
            atvs2 = "Realizou com facilidade";
        }

        if (s25.isChecked()==true)   s24.setChecked(false); {

            atvs2 = "Realizou com dificuldade";
        }

    }
        String comportamento = "";
        Switch s26;
        Switch s27;
        Switch s28;
        Switch s29;

        public void enviar3(View view){

            s26 = findViewById(R.id.s26);
            s27 = findViewById(R.id.s27);
            s28 = findViewById(R.id.s28);
            s29 = findViewById(R.id.s29);

           /* s26.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s27.setChecked(false);
                        }
                    });
            s27.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s26.setChecked(false);
                        }
                    });
            s28.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s29.setChecked(false);
                        }
                    });
            s29.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            s28.setChecked(false);
                        }
                    });*/

            if (s26.isChecked() == true) {
                comportamento = "Tranquilo";
            } else if (s27.isChecked() == true) {
                comportamento = "Obediente";
            }
            else if (s28.isChecked() == true) {
                comportamento = "Conversou muito";
            } else if (s29.isChecked() == true) {
                comportamento = "Irritado";
            }

        }


        //ESSA É A ÚLTIMA PARTE.
    //AQUI VOCÊ PEGA AS STRINGS CRIADAS E ENVIA TUDO AO SERVIDOR
    //CADA PARTE TEM UMA KEY (CHILD) DIFERENTE!
        public void enviar_servidor(){

            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-yyyy");
            String currentDateandTime2 = sdf2.format(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy as HH:mm");
            String currentDateandTime = sdf.format(new Date());


            myRef.child(currentDateandTime2).child(currentDateandTime).child("atvs").setValue(atvs);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("atvs2").setValue(atvs2);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("comportamento").setValue(comportamento);
        }


    }



