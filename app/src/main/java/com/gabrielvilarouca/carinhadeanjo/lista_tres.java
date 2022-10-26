package com.gabrielvilarouca.carinhadeanjo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class lista_tres extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef2 = database.child("P3");
    DatabaseReference myRef_feed = database.child("P2").child(tela_de_carregamento.tturma);
    DatabaseReference banco_feed_professor = database.child("P2").child(tela_de_carregamento.tturma);
    String uid;
    String v;
    String kgh;
    boolean s1 = false;
    boolean s2 = false;
    boolean tem_afalta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_tres);
        final TextView aaaaa = (TextView) findViewById(R.id.aluno_agenda3);

        Log.d("LISTA 3","ENTROU AQUI");

        if (tela_de_carregamento.qual == "1"){
            uid = tela_do_aluno_prof.id_aluno;
            v = tela_de_alunos.onClick3 + " \n " + lista_dois.onClick2;
            aaaaa.setText(v);
            final TextView a18 = (TextView) findViewById(R.id.ciente);
            final TextView a19 = (TextView) findViewById(R.id.recado);
            final TextView a20 = (TextView) findViewById(R.id.recadinho);
            ViewGroup parent = (ViewGroup) a18.getParent();
            parent.removeView(a18);
            ViewGroup parent2 = (ViewGroup) a19.getParent();
            parent2.removeView(a19);
            ViewGroup parent3 = (ViewGroup) a20.getParent();
            parent3.removeView(a20);
        }else{
            uid = login_or_register.id;
            v = tela_de_carregamento.nnomeAluno + " \n " + lista_dois.onClick2;
            aaaaa.setText(v);
            final TextView aa20 = (TextView) findViewById(R.id.apagar_agendaclick);
            ViewGroup parent = (ViewGroup) aa20.getParent();
            parent.removeView(aa20);
        }


            myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("visto_data").addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nn = dataSnapshot.getValue(String.class);
                    if (nn != null && tela_de_carregamento.qual != "1"){
                        aaaaa.setText(v + " \n " + "Ciente: " + nn);
                        final TextView a18 = (TextView) findViewById(R.id.ciente);
                        final TextView a19 = (TextView) findViewById(R.id.recado);
                        final TextView a20 = (TextView) findViewById(R.id.recadinho);
                        ViewGroup parent = (ViewGroup) a18.getParent();
                        parent.removeView(a18);
                        ViewGroup parent2 = (ViewGroup) a19.getParent();
                        parent2.removeView(a19);
                        ViewGroup parent3 = (ViewGroup) a20.getParent();
                        parent3.removeView(a20);
                    }else if (nn!= null && tela_de_carregamento.qual == "1"){
                        aaaaa.setText(v + " \n " + "Ciente: " + nn);
                    }else{
                        aaaaa.setText(v + " \n " + "Responsável não Ciente! ");
                    }
                    chamar_parte2();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

    }

    public void chamar_parte2(){
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("msg").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                if (nn == null){
                    //ViewGroup parent = (ViewGroup) a1.getParent();
                    //parent.removeView(a1);
                    kgh = "-";
                }else{
                    kgh = nn;
                    if (tela_de_carregamento.qual != "1"){
                        //ERROR AQUI
                      /*final TextView a1118 = (TextView) findViewById(R.id.ciente);
                        final TextView a1119 = (TextView) findViewById(R.id.recado);
                        final TextView a2220 = (TextView) findViewById(R.id.recadinho);
                        View a119=findViewById(R.id.recado);
                        a119.setVisibility(View.INVISIBLE);
                        View a120=findViewById(R.id.recadinho);
                        a120.setVisibility(View.INVISIBLE);
                        View a121=findViewById(R.id.ciente);
                        a121.setVisibility(View.INVISIBLE);
                        ViewGroup parent = (ViewGroup) a1118.getParent();
                        parent.removeView(a1118);
                        ViewGroup parent2 = (ViewGroup) a1119.getParent();
                        parent2.removeView(a1119);
                        ViewGroup parent3 = (ViewGroup) a2220.getParent();
                        parent3.removeView(a2220);*/
                    }
                }
                chamar_parte3();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void chamar_parte3(){
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("qnt_visu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer npt = dataSnapshot.getValue(Integer.class);
                if (tela_de_carregamento.qual != "1"){
                    if (npt == null){
                        npt = 1;
                        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("qnt_visu").setValue(npt);
                        kgh += "\n" +npt +" VISUALIZAÇÕES DO RESPONSÁVEL";
                        final TextView a1 = (TextView) findViewById(R.id.msg);
                        a1.setText(kgh);
                    }else{
                        npt += 1;
                        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("qnt_visu").setValue(npt);
                        kgh += "\n" +npt +" VISUALIZAÇÕES DO RESPONSÁVEL";
                        final TextView a1 = (TextView) findViewById(R.id.msg);
                        a1.setText(kgh);
                    }
                }else{
                    if (npt == null){
                        kgh += "\nO RESPONSÁVEL NÃO VISUALIZOU ESTA AGENDA";
                        final TextView a1 = (TextView) findViewById(R.id.msg);
                        a1.setText(kgh);
                    }else{
                        kgh += "\n" +npt +" VISUALIZAÇÕES DO RESPONSÁVEL";
                        final TextView a1 = (TextView) findViewById(R.id.msg);
                        a1.setText(kgh);
                    }
                }
                chamar_parte4();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void chamar_parte4(){
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
        gd.setCornerRadius(60.40f);

        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("falta").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a1 = (TextView) findViewById(R.id.falta);
                a1.setText(nn);
                if(nn.contains("FALTA")){
                    tem_afalta = true;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("atvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a2 = (TextView) findViewById(R.id.atvs);
                if (nn.equals("")){
                    a2.setPadding(0,-1,0,-20);
                    a2.setActivated(false);
                    View aa=findViewById(R.id.atividade_de_sala);
                    ViewGroup parent = (ViewGroup) a2.getParent();
                    parent.removeView(a2);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a2.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("atvs2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a3 = (TextView) findViewById(R.id.atvs2);
                if (nn.equals("")){
                    a3.setPadding(0,-1,0,-20);
                    a3.setActivated(false);
                    View aa=findViewById(R.id.as_atividades_de_sala);
                    ViewGroup parent = (ViewGroup) a3.getParent();
                    parent.removeView(a3);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a3.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("comportamento").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a4 = (TextView) findViewById(R.id.comportamento);
                if (nn.equals("")){
                    View aa=findViewById(R.id.comportamentoo);
                    ViewGroup parent = (ViewGroup) a4.getParent();
                    parent.removeView(a4);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a4.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("dever").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a5 = (TextView) findViewById(R.id.dever);
                if (nn.equals("")){
                    View aa=findViewById(R.id.dever_de_casa);
                    ViewGroup parent = (ViewGroup) a5.getParent();
                    parent.removeView(a5);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a5.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("aviso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a6 = (TextView) findViewById(R.id.aviso);
                Log.d("Porra", "aqui " + nn + "\n" + nn.contains(""));
                if (nn.equals("")){
                    View aa=findViewById(R.id.avisos);
                    ViewGroup parent = (ViewGroup) a6.getParent();
                    parent.removeView(a6);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a6.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("imedicacao").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                if (nn.equals("")){
                    View aa=findViewById(R.id.medicação);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                    s1 = false;
                }
                s1 = true;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("iremedio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a8 = (TextView) findViewById(R.id.iremedio);
                if (nn.equals("")){
                    ViewGroup parent = (ViewGroup) a8.getParent();
                    parent.removeView(a8);
                }
                s1 = true;
                a8.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("idosagem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a9 = (TextView) findViewById(R.id.idosagem);
                if (nn.equals("")){
                    View aa=findViewById(R.id.idosagem);
                    ViewGroup parent = (ViewGroup) a9.getParent();
                    parent.removeView(a9);
                }
                s1 = true;
                a9.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("ihorario").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a10 = (TextView) findViewById(R.id.ihorario);
                if (nn.equals("")){
                    View aa=findViewById(R.id.ihorario);
                    ViewGroup parent = (ViewGroup) a10.getParent();
                    parent.removeView(a10);
                }
                s1 = true;
                a10.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("apresentou").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a11 = (TextView) findViewById(R.id.apresentou);
                if (nn.equals("")){
                    View aa=findViewById(R.id.apresent);
                    ViewGroup parent = (ViewGroup) a11.getParent();
                    parent.removeView(a11);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                    s2 = false;
                    verificar_saude();
                }
                s2 = true;
                a11.setText(nn);
                verificar_saude();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("ilanchematutino").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a11 = (TextView) findViewById(R.id.ilanchematutino);
                if (nn.equals("")){
                    View aa=findViewById(R.id.lanche_mat);
                    ViewGroup parent = (ViewGroup) a11.getParent();
                    parent.removeView(a11);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a11.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("ialmoço").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a12 = (TextView) findViewById(R.id.ialmoço);
                if (nn.equals("")){
                    View aa=findViewById(R.id.Almoço);
                    ViewGroup parent = (ViewGroup) a12.getParent();
                    parent.removeView(a12);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a12.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("ilanchevespertino").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a13 = (TextView) findViewById(R.id.ilanchevespertino);
                if (nn.equals("")){
                    View aa=findViewById(R.id.lanche_vesp);
                    ViewGroup parent = (ViewGroup) a13.getParent();
                    parent.removeView(a13);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a13.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("ijanta").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a14 = (TextView) findViewById(R.id.ijanta);
                if (nn.equals("")){
                    View aa=findViewById(R.id.jantar);
                    ViewGroup parent = (ViewGroup) a14.getParent();
                    parent.removeView(a14);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a14.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("icomportamento").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a15 = (TextView) findViewById(R.id.icomportamento);
                if (nn.equals("")){
                    View aa=findViewById(R.id.comp_integral);
                    ViewGroup parent = (ViewGroup) a15.getParent();
                    parent.removeView(a15);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a15.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("isono").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a15 = (TextView) findViewById(R.id.isono);
                if (nn.equals("")){
                    View aa=findViewById(R.id.aspec_fisio);
                    ViewGroup parent = (ViewGroup) a15.getParent();
                    parent.removeView(a15);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a15.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("iprovidenciar").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a16 = (TextView) findViewById(R.id.iprovidenciar);
                if (nn.equals("")){
                    View aa=findViewById(R.id.f_providenciar);
                    ViewGroup parent = (ViewGroup) a16.getParent();
                    parent.removeView(a16);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a16.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("iatv").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a17 = (TextView) findViewById(R.id.iatv);
                if (nn.equals("")){
                    View aa=findViewById(R.id.atv_rotina);
                    ViewGroup parent = (ViewGroup) a17.getParent();
                    parent.removeView(a17);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a17.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("obs3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a18 = (TextView) findViewById(R.id.obs3);
                if (nn.equals("")){
                    View aa=findViewById(R.id.obsvervacao);
                    ViewGroup parent = (ViewGroup) a18.getParent();
                    parent.removeView(a18);
                    ViewGroup parent2 = (ViewGroup) aa.getParent();
                    parent2.removeView(aa);
                }
                a18.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        chamarfoto();
    }

    public void verificar_saude(){
        if (s1 == false && s2 == false){
            View aa=findViewById(R.id.saude);
            ViewGroup parent = (ViewGroup) aa.getParent();
            parent.removeView(aa);
        }
    }

    String msg = "";

    public void recadinho_enviar(View view) {
        final EditText et2 = (EditText) findViewById(R.id.recadinho);
        if (et2.equals(null) == false) {
            msg = et2.getText().toString();
            ciente();
        }
    }

    public void ciente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());

        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("visto_data").setValue(currentDateandTime);
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("visto").setValue("ok");
        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).child("msg").setValue("RESPONSÁVEL CIENTE COM A MENSAGEM: "+msg);

        final TextView  var = (TextView) findViewById(R.id.aluno_agenda3);
        var.setText(v + " \n " + "Ciente: "+currentDateandTime);

        enviar_novo_feed();
    }

    public void enviar_novo_feed(){
        banco_feed_professor.child("total_new_feed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer nn = dataSnapshot.getValue(Integer.class);
                if (nn == null){
                    nn = 1;
                    banco_feed_professor.child("total_new_feed").setValue(nn);
                }else{
                    if(nn >= 70){
                        nn = 1;
                        banco_feed_professor.child("new_feed").removeValue();
                        banco_feed_professor.child("total_new_feed").setValue(nn);
                    }else{
                        nn += 1;
                        banco_feed_professor.child("total_new_feed").setValue(nn);
                    }
                }

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());
                if (msg == null || msg == "" || msg == " "){
                    banco_feed_professor.child("new_feed").child(nn + " - "+currentDateandTime).setValue(currentDateandTime + "- " + tela_de_carregamento.nnomeAluno + ": RESPONSÁVEL CIENTE NA AGENDA " +lista_dois.onClick2);
                }else{
                    banco_feed_professor.child("new_feed").child(nn + " - "+currentDateandTime).setValue(currentDateandTime + "- " + tela_de_carregamento.nnomeAluno + ": RESPONSÁVEL CIENTE NA AGENDA " +lista_dois.onClick2 + " || RECADO: " +msg);
                }

                pronto_ciente();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void enviar_feed(){
        myRef_feed.child("TOTAL_FEED").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer nn = dataSnapshot.getValue(Integer.class);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String currentDateandTime = sdf.format(new Date());

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

                if (msg == null){
                    myRef_feed.child("FEED").child(nn +" - profe - " + uid).setValue(currentDateandTime + "- " + tela_de_carregamento.nnomeAluno + ": RESPONSÁVEL CIENTE!\nAGENDA:" +lista_dois.onClick2);
                    pronto_ciente();
                }else{
                    myRef_feed.child("FEED").child(nn +" - profe - " + uid).setValue(currentDateandTime + "- " + tela_de_carregamento.nnomeAluno + ": " +msg +"\nAGENDA:" +lista_dois.onClick2);
                    pronto_ciente();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void pronto_ciente(){
        new AlertDialog.Builder(lista_tres.this).setMessage("Enviado com sucesso!").show();
        final TextView a18 = (TextView) findViewById(R.id.ciente);
        final TextView a19 = (TextView) findViewById(R.id.recado);
        final TextView a20 = (TextView) findViewById(R.id.recadinho);
        ViewGroup parent = (ViewGroup) a18.getParent();
        parent.removeView(a18);
        ViewGroup parent2 = (ViewGroup) a19.getParent();
        parent2.removeView(a19);
        ViewGroup parent3 = (ViewGroup) a20.getParent();
        parent3.removeView(a20);
    }


    public void chamarfoto(){
            ImageView myImage2 = (ImageView) findViewById(R.id.profile_foto);
            myImage2.setImageBitmap(lista_dois.my_image2);
        View a1=findViewById(R.id.carrega_listatres1);
        a1.setVisibility(View.INVISIBLE);
        View a2=findViewById(R.id.carrega_listatres2);
        a2.setVisibility(View.INVISIBLE);
        }

        public void apagar_click(View view){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(lista_tres.this);
            builder1.setMessage("ATENÇÃO\n\nTEM CERTEZA DE QUE DESEJA APAGAR ESSA AGENDA?\nEssa ação não poderá ser desfeita!!!\n\nA agenda seguirá a ordem normalmente!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "apagar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            confirma_apagar();
                        }
                    });

            builder1.setNegativeButton(
                    "CANCELAR",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

        public void confirma_apagar(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(lista_tres.this);
            builder1.setMessage("TEM CERTEZA DE QUE DESEJA APAGAR ESTA AGENDA?\n\nNem o desenvolvedor do aplicativo poderá recuperá-la depois!\n\nIsso não irá tirar a notificação " +
                    "no feed do aluno!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Sim, quero apagar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (tem_afalta == true){
                                myRef2.child(uid).child("faltas").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        int nn = dataSnapshot.getValue(Integer.class);
                                        nn -= 1;
                                        myRef2.child(uid).child("faltas").setValue(nn);
                                        myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).removeValue();
                                        Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }else{
                                myRef2.child(uid).child("AGENDA").child(lista_ano.onClick).child(lista_um.onClick).child(lista_dois.onClick2).removeValue();
                                Intent intent = new Intent(getBaseContext(), tela_da_professora.class);
                                startActivity(intent);
                            }
                        }
                    });

            builder1.setNegativeButton(
                    "CANCELAR",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

