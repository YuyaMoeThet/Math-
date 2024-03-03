package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends AppCompatActivity {

    Button btnLev0;
    Button btnLev1;
    Button btnLev2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        btnLev0 = findViewById(R.id.btnLev0);
        btnLev1 = findViewById(R.id.btnLev1);
        btnLev2 = findViewById(R.id.btnLev2);

        btnLev0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectLevel.this,Level0.class);
                startActivity(i);
            }
        });

        btnLev1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(SelectLevel.this,Level1.class);
                startActivity(in);
            }
        });

        btnLev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLevel.this,Level2.class);
                startActivity(intent);
            }
        });

    }
}