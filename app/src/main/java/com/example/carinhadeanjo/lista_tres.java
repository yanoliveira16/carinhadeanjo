package com.example.carinhadeanjo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class lista_tres extends AppCompatActivity {
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef2 = database.child("P3");
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (tela_do_aluno_prof.prof.contains("prof2")==true){
            uid = tela_do_aluno_prof.id_aluno;
        }else{
            uid = login_or_register.id;
        }


        setContentView(R.layout.activity_lista_tres);




            final TextView a1 = (TextView) findViewById(R.id.aluno_agenda3);
            a1.setText(tela_de_alunos.onClick3 + " \n " + lista_dois.onClick2 + " \n ");
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
        myRef2.child(uid).child("Agenda").child(lista_um.onClick).child(lista_dois.onClick2).child("atvs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nn = dataSnapshot.getValue(String.class);
                final TextView a2 = (TextView) findViewById(R.id.atvs);
                if (nn.equals("")){
                    a2.setPadding(0,-1,0,-20);
                    a2.setActivated(false);
                    View aa=findViewById(R.id.atividade_de_sala);
                    aa.setPadding(0,-25,0,-10);
                    a2.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    aa.setPadding(0,-25,0,-10);
                    a3.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    aa.setPadding(0,-25,0,-10);
                    a4.setActivated(false);
                    a4.setPadding(0,-1,0,-20);
                    a4.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    aa.setPadding(0,-25,0,-10);
                    a5.setActivated(false);
                    a5.setPadding(0,-1,0,-20);
                    a5.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a6.setPadding(0,-1,0,-20);
                    a6.setActivated(false);
                    View aa=findViewById(R.id.avisos);
                    aa.setPadding(0,-25,0,-10);
                    a6.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                final TextView a7 = (TextView) findViewById(R.id.medicação);
                if (nn.equals("")){
                    a7.setPadding(0,-1,0,-20);
                    a7.setActivated(false);
                    View aa=findViewById(R.id.medicação);
                    aa.setPadding(0,-25,0,-10);
                    a7.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
                a7.setText(nn);
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
                    a8.setPadding(0,-1,0,-20);
                    a8.setActivated(false);
                    View aa=findViewById(R.id.iremedio);
                    aa.setPadding(0,-25,0,-10);
                    a8.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
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
                    a9.setPadding(0,-1,0,-20);
                    a9.setActivated(false);
                    View aa=findViewById(R.id.idosagem);
                    aa.setPadding(0,-25,0,-10);
                    a9.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
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
                    a10.setPadding(0,-1,0,-20);
                    a10.setActivated(false);
                    View aa=findViewById(R.id.ihorario);
                    aa.setPadding(0,-25,0,-10);
                    a10.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
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
                    a11.setPadding(0,-1,0,-20);
                    a11.setActivated(false);
                    View aa=findViewById(R.id.apresent);
                    aa.setPadding(0,-25,0,-10);
                    a11.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
                a11.setText(nn);
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
                    a11.setPadding(0,-1,0,-20);
                    a11.setActivated(false);
                    View aa=findViewById(R.id.lanche_mat);
                    aa.setPadding(0,-25,0,-10);
                    a11.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a12.setPadding(0,-1,0,-20);
                    a12.setActivated(false);
                    View aa=findViewById(R.id.Almoço);
                    aa.setPadding(0,-25,0,-10);
                    a12.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a13.setPadding(0,-1,0,-20);
                    a13.setActivated(false);
                    View aa=findViewById(R.id.lanche_vesp);
                    aa.setPadding(0,-25,0,-10);
                    a13.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a14.setPadding(0,-1,0,-20);
                    a14.setActivated(false);
                    View aa=findViewById(R.id.jantar);
                    aa.setPadding(0,-25,0,-10);
                    a14.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a15.setPadding(0,-1,0,-20);
                    a15.setActivated(false);
                    View aa=findViewById(R.id.comp_integral);
                    aa.setPadding(0,-25,0,-10);
                    a15.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a15.setPadding(0,-1,0,-20);
                    a15.setActivated(false);
                    View aa=findViewById(R.id.aspec_fisio);
                    aa.setPadding(0,-25,0,-10);
                    a15.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a16.setPadding(0,-1,0,-20);
                    a16.setActivated(false);
                    View aa=findViewById(R.id.f_providenciar);
                    aa.setPadding(0,-25,0,-10);
                    a16.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a17.setPadding(0,-1,0,-20);
                    a17.setActivated(false);
                    View aa=findViewById(R.id.atv_rotina);
                    aa.setPadding(0,-25,0,-10);
                    a17.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
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
                    a18.setPadding(0,-1,0,-20);
                    a18.setActivated(false);
                    View aa=findViewById(R.id.obsvervacao);
                    aa.setPadding(0,-25,0,-10);
                    a18.setVisibility(View.INVISIBLE);
                    aa.setVisibility(View.INVISIBLE);
                }
                a18.setText(nn);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        chamarfoto();
        }

        public void chamarfoto(){
            ImageView myImage2 = (ImageView) findViewById(R.id.profile_foto);
            myImage2.setImageBitmap(lista_dois.my_image2);
        }
    }

