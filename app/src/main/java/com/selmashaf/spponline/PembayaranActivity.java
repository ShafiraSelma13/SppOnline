package com.selmashaf.spponline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PembayaranActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOG_TAG = PembayaranActivity.class.getSimpleName();

    private TextView txtNama;
    private TextView txtJurusan;
    private TextView txtNim;
    private TextView txtPendaftaran;
    private TextView txtPasopati;
    private TextView txtDpp;
    private TextView txtHeregistrasi;
    private TextView txtSpp;
    private TextView txtJumlah;

    private EditText mNamaEdit;
    private EditText mJurusanEdit;
    private EditText mNimEdit;
    private EditText mPendaftaran;
    private EditText mPasopati;
    private EditText mDpp;
    private EditText mHeregistrasi;
    private EditText mSpp;

    private Button btnHitung;
    private Button btnSave, btnViewData;
    private Button btnLogout;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        txtNama = findViewById(R.id.nama);
        txtJurusan = findViewById(R.id.jurusan);
        txtJumlah = findViewById(R.id.jumlah);

        mNamaEdit = findViewById(R.id.namaedit);
        mJurusanEdit = findViewById(R.id.jurusanedit);
        mNimEdit = findViewById(R.id.nimedit);
        mPendaftaran = findViewById(R.id.oneedit);
        mPasopati = findViewById(R.id.twoedit);
        mDpp = findViewById(R.id.threeedit);
        mHeregistrasi = findViewById(R.id.fouredit);
        mSpp = findViewById(R.id.fiveedit);

        btnHitung = findViewById(R.id.hitungbtn);
        btnSave = findViewById(R.id.savebtn);
        btnSave.setOnClickListener(this);
        btnViewData = findViewById(R.id.show);
        btnViewData.setOnClickListener(this);
        btnLogout = findViewById(R.id.logbtn);

        //Memberikan action pada button hitung
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaedit = mNamaEdit.getText().toString().trim();
                String jurusanedit = mJurusanEdit.getText().toString().trim();
                String nimedit = mNimEdit.getText().toString().trim();
                String oneedit = mPendaftaran.getText().toString().trim();
                String twoedit = mPasopati.getText().toString().trim();
                String threeedit = mDpp.getText().toString().trim();
                String fouredit = mHeregistrasi.getText().toString().trim();
                String fiveedit = mSpp.getText().toString().trim();

                double pendaftaran = Double.parseDouble(oneedit);
                double pasopati = Double.parseDouble(twoedit);
                double dpp = Double.parseDouble(threeedit);
                double heregistrasi = Double.parseDouble(fouredit);
                double spp = Double.parseDouble(fiveedit);
                double hitung = (pendaftaran + pasopati + dpp + heregistrasi + spp);
                txtJumlah.setText("JUMLAH : " + hitung);

            }
        });
        //Memberikan action pada button logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PembayaranActivity.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("Are You Sure, That You Want to Back Home?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        HomeActivity.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });


        //Inisialisasi dan memanggil Room Database
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbPembayaran") //Nama File Database yang akan disimpan
                .build();
    }

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData(final Pembayaran pembayaran) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.pembayaranDAO().insertPembayaran(pembayaran);
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(PembayaranActivity.this, "Status Row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClick(View vi) {
        switch (vi.getId()) {
            case R.id.savebtn:

                //Mengecek Data NIM dan Nama
                if (mNimEdit.getText().toString().isEmpty() || mNamaEdit.getText().toString().isEmpty()) {
                    Toast.makeText(PembayaranActivity.this, "NIM atau Nama TIdak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //Membuat Instance/Objek Dari Class Entity Pembayaran
                    Pembayaran data = new Pembayaran();

                    //Memasukan data yang diinputkan user pada database
                    data.setNim(mNimEdit.getText().toString());
                    data.setNama(mNamaEdit.getText().toString());
                    data.setJurusan(mJurusanEdit.getText().toString());
                    data.setPendaftaran(mPendaftaran.getText().toString());
                    data.setPasopati(mPasopati.getText().toString());
                    data.setDpp(mDpp.getText().toString());
                    data.setHeregistrasi(mHeregistrasi.getText().toString());
                    data.setSpp(mSpp.getText().toString());
                    insertData(data);

                    //Mengembalikan EditText menjadi seperti semula\
                    mNamaEdit.setText("");
                    mJurusanEdit.setText("");
                    mNimEdit.setText("");
                    mPendaftaran.setText("");
                    mPasopati.setText("");
                    mDpp.setText("");
                    mHeregistrasi.setText("");
                    mSpp.setText("");
                }
                break;

            case R.id.show:
                startActivity(new Intent(PembayaranActivity.this, ReadDataActivity.class));
                break;
        }
    }
    public void transfer(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, TransferActivity.class);
        startActivity(intent);
    }


}
