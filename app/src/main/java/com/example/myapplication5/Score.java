package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    private TextView Score;

    int scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Score = findViewById(R.id.textScore3);
        Intent intent = getIntent();
        scores = intent.getIntExtra("values",0);
        Score.setText("You Scored "+ scores + " Out Of 10");

        Button btn1 = (Button) findViewById(R.id.btnNextLevel);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Score.this,SelectLevel.class);
                startActivity(i);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btnExit);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Score.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}