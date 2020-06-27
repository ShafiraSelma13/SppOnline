package com.selmashaf.spponline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    //Deklarasi Variable
    private EditText Nama, Jurusan, Pendaftaran, Pasopati, Dpp, Heregistrasi, Spp;
    private AppDatabase database;
    private Button btnSave;
    private Pembayaran pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Nama = findViewById(R.id.namaedit);
        Jurusan = findViewById(R.id.jurusanedit);
        Pendaftaran = findViewById(R.id.oneedit);
        Pasopati = findViewById(R.id.twoedit);
        Dpp = findViewById(R.id.threeedit);
        Heregistrasi = findViewById(R.id.fouredit);
        Spp = findViewById(R.id.fiveedit);
        btnSave = findViewById(R.id.savebtn);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbPembayaran").build();

        //Menampilkan data pembayaran yang akan di edit
        getDataPembayaran();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pembayaran.setNama(Nama.getText().toString());
                pembayaran.setJurusan(Jurusan.getText().toString());
                pembayaran.setPendaftaran(Pendaftaran.getText().toString());
                pembayaran.setPasopati(Pasopati.getText().toString());
                pembayaran.setDpp(Dpp.getText().toString());
                pembayaran.setHeregistrasi(Heregistrasi.getText().toString());
                pembayaran.setSpp(Spp.getText().toString());
                updateData(pembayaran);
            }
        });
    }

    //Method untuk menapilkan data pembayaran yang akan di edit
    private void getDataPembayaran(){
        //Mendapatkan data dari Intent
        pembayaran = (Pembayaran) getIntent().getSerializableExtra("data");

        Nama.setText(pembayaran.getNama());
        Jurusan.setText(pembayaran.getJurusan());
        Pendaftaran.setText(pembayaran.getPendaftaran());
        Pasopati.setText(pembayaran.getPasopati());
        Dpp.setText(pembayaran.getDpp());
        Heregistrasi.setText(pembayaran.getHeregistrasi());
        Spp.setText(pembayaran.getSpp());
    }

    //Menjalankan method Update Data
    @SuppressLint("StaticFieldLeak")
    private void updateData(final Pembayaran pembayaran){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.pembayaranDAO().updatePembayaran(pembayaran);
            }

            @Override
            protected void onPostExecute(Integer status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(EditActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
        finish();
    }
}