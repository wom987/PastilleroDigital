package com.example.pastillerodigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MostarCitas extends AppCompatActivity {
TextView txvMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostar_citas);
        AppDataBase db = Room.databaseBuilder(MostarCitas.this,
        AppDataBase.class,"dbCita").allowMainThreadQueries().build();

        txvMostrar=findViewById(R.id.txvMostrar);

        //Consultando registros de la base de datos
        List<Cita> lista = db.citaDao().getAll();
        String valores = "";
        //Recorrer la lista de los registros
        for(int i=0; i<lista.size();i++){
            //Concatenar los resultados
            valores += " Hora: "+ lista.get(i).fecha+" Fecha: "+lista.get(i).hora +" Lugar: " +lista.get(i).lugar+ "\n\n";

        }//Fin del FOR
        //Asignar el acumulador al textview de los resultados
        txvMostrar.setText(valores);
    }
}