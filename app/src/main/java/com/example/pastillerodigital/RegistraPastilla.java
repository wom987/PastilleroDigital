package com.example.pastillerodigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.work.Data;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegistraPastilla extends AppCompatActivity {
    Spinner opciones, spFrecuencia, spDuracion;
    Button btnGuardarPa, btnMostraH;
    EditText txtNombre;
    TextView txvHora;


    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private  int minuto, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_pastilla);
        txtNombre= findViewById(R.id.txtMedicamento);
        txvHora=findViewById(R.id.txvHoraP);
        opciones=findViewById(R.id.spMedida);
        spDuracion= findViewById(R.id.spDuracion);
        spFrecuencia= findViewById(R.id.spFrecuencia);
        btnGuardarPa= findViewById(R.id.btnGuardarMedi);
        btnMostraH=findViewById(R.id.btnHoraP);

        AppDataBase db = Room.databaseBuilder(RegistraPastilla.this,
                AppDataBase.class,"dbPastillas").allowMainThreadQueries().build();




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.Duracion, android.R.layout.simple_spinner_item);
        spDuracion.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.Frecuencia, android.R.layout.simple_spinner_item);
        spFrecuencia.setAdapter(adapter3);

        btnGuardarPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tag= generateKey();
                Long alertTime= calendar.getTimeInMillis()-System.currentTimeMillis();
                int random = (int)(Math.random()*50+1);


                new SweetAlertDialog(RegistraPastilla.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Pastilla")
                        .setContentText("¿Desea guardar la alarma?")
                        .setConfirmText("Si")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                //insertando Pastilla
                                Pastilla pastilla= new Pastilla(

                                        txtNombre.getText().toString(),
                                        opciones.getSelectedItem().toString(),
                                        spDuracion.getSelectedItem().toString(),
                                        spFrecuencia.getSelectedItem().toString(),
                                        txvHora.getText().toString()
                                );
                                Long reg= db.pastillaDao ().insert(pastilla);
                                Toast.makeText(getApplicationContext(),
                                        "Alarma Guardada",
                                        Toast.LENGTH_SHORT).show();
                                Data data = guardarData("Medicamento:","tomar: "+txtNombre.getText().toString(), random);
                                WorkManagerNot.guardarNoti(alertTime,data,tag);
                                Intent intent = new Intent(getBaseContext(),Inicio.class);
                                startActivity(intent);
                            }
                        })
                        .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

            }
        });

        btnMostraH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora=actual.get(Calendar.HOUR_OF_DAY);
                minuto=actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY,h);
                        calendar.set(Calendar.MINUTE,m);

                        txvHora.setText(String.format("%02d:%02d",h,m));

                    }
                },hora,minuto,false);
                timePickerDialog.show();
            }
        });


    }

    private String generateKey()
    {
        return UUID.randomUUID().toString();
    }

    private Data guardarData(String titulo, String detalle, int idNoti)
    {
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putInt("id noti",idNoti).build();
    }


}