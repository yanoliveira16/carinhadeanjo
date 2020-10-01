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
    String uid;
    String v;

    boolean s1 = false;
    boolean s2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_tres);

        if (tela_de_carregamento.qual.contains("1")==true){
            uid = tela_do_aluno_prof.id_aluno;
            final TextView a1 = (TextView) findViewById(R.id.aluno_agenda3);
            v = tela_de_alunos.onClick3 + " \n " + lista_dois.onClick2;
            a1.setText(v);
        }else{
            uid = login_or_register.id;
            final TextView a2 = (TextView) findViewById(R.id.aluno_agenda3);
            v = tela_de_carregamento.nnomeAluno + " \n " + lista_dois.onClick2;
            a2.setText(v);
        }



            myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("visto_data").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nn = dataSnapshot.getValue(String.class);
                    Log.d("AQUI", "AQUI PORRa" + nn);
                    final TextView a2 = (TextView) findViewById(R.id.aluno_agenda3);
                    a2.setText(v + " \n " + "Ciente: " + nn);
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

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });



        GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.RECTANGLE);
            gd.setStroke(5, Color.argb(100, 0, 0, 0)); // border width and color
            gd.setCornerRadius(60.40f);

        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("falta").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                Log.d("AQUI", "AQUI PORRa" + nn);
                final TextView a1 = (TextView) findViewById(R.id.falta);
                a1.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("msg").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                Log.d("AQUI", "AQUI PORRa" + nn);
                final TextView a1 = (TextView) findViewById(R.id.msg);
                a1.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("atvs").addListenerForSingleValueEvent(new ValueEventListener() {
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

        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("atvs2").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("comportamento").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("dever").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("aviso").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a6 = (TextView) findViewById(R.id.aviso);
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("imedicacao").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("iremedio").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("idosagem").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("ihorario").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("apresentou").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a11 = (TextView) findViewById(R.id.apresentou); if (nn.equals("")){
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


        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("ilanchematutino").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("ialmoço").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("ilanchevespertino").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("ijanta").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("icomportamento").addListenerForSingleValueEvent(new ValueEventListener() {
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
       myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("isono").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("iprovidenciar").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("iatv").addListenerForSingleValueEvent(new ValueEventListener() {
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("obs3").addListenerForSingleValueEvent(new ValueEventListener() {
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
            String nn = et2.getText().toString();
            msg += nn + "";
            ciente();
        }
    }


    public void ciente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());

        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("visto_data").setValue(currentDateandTime);
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("visto").setValue("ok");
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("msg").setValue(msg);

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
        }
    }

