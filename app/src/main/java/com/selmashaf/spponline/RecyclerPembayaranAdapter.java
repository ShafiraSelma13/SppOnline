package com.selmashaf.spponline;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class RecyclerPembayaranAdapter extends RecyclerView.Adapter<RecyclerPembayaranAdapter.ViewHolder> {

    //Deklarasi Variable
    private ArrayList<Pembayaran> daftarPembayaran;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerPembayaranAdapter(ArrayList<Pembayaran> daftarPembayaran, Context context) {

        //Inisialisasi data yang akan digunakan
        this.daftarPembayaran = daftarPembayaran;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbPembayaran").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //Deklarasi View yang akan digunakan
        private TextView Nim, Nama;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);
            Nim = itemView.findViewById(R.id.nim);
            Nama = itemView.findViewById(R.id.nama);
            item = itemView.findViewById(R.id.cvMain);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Deklarasi Variable untuk mendapatkan data dari Database melalui Array
        final String getNIM = daftarPembayaran.get(position).getNim();
        final String getNama = daftarPembayaran.get(position).getNama();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.Nim.setText(getNIM);
        holder.Nama.setText(getNama);

        //Detail
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pembayaran pembayaran = appDatabase.pembayaranDAO()
                        .selectDetailPembayaran(daftarPembayaran.get(position).getNim());
                context.startActivity(new Intent(context, DetailDataActivity.class).putExtra("detail", pembayaran));
            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pilih Aksi")
                        .setItems(menuPilihan, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                    /*
                                     Menjalankan Perintah Edit Data
                                     Menggunakan Bundle untuk mengambil data yang akan Diedit
                                     */
                                        onEditData(position, context);
                                        break;

                                    case 1:
                                    /*
                                     Menjalankan Perintah Delete Data
                                     Akan dibahas pada Tutorial selanjutnya
                                     */
                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    //Menghapus Data dari Room Database yang dipilih oleh user
    private void onDeleteData(int position){
        appDatabase.pembayaranDAO().deletePembayaran(daftarPembayaran.get(position));
        daftarPembayaran.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarPembayaran.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    //Mengirim Data yang akan diedit dari ArrayList berdasarkan posisi item pada RecyclerView
    private void onEditData(int position, Context context){
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarPembayaran.get(position)));
        ((Activity)context).finish();
    }
    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return daftarPembayaran.size();
    }
}