package com.example.pastillerodigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MostrarPastilla extends AppCompatActivity {

    TextView txvMostraP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pastilla);
        AppDataBase db = Room.databaseBuilder(MostrarPastilla.this,
                AppDataBase.class,"dbPastillas").allowMainThreadQueries().build();

        txvMostraP=findViewById(R.id.txvMostrarPastilla);

        //Consultando registros de la base de datos
        List<Pastilla> lista = db.pastillaDao().getAll();
        String valores = "";
        //Recorrer la lista de los registros
        for(int i=0; i<lista.size();i++){
            //Concatenar los resultados
            valores += " Identificador Pastilla: "+lista.get(i).idPastilla+ " Nombre: "+lista.get(i).pastilla+" Unidad: "+lista.get(i).unidad+" Duracion: "+lista.get(i).duracion+" Frecuencia: "+lista.get(i).frecuencia+" Hora: "+lista.get(i).hora+"\n\n";

        }//Fin del FOR
        //Asignar el acumulador al textview de los resultados
        txvMostraP.setText(valores);
    }
}