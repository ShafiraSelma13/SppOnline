package com.selmashaf.spponline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    private Button btnSend;
    private Button btnLogout;

    private EditText mRekeningAnda;
    private EditText mRekeningPenerima;
    private EditText mJumlahTransfer;

    Spinner kodespinner;
    String kode;
    ArrayList<String> daftar_kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        mRekeningAnda = findViewById(R.id.rekandaedit);
        mRekeningPenerima = findViewById(R.id.rekpenerimaedit);
        mJumlahTransfer = findViewById(R.id.jumtfedit);

        kodespinner = findViewById(R.id.kodespin);
        btnSend = findViewById(R.id.sendbtn);
        btnLogout = findViewById(R.id.logtfbtn);

        daftar_kode = new ArrayList<String>();

        daftar_kode.add("BANK BRI");
        daftar_kode.add("008 - BANK MANDIRI");
        daftar_kode.add("009 - BANK BNI");
        daftar_kode.add("011 - BANK DANAMON");
        daftar_kode.add("013 - BANK PERMATA");
        daftar_kode.add("014 - BANK BCA");
        daftar_kode.add("016 - BANK MAYBANK INDONESIA");
        daftar_kode.add("019 - BANK PANIN");
        daftar_kode.add("022 - BANK CIMB NIAGA");
        daftar_kode.add("023 - BANK UOB INDONESIA");
        daftar_kode.add("028 - BANK OCBC NISP");
        daftar_kode.add("031 - CITIBANK INDONESIA");
        daftar_kode.add("036 - BANK CCB INDONESIA");
        daftar_kode.add("037 - BANK ARTHA GRAHA");
        daftar_kode.add("042 - MUFG BANK , LTD");
        daftar_kode.add("046 - BANK DBS");
        daftar_kode.add("050 - BANK STANCHART");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,daftar_kode);

        kodespinner.setAdapter(adapter);

        kodespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kode = kodespinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rekandaedit = mRekeningAnda.getText().toString().trim();
                String rekpenerimaedit = mRekeningPenerima.getText().toString().trim();
                String jumtfedit = mJumlahTransfer.getText().toString().trim();

                String rekeninganda  = (rekandaedit);
                String rekeningpenerima = (rekpenerimaedit);
                String jumlahtf = (jumtfedit);

                AlertDialog.Builder builder = new AlertDialog.Builder(TransferActivity.this);
                builder.setTitle("                      m-Transfer").
                        setMessage("Dari : " + rekeninganda + "\n" +"Kepada : " + rekeningpenerima + "\n" +"Jumlah : Rp. " + jumlahtf);

                builder.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        PrintActivity.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TransferActivity.this);
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
    }
}
