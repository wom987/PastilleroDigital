package com.example.pastillerodigital;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CitaDao {

    @Query("SELECT * FROM Cita")
    List<Cita> getAll();

    @Insert
    Long insert(Cita cita);
}
