package com.selmashaf.spponline;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PembayaranDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPembayaran(Pembayaran pembayaran);

    @Query("SELECT * FROM tPembayaran")
    Pembayaran[] readDataPembayaran();

    @Update
    int updatePembayaran(Pembayaran pembayaran);

    @Delete
    int deletePembayaran(Pembayaran pembayaran);

    @Query("SELECT * FROM tpembayaran WHERE nim_mahasiswa = :nim LIMIT 1")
    Pembayaran selectDetailPembayaran(String nim);
}
