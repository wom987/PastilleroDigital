package com.example.pastillerodigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.work.Data;
import androidx.work.WorkManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegistrarCita extends AppCompatActivity {


    TextView tvFechaS,tvHoraS;
    EditText edt_lugar;
    Button btnGuardar,btnPrueba2, btnFecha, btnHora;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private  int minuto, hora , mes , dia ,anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cita);
        AppDataBase db = Room.databaseBuilder(RegistrarCita.this,
                AppDataBase.class,"dbCita").allowMainThreadQueries().build();


        tvFechaS=findViewById(R.id.txvFecha);
        tvHoraS=findViewById(R.id.txvHora);
        edt_lugar=findViewById(R.id.edt_Lugar);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnFecha=findViewById(R.id.btnFecha);
        btnHora=findViewById(R.id.btnHora);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tag= generateKey();
                Long alertTime= calendar.getTimeInMillis()-System.currentTimeMillis();
                int random = (int)(Math.random()*50+1);


               //insertando cita
                // 2. Mensaje de confirmacion
                new SweetAlertDialog(RegistrarCita.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Registro de cita")
                        .setContentText("¿Desea Guardar la cita a esa hora y dia?")
                        .setConfirmText("Si")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Cita cita= new Cita(
                                        tvFechaS.getText().toString(),
                                        tvHoraS.getText().toString(),
                                        edt_lugar.getText().toString()
                                );
                                Long reg= db.citaDao().insert(cita);
                                Toast.makeText(getApplicationContext(),
                                        "Alarma Guardada",
                                        Toast.LENGTH_LONG).show();
                                sDialog.dismissWithAnimation();

                                //dato al mostrar en la notificacion
                                Data data = guardarData("Cita Prevista "," "+edt_lugar.getText().toString()+"\n hora de cita: "+tvHoraS.getText().toString()+" Fecha de la cita:"+tvFechaS.getText().toString(), random);
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

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anio = actual.get(calendar.YEAR);
                mes = actual.get(calendar.MONTH);
                dia = actual.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH,d);
                        calendar.set(Calendar.MONTH,m);
                        calendar.set(Calendar.YEAR,y);

                        SimpleDateFormat format= new SimpleDateFormat("dd/MM/YYYY");
                        String strDate = format.format(calendar.getTime());
                        tvFechaS.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();

            }
        });
        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora=actual.get(Calendar.HOUR_OF_DAY);
                minuto=actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY,h);
                        calendar.set(Calendar.MINUTE,m);

                        tvHoraS.setText(String.format("%02d:%02d",h,m));

                    }
                },hora,minuto,false);
                timePickerDialog.show();
            }
        });
    }//fin del oncreate

    //generando una llave random para conectar con la notificacion
    private String generateKey()
    {
        return UUID.randomUUID().toString();
    }
    //detalles al mostrar en la notificacion
    private Data guardarData(String titulo, String detalle, int idNoti)
    {
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putInt("id noti",idNoti).build();
    }

}