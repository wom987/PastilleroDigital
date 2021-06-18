package com.example.pastillerodigital;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Cita.class,Pastilla.class},version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CitaDao citaDao();
    public  abstract PastillaDao pastillaDao();
}
