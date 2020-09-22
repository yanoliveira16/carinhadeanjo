package com.example.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
    DatabaseReference myRef2 = database.child("P3").child(tela_do_aluno_prof.id_aluno).child("agendaValor");
    DatabaseReference myRef3 = database.child("P5").child(tela_de_carregamento.tturma).child("AgendaTurma");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ImageView myImage = (ImageView) findViewById(R.id.imageView17);
        myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image, 400));

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

    String falta = "";
    Switch s;
    public void enviar0(){
        Switch sFalta = findViewById(R.id.s);
        if (sFalta.isChecked() == true) {
            falta = "PRESENTE";
        }
        enviar2();
    }



    public void enviar(View view) {
        View a1=findViewById(R.id.progressBar4);
        a1.setVisibility(View.VISIBLE);
        View a2=findViewById(R.id.imageView25);
        a2.setVisibility(View.VISIBLE);

        enviar0();
    }

    String atvs2 = "";
    Switch s24;
    Switch s25;

    public void enviar2() {

        s24 = findViewById(R.id.s24);
        s25 = findViewById(R.id.s25);


        if (s24.isChecked() == true) s25.setChecked(false);
        {
            atvs2 = "Realizou com facilidade";
        }

        if (s25.isChecked() == true) s24.setChecked(false);
        {

            atvs2 = "Realizou com dificuldade";
        }

        enviar3();
    }

    String comportamento = "";
    Switch s26;
    Switch s27;
    Switch s28;
    Switch s29;

    public void enviar3() {

        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);


        if (s26.isChecked() == true) {
            comportamento = "Tranquilo";
        } else if (s27.isChecked() == true) {
            comportamento = "Obediente";
        } else if (s28.isChecked() == true) {
            comportamento = "Conversou muito";
        } else if (s29.isChecked() == true) {
            comportamento = "Irritado";
        }

        enviar5();
    }



    String imedicacao = "";
    String iremedio = "";
    String idosagem = "";
    String ihorario = "";
    Switch siMed;
    Switch siRed;
    Switch siDos;
    Switch siHor;

    public void enviar5() {

        siMed = findViewById(R.id.imed);

        if (siMed.isChecked() == true) {
            imedicacao = "sim";
        }else{
            imedicacao = "não";
        }

        if (siMed.isChecked() == true) {
            final EditText et2 = (EditText) findViewById(R.id.iremedio2);
            String nn = et2.getText().toString();
            if (nn.equals(null) == true) {
                errormsg = "Você precisa escrever ao menos um Rémedio";
                erro();
            } else {
                iremedio += nn + "";
            }

            if (siMed.isChecked() == true) {
                final EditText et3 = (EditText) findViewById(R.id.idosagem2);
                String nn2 = et2.getText().toString();
                if (nn.equals(null) == true) {
                    errormsg = "Você precisa escrever ao menos uma Dosagem!";
                    erro();
                } else {
                    idosagem += nn + "";
                }

                if (siMed.isChecked() == true) {
                    final EditText et4 = (EditText) findViewById(R.id.ihorario2);
                    String nn3 = et2.getText().toString();
                    if (nn.equals(null) == true) {
                        errormsg = "Você precisa escrever ao menos um Horário!";
                        erro();
                    } else {
                        ihorario += nn + "";
                    }
                }
            }
        }

        enviar6();
    }

                String apresentou = "";
               Switch s36;
               Switch s37;
               Switch s38;
               Switch s39;

                public void enviar6() {
                    s36 = findViewById(R.id.s36);
                    s37 = findViewById(R.id.s37);
                    s38 = findViewById(R.id.s38);
                    s39 = findViewById(R.id.s39);

                    if (s36.isChecked() == true) {
                        apresentou = "Coriza";
                    } else if (s37.isChecked() == true) {
                        apresentou = "Tosse";
                    }
                    else if (s38.isChecked() == true) {
                        apresentou = "Vômito";
                    } else if (s39.isChecked() == true) {
                        apresentou = "Febre";
                    }

                    enviar16();
        }


    String ilanchematutino = "";
    Switch s32;
    Switch s34;
    Switch s72;

    public void enviar16() {
        s32 = findViewById(R.id.s32);
        s34 = findViewById(R.id.s34);
        s72 = findViewById(R.id.s72);

        if (s32.isChecked() == true) {
            ilanchematutino = "Comeu bem";
        } else if (s34.isChecked() == true) {
            ilanchematutino = "Comeu pouco";
        }
        else if (s72.isChecked() == true) {
            ilanchematutino = "Não comeu";
        }
        enviar7();
    }



    String ialmoço = "";
    Switch s40;
    Switch s41;
    Switch s42;

    public void enviar7() {
        s40 = findViewById(R.id.s40);
        s41 = findViewById(R.id.s41);
        s42 = findViewById(R.id.s42);

        if (s40.isChecked() == true) {
            ialmoço = "Comeu bem";
        } else if (s41.isChecked() == true) {
            ialmoço = "Comeu pouco";
        }
        else if (s42.isChecked() == true) {
            ialmoço = "Não comeu";
        }
        enviar9();
    }


    String ilanchevespertino = "";
    Switch s43;
    Switch s44;
    Switch s45;

    public void enviar9() {
        s43 = findViewById(R.id.s43);
        s44 = findViewById(R.id.s44);
        s45 = findViewById(R.id.s45);

        if (s43.isChecked() == true) {
            ilanchevespertino = "Comeu bem";
        } else if (s44.isChecked() == true) {
            ilanchevespertino = "Comeu pouco";
        } else if (s45.isChecked() == true) {
            ilanchevespertino = "Não comeu";
        }

        enviar10();
    }

    String ijanta = "";
    Switch s46;
    Switch s47;
    Switch s48;

    public void enviar10() {
        s46 = findViewById(R.id.s46);
        s47 = findViewById(R.id.s47);
        s48 = findViewById(R.id.s48);

        if (s46.isChecked() == true) {
            ijanta = "Comeu bem";
        } else if (s47.isChecked() == true) {
            ijanta = "Comeu pouco";
        } else if (s48.isChecked() == true) {
            ijanta = "Não comeu";
        }
        enviar11();
    }

    String isono = "";
    Switch s49;
    Switch s50;
    Switch s51;
    Switch s52;
    Switch s53;

    public void enviar11() {
        s49 = findViewById(R.id.s49);
        s50 = findViewById(R.id.s50);
        s51 = findViewById(R.id.s51);
        s52 = findViewById(R.id.s52);
        s53 = findViewById(R.id.s53);

        if (s49.isChecked() == true) {
            isono = "Não dormiu";
        } else if (s50.isChecked() == true) {
            isono = "Evacuação anormal";
        } else if (s51.isChecked() == true) {
            isono = "Uriniou pouco";
        } else if (s52.isChecked() == true) {
          isono = "Tomou pouca água";
        } else if (s53.isChecked() == true) {
            isono = "Não tomou banho";
        }
        enviar12();
    }

    String icomportamento = "";
    Switch s54;
    Switch s55;
    Switch s56;

    public void enviar12() {
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);

        if (s54.isChecked() == true) {
            icomportamento = "Tranquilo";
        } else if (s55.isChecked() == true) {
            icomportamento = "Obediente";
        } else if (s56.isChecked() == true) {
            icomportamento = "Irritado";
        }
        enviar13();
    }

    String iprovidenciar = "";
    Switch s57;
    Switch s58;
    Switch s59;
    Switch s60;
    Switch s61;
    Switch s62;
    Switch s63;
    Switch s64;

    public void enviar13() {
        s57 = findViewById(R.id.s57);
        s58 = findViewById(R.id.s58);
        s59 = findViewById(R.id.s59);
        s60 = findViewById(R.id.s60);
        s61 = findViewById(R.id.s61);
        s62 = findViewById(R.id.s62);
        s63 = findViewById(R.id.s63);
        s64 = findViewById(R.id.s64);


        if (s57.isChecked() == true) {
            iprovidenciar = "Toalha";
        } else if (s58.isChecked() == true) {
            iprovidenciar = "Lençol";
        } else if (s59.isChecked() == true) {
            iprovidenciar = "Fralda";
        } else if (s60.isChecked() == true) {
            iprovidenciar = "Sabonete Líquido";
        } else if (s61.isChecked() == true) {
            iprovidenciar = "Shampoo";
        } else if (s62.isChecked() == true) {
            iprovidenciar = "Condicionador";
        } else if (s63.isChecked() == true) {
            iprovidenciar = "Colônia";
        } else if (s64.isChecked() == true) {
            iprovidenciar = "Lenço Umidecido";
        }
        enviar14();
    }

    String iatv = "";
    Switch s65;
    Switch s66;
    Switch s67;
    Switch s68;
    Switch s69;
    Switch s70;
    Switch s71;

    public void enviar14() {
        s65= findViewById(R.id.s65);
        s66 = findViewById(R.id.s66);
        s67 = findViewById(R.id.s67);
        s68 = findViewById(R.id.s68);
        s69 = findViewById(R.id.s69);
        s70 = findViewById(R.id.s70);
        s71 = findViewById(R.id.s71);


        if (s65.isChecked() == true) {
            iatv = "Psicomotricidade";
        } else if (s66.isChecked() == true) {
            iatv = "Recreação";
        } else if (s67.isChecked() == true) {
            iatv = "Hora da leitura";
        } else if (s68.isChecked() == true) {
            iatv = "Jogos";
        } else if (s69.isChecked() == true) {
            iatv = "Arte";
        } else if (s70.isChecked() == true) {
            iatv = "Musicalização";
        } else if (s71.isChecked() == true) {
            iatv = "Videoteca";
        }
        enviar15();
    }
    String obs3 = "";
        public void enviar15() {
            final EditText et2 = (EditText) findViewById(R.id.obs2);
            if (et2.equals(null) == false) {
                String nn = et2.getText().toString();
                obs3 += nn + "";
            }
            pegar_do_servidor();
    }

    //ESSA É A ÚLTIMA PARTE.
    //AQUI VOCÊ PEGA AS STRINGS CRIADAS E ENVIA TUDO AO SERVIDOR
    //CADA PARTE TEM UMA KEY (CHILD) DIFERENTE!

    Integer valor_agenda = 0;
        String atvs;
        String dever;
        String aviso;
    public void  pegar_do_servidor(){
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                valor_agenda = dataSnapshot.getValue(Integer.class);
                valor_agenda += 1;
                myRef2.setValue(valor_agenda);
                pegar_agendaTurma();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void pegar_agendaTurma(){
        myRef3.child("atvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                atvs = dataSnapshot.getValue(String.class);
                myRef3.child("dever").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dever = dataSnapshot.getValue(String.class);
                        myRef3.child("aviso").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                aviso = dataSnapshot.getValue(String.class);
                                enviar_servidor();
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

        public void enviar_servidor(){

            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-yyyy");
            String currentDateandTime2 = sdf2.format(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String currentDateandTime = sdf.format(new Date());

            String data = valor_agenda + " - " + currentDateandTime;

            myRef.child(currentDateandTime2).child(data).child("falta").setValue(falta);
            myRef.child(currentDateandTime2).child(data).child("atvs").setValue(atvs);
            myRef.child(currentDateandTime2).child(data).child("atvs2").setValue(atvs2);
            myRef.child(currentDateandTime2).child(data).child("comportamento").setValue(comportamento);
            myRef.child(currentDateandTime2).child(data).child("dever").setValue(dever);
            myRef.child(currentDateandTime2).child(data).child("imedicacao").setValue(imedicacao);
            myRef.child(currentDateandTime2).child(data).child("iremedio").setValue(iremedio);
            myRef.child(currentDateandTime2).child(data).child("idosagem").setValue(idosagem);
            myRef.child(currentDateandTime2).child(data).child("ihorario").setValue(ihorario);
            myRef.child(currentDateandTime2).child(data).child("apresentou").setValue(apresentou);
            myRef.child(currentDateandTime2).child(data).child("aviso").setValue(aviso);
            myRef.child(currentDateandTime2).child(data).child("ialmoço").setValue(ialmoço);
            myRef.child(currentDateandTime2).child(data).child("ilanchevespertino").setValue(ilanchevespertino);
            myRef.child(currentDateandTime2).child(data).child("ilanchematutino").setValue(ilanchematutino);
            myRef.child(currentDateandTime2).child(data).child("ijanta").setValue(ijanta);
            myRef.child(currentDateandTime2).child(data).child("isono").setValue(isono);
            myRef.child(currentDateandTime2).child(data).child("icomportamento").setValue(icomportamento);
            myRef.child(currentDateandTime2).child(data).child("iprovidenciar").setValue(iprovidenciar);
            myRef.child(currentDateandTime2).child(data).child("iatv").setValue(iatv);
            myRef.child(currentDateandTime2).child(data).child("obs3").setValue(obs3);

            Intent intent = new Intent(getBaseContext(), tela_do_aluno_prof.class);
            startActivity(intent);
        }


        //As atividades de sala
        public void hg1(View view){
            s25 = findViewById(R.id.s25);
            if (s25.isChecked()) {
                s25.setChecked(false);
            }
        }

    public void hg2(View view){
        s24 = findViewById(R.id.s24);
        if (s24.isChecked()) {
            s24.setChecked(false);
        }
    }

    //Comportamento
    public void hg3(View view){
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        if (s26.isChecked()) {
            s27.setChecked(false);
            s28.setChecked(false);
            s29.setChecked(false);
        }
    }

    public void hg4(View view){
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        if (s27.isChecked()) {
            s26.setChecked(false);
            s28.setChecked(false);
            s29.setChecked(false);
        }
    }

    public void hg5(View view){
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        if (s28.isChecked()) {
            s27.setChecked(false);
            s26.setChecked(false);
            s29.setChecked(false);
        }
    }

    public void hg6(View view){
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        if (s29.isChecked()) {
            s27.setChecked(false);
            s28.setChecked(false);
            s26.setChecked(false);
        }
    }

    //Lanche Matutino
    public void hg7(View view){
        s32 = findViewById(R.id.s32);
        s34 = findViewById(R.id.s34);
        s72 = findViewById(R.id.s72);
        if (s32.isChecked()) {
            s34.setChecked(false);
            s72.setChecked(false);
        }
    }

    public void hg8(View view){
        s32 = findViewById(R.id.s32);
        s34 = findViewById(R.id.s34);
        s72 = findViewById(R.id.s72);
        if (s34.isChecked()) {
            s32.setChecked(false);
            s72.setChecked(false);
        }
    }

    public void hg9(View view){
        s32 = findViewById(R.id.s32);
        s34 = findViewById(R.id.s34);
        s72 = findViewById(R.id.s72);
        if (s72.isChecked()) {
            s34.setChecked(false);
            s32.setChecked(false);
        }
    }

    }



