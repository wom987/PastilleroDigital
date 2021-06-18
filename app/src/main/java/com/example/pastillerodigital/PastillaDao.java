package com.example.pastillerodigital;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PastillaDao {

    @Query("SELECT * FROM pastilla")
    List<Pastilla> getAll();

    @Insert
    Long insert(Pastilla pastilla);
}
