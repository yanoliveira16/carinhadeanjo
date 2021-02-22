package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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
    DatabaseReference myRef4 = database.child("P3").child(tela_do_aluno_prof.id_aluno).child("faltas");
    DatabaseReference myRef5 = database.child("P5").child(tela_de_carregamento.tturma).child("AgendaTemporaria").child(tela_do_aluno_prof.id_aluno);
    DatabaseReference myRef_feed = database.child("P2").child(tela_de_carregamento.tturma);
    DatabaseReference myRef6 = database.child("P6");

    Switch s_ballet;
    Switch s_judo;
    Switch sint_dormiu;
    Switch sint_evacuou;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        ImageView myImage = (ImageView) findViewById(R.id.imageView17);
        myImage.setImageBitmap(tela_do_aluno_prof.getRoundedCornerBitmap(tela_do_aluno_prof.my_image, 400));

        TextView a1 = (TextView) findViewById(R.id.nome_aluno_agenda);
        a1.setText(tela_de_alunos.onClick3);

        colocar_carregamento();

        buscar_dia();

    }

    public void buscar_dia(){
        myRef3.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                String currentDateandTime2 = sdf2.format(new Date());
                if (nk == null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(agenda.this);
                    builder1.setMessage("OPA\nVOCÊ NÃO FEZ A AGENDA DA TURMA HOJE!\nFaça a agenda da turma antes de fazer a agenda do aluno!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "VOLTAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else if (nk.contains(currentDateandTime2)){
                    tem_temporaria();
                }else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(agenda.this);
                    builder1.setMessage("OPA\nVOCÊ NÃO FEZ A AGENDA DA TURMA HOJE!\nFaça a agenda da turma antes de fazer a agenda do aluno!");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "VOLTAR",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void tem_temporaria(){
        myRef5.child("valor").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk == null){
                    tirar_carregamento();

                   // new AlertDialog.Builder(agenda.this).setMessage("NOVA AGENDA\nLEMBRE-SE DE FAZER A AGENDA DA TURMA!").show();
                }else if(nk.contains("sim") == true){
                    puxar_temporaria();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    boolean hg1 = false;
    boolean hg2 = false;
    boolean hg3 = false;
    boolean hg4 = false;
    boolean hg5 = false;
    boolean hg6 = false;
    boolean hg7 = false;
    boolean hg8 = false;
    boolean hg9 = false;
    boolean hg10 = false;
    boolean hg11 = false;
    boolean hg12 = false;

    public void puxar_temporaria(){
        myRef5.child("extra_ballet").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk != null){
                    if (nk.contains("sim")){
                        s_ballet = findViewById(R.id.s_ballet);
                        s_ballet.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("extra_judo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk != null){
                    if (nk.contains("sim")){
                        s_judo = findViewById(R.id.s_judo);
                        s_judo.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("apresentou").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Coriza") == true){
                    s36 = findViewById(R.id.s36);
                    s36.setChecked(true);
                }
                if (nk.contains("Tosse") == true){
                    s37 = findViewById(R.id.s37);
                    s37.setChecked(true);
                }
                if (nk.contains("Vômito") == true){
                    s38 = findViewById(R.id.s38);
                    s38.setChecked(true);
                }
                if (nk.contains("Febre") == true){
                    s39 = findViewById(R.id.s39);
                    s39.setChecked(true);
                }
                hg1 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("atvs2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("facilidade") == true){
                    s24 = findViewById(R.id.s24);
                    s24.setChecked(true);
                }else if (nk.contains("dificuldade") == true){
                    s25 = findViewById(R.id.s25);
                    s25.setChecked(true);
                }
                hg2 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("comportamento").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Tranquilo") == true){
                    s26 = findViewById(R.id.s26);
                    s26.setChecked(true);
                }else if (nk.contains("Obediente") == true){
                    s27 = findViewById(R.id.s27);
                    s27.setChecked(true);
                }else if (nk.contains("Conversou") == true){
                    s28 = findViewById(R.id.s28);
                    s28.setChecked(true);
                }else if (nk.contains("Irritado") == true){
                    s29 = findViewById(R.id.s29);
                    s29.setChecked(true);
                }else if (nk.contains("Agitado") == true){
                    s35 = findViewById(R.id.s35);
                    s35.setChecked(true);
                }
                hg3 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("iatv").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Psicomotricidade") == true){
                    s65 = findViewById(R.id.s65);
                    s65.setChecked(true);
                }
                if (nk.contains("Recreação") == true){
                    s66 = findViewById(R.id.s66);
                    s66.setChecked(true);
                }
                if (nk.contains("Hora") == true){
                    s67 = findViewById(R.id.s67);
                    s67.setChecked(true);
                }
                if (nk.contains("Jogos") == true){
                    s68 = findViewById(R.id.s68);
                    s68.setChecked(true);
                }
                if (nk.contains("Arte") == true){
                    s69 = findViewById(R.id.s69);
                    s69.setChecked(true);
                }
                if (nk.contains("Musicalização") == true){
                    s70 = findViewById(R.id.s70);
                    s70.setChecked(true);
                }
                if (nk.contains("Videoteca") == true){
                    s71 = findViewById(R.id.s71);
                    s71.setChecked(true);
                }
                hg4 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("ilanchematutino").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("bem") == true){
                    s32 = findViewById(R.id.s32);
                    s32.setChecked(true);
                }else if (nk.contains("pouco") == true){
                    s34 = findViewById(R.id.s34);
                    s34.setChecked(true);
                }else if (nk.contains("Não") == true){
                    s72 = findViewById(R.id.s72);
                    s72.setChecked(true);
                }
                hg5 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("ilanchevespertino").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("bem") == true){
                    s43 = findViewById(R.id.s43);
                    s43.setChecked(true);
                }else if (nk.contains("pouco") == true){
                    s44 = findViewById(R.id.s44);
                    s44.setChecked(true);
                }else if (nk.contains("Não") == true){
                    s45 = findViewById(R.id.s45);
                    s45.setChecked(true);
                }
                hg6 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("ialmoço").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("bem") == true){
                    s40 = findViewById(R.id.s40);
                    s40.setChecked(true);
                }else if (nk.contains("pouco") == true){
                    s41 = findViewById(R.id.s41);
                    s41.setChecked(true);
                }else if (nk.contains("Não") == true){
                    s42 = findViewById(R.id.s42);
                    s42.setChecked(true);
                }
                hg7 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("ijanta").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("bem") == true){
                    s46 = findViewById(R.id.s46);
                    s46.setChecked(true);
                }else if (nk.contains("pouco") == true){
                    s47 = findViewById(R.id.s47);
                    s47.setChecked(true);
                }else if (nk.contains("Não") == true){
                    s48 = findViewById(R.id.s48);
                    s48.setChecked(true);
                }
                hg8 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("icomportamento").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Tranquilo") == true){
                    s54 = findViewById(R.id.s54);
                    s54.setChecked(true);
                }else if (nk.contains("Obediente") == true){
                    s55 = findViewById(R.id.s55);
                    s55.setChecked(true);
                }else if (nk.contains("Irritado") == true){
                    s56 = findViewById(R.id.s56);
                    s56.setChecked(true);
                }else if (nk.contains("Agitado") == true){
                    sint_agitado = findViewById(R.id.sint_agitado);
                    sint_agitado.setChecked(true);
                }
                hg9 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("imedicacao").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Sim") == true){
                    siMed = findViewById(R.id.imed);
                    siMed.setChecked(true);
                    myRef5.child("iremedio").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String nk = dataSnapshot.getValue(String.class);
                            final EditText et2 = (EditText) findViewById(R.id.iremedio2);
                            et2.setText(nk);
                            myRef5.child("ihorario").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String nk = dataSnapshot.getValue(String.class);
                                    final EditText et2 = (EditText) findViewById(R.id.ihorario2);
                                    et2.setText(nk);
                                    myRef5.child("idosagem").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            String nk = dataSnapshot.getValue(String.class);
                                            final EditText et2 = (EditText) findViewById(R.id.idosagem2);
                                            et2.setText(nk);
                                            hg9 = true;
                                            checar();
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
                }else{
                    hg9 = true;
                    checar();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("obs3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                final EditText et2 = (EditText) findViewById(R.id.obs2);
                et2.setText(nk);
                hg10 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("isono").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Não dor") == true){
                    s49 = findViewById(R.id.s49);
                    s49.setChecked(true);
                }else if (nk.contains("Dormiu") == true){
                    sint_dormiu = findViewById(R.id.sint_dormiu);
                    sint_dormiu.setChecked(true);
                }
                if (nk.contains("vezes") == true){
                    sint_evacuou = findViewById(R.id.sint_evacuou);
                    sint_evacuou.setChecked(true);
                }else if (nk.contains("Não evacuou")== true){
                    s50 = findViewById(R.id.s50);
                    s50.setChecked(true);
                }
                if (nk.contains("Urinou") == true){
                    s51 = findViewById(R.id.s51);
                    s51.setChecked(true);
                }
                if (nk.contains("Tomou") == true){
                    s52 = findViewById(R.id.s52);
                    s52.setChecked(true);
                }
                if (nk.contains("Não tomou banho") == true){
                    s53 = findViewById(R.id.s53);
                    s53.setChecked(true);
                }
                hg11 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("isono_vezes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk != null && nk != ""){
                    final EditText et5 = (EditText) findViewById(R.id.sint_qntsevacuou);
                    et5.setText(nk);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef5.child("iprovidenciar").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nk = dataSnapshot.getValue(String.class);
                if (nk.contains("Toalha") == true){
                    s57 = findViewById(R.id.s57);
                    s57.setChecked(true);
                }
                if (nk.contains("Lençol") == true){
                    s58 = findViewById(R.id.s58);
                    s58.setChecked(true);
                }
                if (nk.contains("Fralda") == true){
                    s59 = findViewById(R.id.s59);
                    s59.setChecked(true);
                }
                if (nk.contains("Sabonete") == true){
                    s60 = findViewById(R.id.s60);
                    s60.setChecked(true);
                }
                if (nk.contains("Shampoo") == true){
                    s61 = findViewById(R.id.s61);
                    s61.setChecked(true);
                }
                if (nk.contains("Condicionador") == true){
                    s62 = findViewById(R.id.s62);
                    s62.setChecked(true);
                }
                if (nk.contains("Colônia") == true){
                    s63 = findViewById(R.id.s63);
                    s63.setChecked(true);
                }
                if (nk.contains("Lenço") == true){
                    s64 = findViewById(R.id.s64);
                    s64.setChecked(true);
                }
                hg12 = true;
                checar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void checar(){
        if(hg1 == true && hg2 == true && hg3 == true && hg4 == true && hg5 == true && hg6 == true && hg7 == true && hg8 == true && hg9 == true && hg10 == true && hg11 == true && hg12 == true){
            tirar_carregamento();
        }
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

    public void enviar0() {
        s = findViewById(R.id.s);
        if (s.isChecked() == false) {
            falta = "PRESENTE";
            enviar2();
        }else if (s.isChecked() == true){
            falta = "FALTA";
            myRef4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int nn = dataSnapshot.getValue(Integer.class);
                    nn += 1;
                    myRef4.setValue(nn);
                    enviar2();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }


    public void enviar(View view) {
        View a1 = findViewById(R.id.progressBar4);
        a1.setVisibility(View.VISIBLE);
        View a2 = findViewById(R.id.imageView25);
        a2.setVisibility(View.VISIBLE);
        View aa3 = findViewById(R.id.button12);
        aa3.setVisibility(View.INVISIBLE);
        View aa4 = findViewById(R.id.button13);
        aa3.setVisibility(View.INVISIBLE);
        View aa5 = findViewById(R.id.button14);
        aa3.setVisibility(View.INVISIBLE);

        a1.setAlpha(1);
        a2.setAlpha(1);
        aa3.setAlpha(0);
        aa4.setAlpha(0);
        aa5.setAlpha(0);

        agd_temporaria = false;

        enviar0();
    }

    String atvs2 = "";
    String extra_ballet = "";
    String extra_judo = "";
    Switch s24;
    Switch s25;

    public void enviar2(){
        s_ballet = findViewById(R.id.s_ballet);
        s_judo = findViewById(R.id.s_judo);

        if (s_ballet.isChecked() == true){
            extra_ballet = "sim";
        }

        if (s_judo.isChecked() == true){
            extra_judo = "sim";
        }

        enviar2_1();

    }

    public void enviar2_1() {

        s24 = findViewById(R.id.s24);
        s25 = findViewById(R.id.s25);


        if (s24.isChecked() == true)
        {
            atvs2 = "Realizou com facilidade";
        }else if (s25.isChecked() == true)
        {
            atvs2 = "Realizou com dificuldade";
        }else{
            atvs2 = "";
        }

        enviar3();
    }

    String comportamento = "";
    Switch s26;
    Switch s27;
    Switch s28;
    Switch s29;
    Switch s35;

    public void enviar3() {

        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        s35 = findViewById(R.id.s35);


        if (s26.isChecked() == true) {
            comportamento = "Tranquilo";
        } else if (s27.isChecked() == true) {
            comportamento = "Obediente";
        } else if (s28.isChecked() == true) {
            comportamento = "Conversou muito";
        } else if (s29.isChecked() == true) {
            comportamento = "Irritado";
        } else if (s35.isChecked() == true) {
            comportamento = "Agitado";
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

    boolean pode_continuar = true;

    public void enviar5() {
        pode_continuar = true;
        siMed = findViewById(R.id.imed);

        if (siMed.isChecked() == true) {
            imedicacao = "Sim";
        } else {
            imedicacao = "";
        }

        if (siMed.isChecked() == true) {
            final EditText et2 = (EditText) findViewById(R.id.iremedio2);
            String nn = et2.getText().toString();
            Log.d("AGENDA--","MEDICA 1 " +nn);
            if (nn.equals(null) == true || nn == null || nn == "" || nn.isEmpty()) {
                pode_continuar = false;
                errormsg = "Você precisa escrever ao menos um Rémedio";
                erro();
                tirar_carregamento();
            } else {
                iremedio += nn;
            }

            if (siMed.isChecked() == true) {
                final EditText et3 = (EditText) findViewById(R.id.idosagem2);
                String nn2 = et3.getText().toString();
                if (nn2.equals(null) == true || nn2 == null || nn2 == "" || nn2.isEmpty()) {
                    pode_continuar = false;
                    errormsg = "Você precisa escrever ao menos uma Dosagem!";
                    erro();
                    tirar_carregamento();
                } else {
                    idosagem += nn2;
                }

                if (siMed.isChecked() == true) {
                    final EditText et4 = (EditText) findViewById(R.id.ihorario2);
                    String nn3 = et4.getText().toString();
                    if (nn3.equals(null) == true || nn3 == null || nn3 == "" || nn3.isEmpty()) {
                        pode_continuar = false;
                        errormsg = "Você precisa escrever ao menos um Horário!";
                        erro();
                        tirar_carregamento();
                    } else {
                        ihorario += nn3;
                    }
                }
            }
        }

        if (pode_continuar == true){
            enviar6();
        }
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
        }
        if (s37.isChecked() == true) {
            apresentou += "Tosse | ";
        }
        if (s38.isChecked() == true) {
            apresentou += "Vômito | ";
        }
        if (s39.isChecked() == true) {
            apresentou += "Febre | ";
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
        } else if (s72.isChecked() == true) {
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
        } else if (s42.isChecked() == true) {
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
        sint_dormiu = findViewById(R.id.sint_dormiu);
        s49 = findViewById(R.id.s49);
        s50 = findViewById(R.id.s50);
        s51 = findViewById(R.id.s51);
        s52 = findViewById(R.id.s52);
        s53 = findViewById(R.id.s53);

        if (sint_dormiu.isChecked() == true){
            isono = "Dormiu | ";
        }else if (s49.isChecked() == true) {
            isono = "Não dormiu | ";
        }
        if (s50.isChecked() == true) {
            isono += "Não evacuou | ";
        }
        if (s51.isChecked() == true) {
            isono += "Urinou pouco | ";
        }
        if (s52.isChecked() == true) {
            isono += "Tomou pouca água | ";
        }
        if (s53.isChecked() == true) {
            isono += "Não tomou banho";
        }
        enviar12();
    }

    String icomportamento = "";
    Switch s54;
    Switch s55;
    Switch s56;
    Switch sint_agitado;

    public void enviar12() {
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);
        sint_agitado = findViewById(R.id.sint_agitado);

        if (s54.isChecked() == true) {
            icomportamento = "Tranquilo";
        } else if (s55.isChecked() == true) {
            icomportamento = "Obediente";
        } else if (s56.isChecked() == true) {
            icomportamento = "Irritado";
        }else if (sint_agitado.isChecked() == true){
            icomportamento = "Agitado";
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
            iprovidenciar = "Toalha | ";
        }
        if (s58.isChecked() == true) {
            iprovidenciar += "Lençol | ";
        }
        if (s59.isChecked() == true) {
            iprovidenciar += "Fralda | ";
        }
        if (s60.isChecked() == true) {
            iprovidenciar += "Sabonete Líquido | ";
        }
        if (s61.isChecked() == true) {
            iprovidenciar += "Shampoo |";
        }
        if (s62.isChecked() == true) {
            iprovidenciar += "Condicionador | ";
        }
        if (s63.isChecked() == true) {
            iprovidenciar += "Colônia | ";
        }
        if (s64.isChecked() == true) {
            iprovidenciar += "Lenço Umidecido";
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
        s65 = findViewById(R.id.s65);
        s66 = findViewById(R.id.s66);
        s67 = findViewById(R.id.s67);
        s68 = findViewById(R.id.s68);
        s69 = findViewById(R.id.s69);
        s70 = findViewById(R.id.s70);
        s71 = findViewById(R.id.s71);


        if (s65.isChecked() == true) {
            iatv = "Psicomotricidade | ";
        }
        if (s66.isChecked() == true) {
            iatv += "Recreação | ";
        }
        if (s67.isChecked() == true) {
            iatv += "Hora da leitura | ";
        }
        if (s68.isChecked() == true) {
            iatv += "Jogos | ";
        }
        if (s69.isChecked() == true) {
            iatv += "Arte | ";
        }
        if (s70.isChecked() == true) {
            iatv += "Musicalização | ";
        }
        if (s71.isChecked() == true) {
            iatv += "Videoteca";
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
        if (agd_temporaria == false){
            pegar_do_servidor();
        }else{
            enviar_temporaria();
        }
    }

    //ESSA É A ÚLTIMA PARTE.
    //AQUI VOCÊ PEGA AS STRINGS CRIADAS E ENVIA TUDO AO SERVIDOR
    //CADA PARTE TEM UMA KEY (CHILD) DIFERENTE!

    Integer valor_agenda = 0;
    String atvs;
    String dever;
    String aviso;

    public void pegar_do_servidor() {
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

    public void pegar_agendaTurma() {
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

    boolean agd_temporaria = false;
    public void salvar_agenda_temporaria(View view){
        colocar_carregamento();
        agd_temporaria = true;
        enviar2();
    }

    public void enviar_temporaria(){
        SimpleDateFormat sdf000 = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime000 = sdf000.format(new Date());

        myRef5.child("atvs2").setValue(atvs2);
        myRef5.child("extra_ballet").setValue(extra_ballet);
        myRef5.child("extra_judo").setValue(extra_judo);
        myRef5.child("comportamento").setValue(comportamento);
        myRef5.child("imedicacao").setValue(imedicacao);
        myRef5.child("iremedio").setValue(iremedio);
        myRef5.child("idosagem").setValue(idosagem);
        myRef5.child("ihorario").setValue(ihorario);
        myRef5.child("apresentou").setValue(apresentou);
        myRef5.child("ialmoço").setValue(ialmoço);
        myRef5.child("ilanchevespertino").setValue(ilanchevespertino);
        myRef5.child("ilanchematutino").setValue(ilanchematutino);
        myRef5.child("ijanta").setValue(ijanta);
        myRef5.child("isono").setValue(isono);
        myRef5.child("icomportamento").setValue(icomportamento);
        myRef5.child("iprovidenciar").setValue(iprovidenciar);
        myRef5.child("iatv").setValue(iatv);
        myRef5.child("obs3").setValue(obs3);
        myRef5.child("valor").setValue("sim");
        myRef6.child("agd_diaria").child(tela_de_carregamento.tturma).child(tela_de_alunos.onClick3).setValue("provi - "+ currentDateandTime000);
        new AlertDialog.Builder(agenda.this).setMessage("Agenda temporária enviada com sucesso!").show();
        Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
        startActivity(intent);
    }

    public void enviar_servidor(){
        if(extra_ballet == "sim"){
            atvs += "Ballet |";
        }
        if(extra_judo == "sim"){
            atvs += "Judô |";
        }

        enviar_servidor_oficial();
    }

    public void enviar_servidor_oficial() {

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-yyyy");
        String currentDateandTime2 = sdf2.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());

        SimpleDateFormat sdf000 = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime000 = sdf000.format(new Date());

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
        myRef6.child("agd_diaria").child(tela_de_carregamento.tturma).child(tela_de_alunos.onClick3).setValue("fixa - "+ currentDateandTime000);
        myRef5.removeValue();
        enviar_feed();
    }

    public void enviar_feed(){
        myRef_feed.child("totalfeed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer nn = dataSnapshot.getValue(Integer.class);
                nn += 1;
                myRef_feed.child("totalfeed").setValue(nn);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());

                myRef_feed.child("feed").child(nn +" - aln - " + tela_do_aluno_prof.id_aluno).setValue(currentDateandTime + ": NOVA AGENDA DISPONÍVEL!");

                pronto_feed();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void pronto_feed(){
        new AlertDialog.Builder(agenda.this).setMessage("Agenda enviada com sucesso!").show();
        //finish();
        Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
        startActivity(intent);
    }


    //As atividades de sala
    public void hg1(View view) {
        s24 = findViewById(R.id.s24);
        s25 = findViewById(R.id.s25);

        if (s24.isChecked()) {
            s25.setChecked(false);
        }
    }

    public void hg2(View view) {
        s24 = findViewById(R.id.s24);
        s25 = findViewById(R.id.s25);

        if (s25.isChecked()) {
            s24.setChecked(false);
        }
    }


    //Comportamento
    public void hg3(View view) {
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        s35 = findViewById(R.id.s35);

        if (s26.isChecked()) {
            s27.setChecked(false);
            s28.setChecked(false);
            s29.setChecked(false);
            s35.setChecked(false);
        }
    }
        public void hg4 (View view){
            s26 = findViewById(R.id.s26);
            s27 = findViewById(R.id.s27);
            s28 = findViewById(R.id.s28);
            s29 = findViewById(R.id.s29);
            s35 = findViewById(R.id.s35);

            if (s27.isChecked()) {
                s26.setChecked(false);
                s28.setChecked(false);
                s29.setChecked(false);
                s35.setChecked(false);
            }
        }
        public void hg5 (View view){
            s26 = findViewById(R.id.s26);
            s27 = findViewById(R.id.s27);
            s28 = findViewById(R.id.s28);
            s29 = findViewById(R.id.s29);
            s35 = findViewById(R.id.s35);

            if (s28.isChecked()) {
                s27.setChecked(false);
                s26.setChecked(false);
                s29.setChecked(false);
                s35.setChecked(false);
            }
        }
        public void hg6 (View view){
            s26 = findViewById(R.id.s26);
            s27 = findViewById(R.id.s27);
            s28 = findViewById(R.id.s28);
            s29 = findViewById(R.id.s29);
            s35 = findViewById(R.id.s35);

            if (s29.isChecked()) {
                s27.setChecked(false);
                s28.setChecked(false);
                s26.setChecked(false);
                s35.setChecked(false);
            }
        }

    public void hg6_2 (View view){
        s26 = findViewById(R.id.s26);
        s27 = findViewById(R.id.s27);
        s28 = findViewById(R.id.s28);
        s29 = findViewById(R.id.s29);
        s35 = findViewById(R.id.s35);

        if (s35.isChecked()) {
            s27.setChecked(false);
            s28.setChecked(false);
            s26.setChecked(false);
            s29.setChecked(false);
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


        if(s34.isChecked()){
            s32.setChecked(false);
            s72.setChecked(false);
        }
        }
        public void hg9(View view){
            s32 = findViewById(R.id.s32);
            s34 = findViewById(R.id.s34);
            s72 = findViewById(R.id.s72);

        if(s72.isChecked()) {
            s34.setChecked(false);
            s32.setChecked(false);
        }
        }

//Almoço
    public void hg10(View view) {
        s40 = findViewById(R.id.s40);
        s41 = findViewById(R.id.s41);
        s42 = findViewById(R.id.s42);

        if (s40.isChecked()) {
            s41.setChecked(false);
            s42.setChecked(false);
        }
    }
        public void hg11(View view){
            s40 = findViewById(R.id.s40);
            s41 = findViewById(R.id.s41);
            s42 = findViewById(R.id.s42);

            if (s41.isChecked()) {
                s40.setChecked(false);
                s42.setChecked(false);
            }
        }
        public void hg12(View view){
            s40 = findViewById(R.id.s40);
            s41 = findViewById(R.id.s41);
            s42 = findViewById(R.id.s42);

        if(s42.isChecked()){
            s40.setChecked(false);
            s41.setChecked(false);
        }
    }

    //lanche_vespertino
    public void hg13(View view) {
        s43 = findViewById(R.id.s43);
        s44 = findViewById(R.id.s44);
        s45 = findViewById(R.id.s45);

        if (s43.isChecked()) {
            s44.setChecked(false);
            s45.setChecked(false);
        }
    }
    public void hg14(View view) {
        s43 = findViewById(R.id.s43);
        s44 = findViewById(R.id.s44);
        s45 = findViewById(R.id.s45);

        if (s44.isChecked()) {
            s43.setChecked(false);
            s45.setChecked(false);
        }
    }
    public void hg15(View view) {
    s43 = findViewById(R.id.s43);
    s44 = findViewById(R.id.s44);
    s45 = findViewById(R.id.s45);

            if(s45.isChecked()){
            s44.setChecked(false);
            s43.setChecked(false);
        }
    }

    //jantar
    public void hg16(View view) {
        s46 = findViewById(R.id.s46);
        s47 = findViewById(R.id.s47);
        s48 = findViewById(R.id.s48);

        if (s46.isChecked()) {
            s47.setChecked(false);
            s48.setChecked(false);
        }
    }
    public void hg17(View view) {
        s46 = findViewById(R.id.s46);
        s47 = findViewById(R.id.s47);
        s48 = findViewById(R.id.s48);

        if (s47.isChecked()) {
            s46.setChecked(false);
            s48.setChecked(false);
        }
    }
    public void hg18(View view) {
        s46 = findViewById(R.id.s46);
        s47 = findViewById(R.id.s47);
        s48 = findViewById(R.id.s48);

         if(s48.isChecked()){
            s46.setChecked(false);
            s47.setChecked(false);
        }
    }

    //evacuou
    public void hg_evacuou1(View view){
        s50 = findViewById(R.id.s50);
        sint_evacuou = findViewById(R.id.sint_evacuou);

        if (s50.isChecked()) {
            sint_evacuou.setChecked(false);
        }
    }

    public void hg_evacuou2(View view){
        s50 = findViewById(R.id.s50);
        sint_evacuou = findViewById(R.id.sint_evacuou);

        if (sint_evacuou.isChecked()) {
            s50.setChecked(false);
        }
    }

    //dormiu
    public void hg_sono1(View view) {
        s49 = findViewById(R.id.s49);
        sint_dormiu = findViewById(R.id.sint_dormiu);

        if (s49.isChecked()) {
            sint_dormiu.setChecked(false);
        }
    }

    public void hg_sono2(View view) {
        s49 = findViewById(R.id.s49);
        sint_dormiu = findViewById(R.id.sint_dormiu);

        if (sint_dormiu.isChecked()) {
            s49.setChecked(false);
        }
    }

    //comportamento integral
    public void hg19(View view) {
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);
        sint_agitado = findViewById(R.id.sint_agitado);

        if (s54.isChecked()) {
            s55.setChecked(false);
            s56.setChecked(false);
            sint_agitado.setChecked(false);
        }
    }
    public void hg20(View view) {
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);
        sint_agitado = findViewById(R.id.sint_agitado);

        if (s55.isChecked()) {
            s54.setChecked(false);
            s56.setChecked(false);
            sint_agitado.setChecked(false);
        }
    }
    public void hg21(View view) {
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);
        sint_agitado = findViewById(R.id.sint_agitado);

        if(s56.isChecked()){
            s55.setChecked(false);
            s54.setChecked(false);
            sint_agitado.setChecked(false);
        }
    }
    public void hg20_1(View view){
        s54 = findViewById(R.id.s54);
        s55 = findViewById(R.id.s55);
        s56 = findViewById(R.id.s56);
        sint_agitado = findViewById(R.id.sint_agitado);

        if (sint_agitado.isChecked()) {
            s54.setChecked(false);
            s56.setChecked(false);
            s55.setChecked(false);
        }
    }


    public void apagar_agenda(View view){
        colocar_carregamento();
        AlertDialog.Builder builder1 = new AlertDialog.Builder(agenda.this);
        builder1.setMessage("ATENÇÃO\nTem certeza que deseja apagar toda agenda?\nIsso não apagará a agenda da turma.\nA agenda temporária será mantida até que uma nova seja enviada." +
                " Ou seja, se você sair e voltar, a agenda temporária anterior será preenchidda se não tiver uma nova!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Apagar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Switch aa_1 = findViewById(R.id.s_ballet);
                        aa_1.setChecked(false);
                        Switch aa_2 = findViewById(R.id.s_judo);
                        aa_2.setChecked(false);
                        Switch aa_3 = findViewById(R.id.sint_dormiu);
                        aa_3.setChecked(false);
                        Switch aa_4 = findViewById(R.id.sint_evacuou);
                        aa_4.setChecked(false);
                        Switch aa_5 = findViewById(R.id.sint_agitado);
                        aa_5.setChecked(false);

                        Switch a_1 = findViewById(R.id.s);
                        a_1.setChecked(false);
                        Switch a_2 = findViewById(R.id.s24);
                        a_2.setChecked(false);
                        Switch a_3 = findViewById(R.id.s25);
                        a_3.setChecked(false);
                        Switch a_4 = findViewById(R.id.s26);
                        a_4.setChecked(false);
                        Switch a_5 = findViewById(R.id.s27);
                        a_5.setChecked(false);
                        Switch a_6 = findViewById(R.id.s28);
                        a_6.setChecked(false);
                        Switch a_7 = findViewById(R.id.s29);
                        a_7.setChecked(false);
                        Switch a_8 = findViewById(R.id.s35);
                        a_8.setChecked(false);
                        Switch a_9 = findViewById(R.id.imed);
                        a_9.setChecked(false);
                        Switch a_10 = findViewById(R.id.s36);
                        a_10.setChecked(false);
                        Switch a_11 = findViewById(R.id.s37);
                        a_11.setChecked(false);
                        Switch a_12 = findViewById(R.id.s38);
                        a_12.setChecked(false);
                        Switch a_13 = findViewById(R.id.s39);
                        a_13.setChecked(false);
                        Switch a_14 = findViewById(R.id.s32);
                        a_14.setChecked(false);
                        Switch a_15 = findViewById(R.id.s34);
                        a_15.setChecked(false);
                        Switch a_16 = findViewById(R.id.s72);
                        a_16.setChecked(false);
                        Switch a_17 = findViewById(R.id.s40);
                        a_17.setChecked(false);
                        Switch a_18 = findViewById(R.id.s41);
                        a_18.setChecked(false);
                        Switch a_19 = findViewById(R.id.s42);
                        a_19.setChecked(false);
                        Switch a_20 = findViewById(R.id.s43);
                        a_20.setChecked(false);
                        Switch a_21 = findViewById(R.id.s44);
                        a_21.setChecked(false);
                        Switch a_22 = findViewById(R.id.s45);
                        a_22.setChecked(false);
                        Switch a_23 = findViewById(R.id.s46);
                        a_23.setChecked(false);
                        Switch a_24 = findViewById(R.id.s47);
                        a_24.setChecked(false);
                        Switch a_25 = findViewById(R.id.s48);
                        a_25.setChecked(false);
                        Switch a_26 = findViewById(R.id.s49);
                        a_26.setChecked(false);
                        Switch a_27 = findViewById(R.id.s50);
                        a_27.setChecked(false);
                        Switch a_28 = findViewById(R.id.s51);
                        a_28.setChecked(false);
                        Switch a_29 = findViewById(R.id.s52);
                        a_29.setChecked(false);
                        Switch a_30 = findViewById(R.id.s53);
                        a_30.setChecked(false);
                        Switch a_31 = findViewById(R.id.s54);
                        a_31.setChecked(false);
                        Switch a_32 = findViewById(R.id.s55);
                        a_32.setChecked(false);
                        Switch a_33 = findViewById(R.id.s56);
                        a_33.setChecked(false);
                        Switch a_34 = findViewById(R.id.s57);
                        a_34.setChecked(false);
                        Switch a_35 = findViewById(R.id.s58);
                        a_35.setChecked(false);
                        Switch a_36 = findViewById(R.id.s59);
                        a_36.setChecked(false);
                        Switch a_37 = findViewById(R.id.s60);
                        a_37.setChecked(false);
                        Switch a_38 = findViewById(R.id.s61);
                        a_38.setChecked(false);
                        Switch a_39 = findViewById(R.id.s62);
                        a_39.setChecked(false);
                        Switch a_40 = findViewById(R.id.s63);
                        a_40.setChecked(false);
                        Switch a_41 = findViewById(R.id.s64);
                        a_41.setChecked(false);
                        Switch a_42 = findViewById(R.id.s65);
                        a_42.setChecked(false);
                        Switch a_43 = findViewById(R.id.s66);
                        a_43.setChecked(false);
                        Switch a_44 = findViewById(R.id.s67);
                        a_44.setChecked(false);
                        Switch a_45 = findViewById(R.id.s68);
                        a_45.setChecked(false);
                        Switch a_46 = findViewById(R.id.s69);
                        a_46.setChecked(false);
                        Switch a_47 = findViewById(R.id.s70);
                        a_47.setChecked(false);
                        Switch a_48 = findViewById(R.id.s71);
                        a_48.setChecked(false);

                        final EditText et1 = (EditText) findViewById(R.id.iremedio2);
                        et1.setText("");
                        final EditText et2 = (EditText) findViewById(R.id.ihorario2);
                        et2.setText("");
                        final EditText et3 = (EditText) findViewById(R.id.idosagem2);
                        et3.setText("");
                        final EditText et4 = (EditText) findViewById(R.id.obs2);
                        et4.setText("");
                        final EditText et5 = (EditText) findViewById(R.id.sint_qntsevacuou);
                        et5.setText("");
                        tirar_carregamento();
                    }
                });

        builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        tirar_carregamento();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void visualizar_agenda_turma(View view){
        colocar_carregamento();
        myRef3.child("atvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String t_atvs = dataSnapshot.getValue(String.class);
                myRef3.child("dever").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String t_dever = dataSnapshot.getValue(String.class);
                        myRef3.child("aviso").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String t_aviso = dataSnapshot.getValue(String.class);
                                myRef3.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String t_data = dataSnapshot.getValue(String.class);
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(agenda.this);
                                        builder1.setMessage("AGENDA DA TURMA\n" + t_data +"\n\nAtividade de sala:\n" +t_atvs +"\n\nDever de casa:\n" + t_dever +"\n\nAviso:\n" +t_aviso);
                                        builder1.setCancelable(true);

                                        builder1.setNegativeButton(
                                                "OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        tirar_carregamento();
                                                        dialog.cancel();
                                                    }
                                                });

                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
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

    public void colocar_carregamento(){
        View aa1 = findViewById(R.id.progressBar4);
        aa1.setVisibility(View.VISIBLE);
        View aa2 = findViewById(R.id.imageView25);
        aa2.setVisibility(View.VISIBLE);
        View aa3 = findViewById(R.id.button12);
        aa3.setVisibility(View.INVISIBLE);
        View aa4 = findViewById(R.id.button13);
        aa4.setVisibility(View.INVISIBLE);
        View aa5 = findViewById(R.id.button14);
        aa5.setVisibility(View.INVISIBLE);

        aa1.setAlpha(1);
        aa2.setAlpha(1);
        aa3.setAlpha(0);
        aa4.setAlpha(0);
        aa5.setAlpha(0);
    }

    public void tirar_carregamento(){
        View aa1 = findViewById(R.id.progressBar4);
        aa1.setVisibility(View.INVISIBLE);
        View aa2 = findViewById(R.id.imageView25);
        aa2.setVisibility(View.INVISIBLE);
        View aa3 = findViewById(R.id.button12);
        aa3.setVisibility(View.VISIBLE);
        View aa4 = findViewById(R.id.button13);
        aa4.setVisibility(View.VISIBLE);
        View aa5 = findViewById(R.id.button14);
        aa5.setVisibility(View.VISIBLE);

        aa1.setAlpha(0);
        aa2.setAlpha(0);
        aa3.setAlpha(1);
        aa4.setAlpha(1);
        aa5.setAlpha(1);
    }

    }



