package com.example.kanlane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Button mAccountBtn;
    Button mSosButton;
    DatabaseReference databaseReference;
    public String emailFrom, emailTo;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference mDataBase;
    String userData = "User";
    String currentUser;

    //EmailTo это Email
    //EmailFrom это UserBinder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Для полноэкранного режима и скрывания шапки.
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Базы данных
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(userData);
        mAccountBtn = findViewById(R.id.accountBtn);
        mSosButton = findViewById(R.id.sosButton);
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Кнопка SOS
        mSosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference().child("Sos");
                Toast.makeText(MainActivity.this, "Сообщение отправлено!", Toast.LENGTH_LONG).show();

                Sos sos = new Sos(null, null);

                databaseReference.push().setValue(sos);


            }
        });

        //Кнопка настроек аккаунта (переход в Account.activity)
        mAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Account.class));

            }
        });
    }

}