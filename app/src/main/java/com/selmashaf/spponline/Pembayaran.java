package com.selmashaf.spponline;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tPembayaran")
public class Pembayaran implements Serializable {

    // Membuat kolom NIM sebagai Primary Key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nim_mahasiswa")
    private String nim;

    // Membuat kolom Nama
    @ColumnInfo(name = "nama_mahasiswa")
    private String nama;

    //Membuat kolom Jurusan
    @ColumnInfo(name = "jurusan")
    private String jurusan;

    //Membuat kolom Uang Pendaftaran
    @ColumnInfo(name = "uang_pendaftaran")
    private String pendaftaran;

    //Membuat kolom Biaya Pasopati
    @ColumnInfo(name = "biaya_pasopati")
    private String pasopati;

    //Membuat kolom Biaya Pembangunan Dpp
    @ColumnInfo(name = "biaya_dpp")
    private String dpp;

    //Membuat kolom Biaya Heregistrasi
    @ColumnInfo(name = "biaya_heregistrasi")
    private String heregistrasi;

    //Membuat kolom Biaya Pendidikan Spp
    @ColumnInfo(name = "biaya_spp")
    private String spp;

    //Method untuk mengambil data NIM
    @NonNull
    public String getNim() {
        return nim;
    }

    //Method untuk memasukan data NIM
    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    //Method untuk mengambil data Nama
    public String getNama() {
        return nama;
    }

    //Method untuk memasukan data Nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    //Method untuk mengambil data Jurusan
    public String getJurusan() {
        return jurusan;
    }

    //Method untuk memasukan data Jurusan
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    //Method untuk mengambil data Pendaftaran
    public String getPendaftaran() {
        return pendaftaran;
    }

    //Method untuk memasukan data Pendaftaran
    public void setPendaftaran(String pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    //Method untuk mengambil data Pasopati
    public String getPasopati() {
        return pasopati;
    }

    //Method untuk memasukan data Pasopati
    public void setPasopati(String pasopati) {
        this.pasopati = pasopati;
    }

    //Method untuk mengambil data Dpp
    public String getDpp() {
        return dpp;
    }

    //Method untuk memasukan data Dpp
    public void setDpp(String dpp) {
        this.dpp = dpp;
    }

    //Method untuk mengambil data Heregistrasi
    public String getHeregistrasi() {
        return heregistrasi;
    }

    //Method untuk memasukan data Heregistrasi
    public void setHeregistrasi(String heregistrasi) {
        this.heregistrasi = heregistrasi;
    }

    //Method untuk mengambil data Spp
    public String getSpp() {
        return spp;
    }

    //Method untuk memasukan data Spp
    public void setSpp(String spp) {
        this.spp = spp;
    }


}
