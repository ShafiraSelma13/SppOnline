package com.selmashaf.spponline;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pembayaran.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract PembayaranDAO pembayaranDAO();
}
