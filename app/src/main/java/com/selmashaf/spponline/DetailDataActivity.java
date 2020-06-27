package com.selmashaf.spponline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DetailDataActivity extends AppCompatActivity {

    private TextView getNIM, getName, getJurusan, getPendaftaran, getPasopati, getDpp, getHeregistrasi, getSpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        getNIM = findViewById(R.id.MyNim);
        getName = findViewById(R.id.MyName);
        getJurusan = findViewById(R.id.MyJurusan);
        getPendaftaran = findViewById(R.id.MyPendaftaran);
        getPasopati = findViewById(R.id.MyPasopati);
        getDpp = findViewById(R.id.MyDpp);
        getHeregistrasi = findViewById(R.id.MyHeregistrasi);
        getSpp = findViewById(R.id.MySpp);
        getDetailData();
    }

    //Mendapatkan data yang akan ditampilkan secara detail
    private void getDetailData(){
        Pembayaran pembayaran = (Pembayaran) getIntent().getSerializableExtra("detail");

        //Menampilkan data Pembayaran pada activity
        if(pembayaran != null){
            getNIM.setText(pembayaran.getNim());
            getName.setText(pembayaran.getNama());
            getJurusan.setText(pembayaran.getJurusan());
            getPendaftaran.setText(pembayaran.getPendaftaran());
            getPasopati.setText(pembayaran.getPasopati());
            getDpp.setText(pembayaran.getDpp());
            getHeregistrasi.setText(pembayaran.getHeregistrasi());
            getSpp.setText(pembayaran.getSpp());
        }
    }
}