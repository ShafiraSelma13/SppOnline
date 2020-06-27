package com.selmashaf.spponline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataActivity extends AppCompatActivity {

    //Deklarasi Variable
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Pembayaran> daftarPembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        getSupportActionBar().setTitle("Data Pembayaran");

        //Inisialisasi ArrayList
        daftarPembayaran = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbPembayaran").allowMainThreadQueries().build();

        /*
         * Mengambil data Pembayaran dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarPembayaran)
         */
        daftarPembayaran.addAll(Arrays.asList(database.pembayaranDAO().readDataPembayaran()));

        recyclerView = findViewById(R.id.dataItem);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerPembayaranAdapter(daftarPembayaran, ReadDataActivity.this);
        recyclerView.setAdapter(adapter);
    }
}