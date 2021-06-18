package com.example.pastillerodigital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class Inicio extends AppCompatActivity {
    FragmentRegistro fragmentRegistro;
    FragmentPromedio fragmentPromedio;
    Button btnHistorial;
    Button btnMostraPasti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnMostraPasti=findViewById(R.id.btnHistorialPastilla);
        btnHistorial=findViewById(R.id.btnHistorial);
        fragmentPromedio= new FragmentPromedio();
        fragmentRegistro = new FragmentRegistro();
        getSupportFragmentManager().beginTransaction().add(R.id.frlContenedor,fragmentRegistro).commit();

        btnHistorial.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),MostarCitas.class);
            startActivity(intent);
        }
    });
        btnMostraPasti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MostrarPastilla.class);
                startActivity(intent);
            }
        });

    }

    public  void  onClick(View view)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId())
        {
            case R.id.btnFragmentUno:
                transaction.replace(R.id.frlContenedor,fragmentRegistro);
                transaction.addToBackStack(null);
                break;
            case R.id.btnFragmentDos:
                transaction.replace(R.id.frlContenedor,fragmentPromedio);
                transaction.addToBackStack(null);
                break;

        }
        transaction.commit();
    }
}

