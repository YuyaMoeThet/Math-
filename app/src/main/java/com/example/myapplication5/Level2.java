package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Level2 extends AppCompatActivity {

    private TextView tvQuestion2, tvScore2, tvQuestionNo2, tvTimer1;
    private RadioGroup radioGroup2;
    private RadioButton rbtn1, rbtn2, rbtn3;
    private Button btnNext2;

    int totalQuestions2;
    int qCounter2 = 0;

    private QuestionModel currentQuestion2;

    ColorStateList dfRbColor2;
    boolean answered;

    int score2;

    CountDownTimer countDownTimer;

    private List<QuestionModel> questionlist;

    private int[] RandomQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        tvQuestion2 = findViewById(R.id.textQuestion2);
        tvQuestionNo2 = findViewById(R.id.textQuestionNo2);
        tvScore2 = findViewById(R.id.textScore2);
        tvScore2.setText("Score: 0");
        tvTimer1 = findViewById(R.id.textTimer1);

        radioGroup2 = findViewById(R.id.radioGroup2);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);

        btnNext2 = findViewById(R.id.btnNext2);

        dfRbColor2 = rbtn1.getTextColors();

        questionlist = new ArrayList<>();
        addQuestion();

        totalQuestions2 = questionlist.size();

        RandomQuestion = new int[10];
        RandomQuestion = new RandomQuestion().getRandomGenerateNoArr();

        showNextQuestion();


        btnNext2.setOnClickListener(v -> {
            if (answered == false) {
                if (rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked()) {
                    checkAnswer();
                    countDownTimer.cancel();
                } else {
                    Toast.makeText(Level2.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }

            if(btnNext2.getText().toString().equals("Finish")){
                Intent i = new Intent(Level2.this,Score.class);

                i.putExtra("values",score2);
                startActivity(i);
            }
        });

    }

    private void addQuestion() {
        questionlist.add(new QuestionModel("6+6", "11", "12", "13", 2));
        questionlist.add(new QuestionModel("9%3", "12", "6", "3", 3));
        questionlist.add(new QuestionModel("4*2", "8", "9", "10", 1));
        questionlist.add(new QuestionModel("1-1", "2", "0", "1", 2));
        questionlist.add(new QuestionModel("8%4", "12", "4", "2", 3));
        questionlist.add(new QuestionModel("6-6", "0", "12", "1", 1));
        questionlist.add(new QuestionModel("12%2", "4", "6", "8", 2));
        questionlist.add(new QuestionModel("0*0", "1", "3", "0", 3));
        questionlist.add(new QuestionModel("1*9", "8", "9", "7", 2));
        questionlist.add(new QuestionModel("5+6", "11", "12", "13", 1));
        totalQuestions2 = questionlist.size();

    }

    private void showNextQuestion() {
        radioGroup2.clearCheck();
        rbtn1.setTextColor(dfRbColor2);
        rbtn2.setTextColor(dfRbColor2);
        rbtn3.setTextColor(dfRbColor2);

        if (qCounter2 < totalQuestions2) {
            Timer();
            currentQuestion2 = questionlist.get(qCounter2);
            currentQuestion2 = questionlist.get(RandomQuestion[qCounter2]);
            tvQuestion2.setText(currentQuestion2.getQuestion());
            rbtn1.setText(currentQuestion2.getOption1());
            rbtn2.setText(currentQuestion2.getOption2());
            rbtn3.setText(currentQuestion2.getOption3());

            qCounter2++;
            btnNext2.setText("SUBMIT");
            radioGroup2.getChildAt(0).setEnabled(true);
            radioGroup2.getChildAt(1).setEnabled(true);
            radioGroup2.getChildAt(2).setEnabled(true);

            tvQuestionNo2.setText("Question" + qCounter2 + "/" + totalQuestions2);
            answered = false;
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup2.getCheckedRadioButtonId());
        int answerNo = radioGroup2.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion2.getCorrectAnsNo()) {
            score2++;
            tvScore2.setText("Score: " + score2);
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level2.this,R.raw.correct);
            mediaPlayer.start();
        }

        if (answerNo != currentQuestion2.getCorrectAnsNo()) {
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level2.this,R.raw.wrong);
            mediaPlayer.start();


        }
        rbtn1.setTextColor(Color.RED);
        rbtn2.setTextColor(Color.RED);
        rbtn3.setTextColor(Color.RED);

        switch (currentQuestion2.getCorrectAnsNo()) {
            case 1:
                rbtn1.setTextColor(Color.GREEN);
                break;
            case 2:
                rbtn2.setTextColor(Color.GREEN);
                break;
            case 3:
                rbtn3.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter2 < totalQuestions2) {
            btnNext2.setText("Next");
            radioGroup2.getChildAt(0).setEnabled(false);
            radioGroup2.getChildAt(1).setEnabled(false);
            radioGroup2.getChildAt(2).setEnabled(false);
        } else {
            btnNext2.setText("Finish");
        }
    }


    private void Timer() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer1.setText("00: " + l / 1000);

            }

            @Override
            public void onFinish() {
                showNextQuestion();

            }
        }.start();
    }
}