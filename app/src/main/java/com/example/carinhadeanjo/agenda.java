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
        Switch s24 = findViewById(R.id.s24);
        Switch s25 = findViewById(R.id.s25);
        Switch s26 = findViewById(R.id.s26);
        Switch s27 = findViewById(R.id.s27);
        Switch s28 = findViewById(R.id.s28);
        Switch s29 = findViewById(R.id.s29);
        Switch s30 = findViewById(R.id.s30);
        Switch s31 = findViewById(R.id.s31);
        Switch s33 = findViewById(R.id.s33);
        Switch s35 = findViewById(R.id.s35);
        Switch s36 = findViewById(R.id.s36);
        Switch s37 = findViewById(R.id.s37);
        Switch s38 = findViewById(R.id.s38);
        Switch s39 = findViewById(R.id.s39);
        Switch s40 = findViewById(R.id.s40);
        Switch s41 = findViewById(R.id.s41);
        Switch s42 = findViewById(R.id.s42);
        Switch s43 = findViewById(R.id.s43);
        Switch s44 = findViewById(R.id.s44);
        Switch s45 = findViewById(R.id.s45);
        Switch s46 = findViewById(R.id.s46);
        Switch s47 = findViewById(R.id.s47);
        Switch s48 = findViewById(R.id.s48);
        Switch s49 = findViewById(R.id.s49);
        Switch s50 = findViewById(R.id.s50);
        Switch s51 = findViewById(R.id.s51);
        Switch s52 = findViewById(R.id.s52);
        Switch s53 = findViewById(R.id.s53);
        Switch s54 = findViewById(R.id.s54);
        Switch s55 = findViewById(R.id.s55);
        Switch s56 = findViewById(R.id.s56);
        Switch s57 = findViewById(R.id.s57);
        Switch s58 = findViewById(R.id.s58);
        Switch s59 = findViewById(R.id.s59);
        Switch s60 = findViewById(R.id.s60);
        Switch s61 = findViewById(R.id.s61);
        Switch s62 = findViewById(R.id.s62);
        Switch s63 = findViewById(R.id.s63);
        Switch s64 = findViewById(R.id.s64);
        Switch s65 = findViewById(R.id.s65);
        Switch s66 = findViewById(R.id.s66);
        Switch s67 = findViewById(R.id.s67);
        Switch s68 = findViewById(R.id.s68);
        Switch s69 = findViewById(R.id.s69);
        Switch s70 = findViewById(R.id.s70);
        Switch s71 = findViewById(R.id.s71);
        Switch siMed = findViewById(R.id.imed);

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


        if (s24.isChecked() == true) s25.setChecked(false);
        {
            atvs2 = "Realizou com facilidade";
        }

        if (s25.isChecked() == true) s24.setChecked(false);
        {

            atvs2 = "Realizou com dificuldade";
        }

    }

    String comportamento = "";
    Switch s26;
    Switch s27;
    Switch s28;
    Switch s29;

    public void enviar3(View view) {

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

    }

    String dever = "";
    Switch s30;
    Switch s31;
    Switch s33;
    Switch s35;

    public void enviar4(View view) {

        s30 = findViewById(R.id.s30);
        s31 = findViewById(R.id.s31);
        s33 = findViewById(R.id.s33);
        s35 = findViewById(R.id.s35);


        if (s30.isChecked() == true) {
            dever = "Em folha";
        } else if (s35.isChecked() == true) {
            dever = "Página";
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
                }
            }
        }
    }



    String imedicacao = "";
    Switch siMed;

    public void enviar5(View view) {

        siMed = findViewById(R.id.imed);

        if (siMed.isChecked() == true) {
            final EditText et2 = (EditText) findViewById(R.id.iremedio2);
            String nn = et2.getText().toString();
            if (nn.equals(null) == true) {
                errormsg = "Você precisa escrever ao menos um Rémedio";
                erro();
            } else {
                imedicacao += nn + " | ";
            }

            if (siMed.isChecked() == true) {
                final EditText et3 = (EditText) findViewById(R.id.idosagem2);
                String nn2 = et2.getText().toString();
                if (nn.equals(null) == true) {
                    errormsg = "Você precisa escrever ao menos uma Dosagem!";
                    erro();
                } else {
                    imedicacao += nn + " | ";
                }

                if (siMed.isChecked() == true) {
                    final EditText et4 = (EditText) findViewById(R.id.ihorario2);
                    String nn3 = et2.getText().toString();
                    if (nn.equals(null) == true) {
                        errormsg = "Você precisa escrever ao menos um Horário!";
                        erro();
                    } else {
                        imedicacao += nn + " | ";
                    }
                }
            }
        }
    }

                String apresentou = "";
               Switch s36;
               Switch s37;
               Switch s38;
               Switch s39;

                public void enviar6(View view) {
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
        }

    String ialmoço = "";
    Switch s40;
    Switch s41;
    Switch s42;

    public void enviar7(View view) {
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

    }

    String aviso = "";

    public void enviar8(View view) {
        final EditText et2 = (EditText) findViewById(R.id.avisos);
        if (et2.equals(null) == false) {
            String nn = et2.getText().toString();
            aviso += nn + "";
        }
    }


    String ilanchevespertino = "";
    Switch s43;
    Switch s44;
    Switch s45;

    public void enviar9(View view) {
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
    }

    String ijanta = "";
    Switch s46;
    Switch s47;
    Switch s48;

    public void enviar10(View view) {
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
    }

    String isono = "";
    Switch s49;
    Switch s50;
    Switch s51;
    Switch s52;
    Switch s53;

    public void enviar11(View view) {
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
    }

    String icomportamento = "";
    Switch s54;
    Switch s55;
    Switch s56;

    public void enviar12(View view) {
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

    public void enviar13(View view) {
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

    }

    String iatv = "";
    Switch s65;
    Switch s66;
    Switch s67;
    Switch s68;
    Switch s69;
    Switch s70;
    Switch s71;

    public void enviar14(View view) {
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

    }
    String obs3 = "";
        public void enviar15(View view) {
            final EditText et2 = (EditText) findViewById(R.id.obs2);
            if (et2.equals(null) == false) {
                String nn = et2.getText().toString();
                obs3 += nn + "";
            }
    }

    //ESSA É A ÚLTIMA PARTE.
    //AQUI VOCÊ PEGA AS STRINGS CRIADAS E ENVIA TUDO AO SERVIDOR
    //CADA PARTE TEM UMA KEY (CHILD) DIFERENTE!

        public void enviar_servidor(View view){

            SimpleDateFormat sdf2 = new SimpleDateFormat("MM-yyyy");
            String currentDateandTime2 = sdf2.format(new Date());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy as HH:mm");
            String currentDateandTime = sdf.format(new Date());


            myRef.child(currentDateandTime2).child(currentDateandTime).child("atvs").setValue(atvs);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("atvs2").setValue(atvs2);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("comportamento").setValue(comportamento);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("dever").setValue(dever);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("Medicacao").setValue(imedicacao);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("Apresentou").setValue(apresentou);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("aviso").setValue(aviso);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("ialmoço").setValue(ialmoço);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("ilanchevespertino").setValue(ilanchevespertino);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("ijanta").setValue(ijanta);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("isono").setValue(isono);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("icomportamento").setValue(icomportamento);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("iprovidenciar").setValue(iprovidenciar);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("iatv").setValue(iatv);
            myRef.child(currentDateandTime2).child(currentDateandTime).child("obs3").setValue(obs3);
        }


    }



