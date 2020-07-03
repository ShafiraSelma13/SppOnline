package com.selmashaf.spponline;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrintActivity extends AppCompatActivity {
    private static final String LOG_TAG = PrintActivity.class.getSimpleName();
    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION = "com.selmashaf.spponline.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private final String COLOR_KEY = "color";

    private TextView mShowTextView;
    private int mColor;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.selmashaf.spponline";

    private Button btnLogout;

    private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        mShowTextView = findViewById(R.id.transaksi_txt);
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowTextView.setBackgroundColor(mColor);

        btnLogout = findViewById(R.id.logbtn);

        //Memberikan action pada button logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PrintActivity.this);
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

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void reset(View view){
        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }
}