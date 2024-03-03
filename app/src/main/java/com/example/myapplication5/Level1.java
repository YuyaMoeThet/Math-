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

public class Level1 extends AppCompatActivity {

    private TextView tvQuestion1, tvScore1, tvQuestionNo1, tvTimer;
    private RadioGroup radioGroup1;
    private RadioButton rbtnOp1, rbtnOp2, rbtnOp3;
    private Button btnNext1;

    int totalQuestions1;
    int qCounter1 = 0;

    private QuestionModel currentQuestion1;

    ColorStateList dfRbColor1;
    boolean answered;

    int score1;

    CountDownTimer countDownTimer;

    private List<QuestionModel> questionlist;

    private int[] RandomQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        tvQuestion1 = findViewById(R.id.textQuestion1);
        tvQuestionNo1 = findViewById(R.id.textQuestionNo1);
        tvScore1 = findViewById(R.id.textScore1);
        tvScore1.setText("Score: 0");
        tvTimer = findViewById(R.id.textTimer);

        radioGroup1 = findViewById(R.id.radioGroup1);
        rbtnOp1 = findViewById(R.id.rbtnOp1);
        rbtnOp2 = findViewById(R.id.rbtnOp2);
        rbtnOp3 = findViewById(R.id.rbtnOp3);

        btnNext1 = findViewById(R.id.btnNext1);

        dfRbColor1 = rbtnOp1.getTextColors();

        questionlist = new ArrayList<>();
        addQuestion();

        totalQuestions1 = questionlist.size();

        RandomQuestion = new int[10];
        RandomQuestion = new RandomQuestion().getRandomGenerateNoArr();

        showNextQuestion();


        btnNext1.setOnClickListener(v -> {
            if (answered == false) {
                if (rbtnOp1.isChecked() || rbtnOp2.isChecked() || rbtnOp3.isChecked()) {
                    checkAnswer();
                    countDownTimer.cancel();
                } else {
                    Toast.makeText(Level1.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }

            if(btnNext1.getText().toString().equals("Finish")){
                Intent i = new Intent(Level1.this,Score.class);

                i.putExtra("values",score1);
                startActivity(i);
            }
        });

    }

    private void addQuestion() {
        questionlist.add(new QuestionModel("2*2", "5", "4", "8", 2));
        questionlist.add(new QuestionModel("4*3", "10", "8", "12", 3));
        questionlist.add(new QuestionModel("8+3", "11", "6", "12", 1));
        questionlist.add(new QuestionModel("5-2", "6", "3", "4", 2));
        questionlist.add(new QuestionModel("6*2", "4", "8", "12", 3));
        questionlist.add(new QuestionModel("4+4", "8", "7", "9", 1));
        questionlist.add(new QuestionModel("12-5", "6", "7", "8", 2));
        questionlist.add(new QuestionModel("1*1", "2", "3", "1", 3));
        questionlist.add(new QuestionModel("5*2", "12", "10", "7", 2));
        questionlist.add(new QuestionModel("9-9", "0", "1", "2", 1));
        totalQuestions1 = questionlist.size();

    }

    private void showNextQuestion() {
        radioGroup1.clearCheck();
        rbtnOp1.setTextColor(dfRbColor1);
        rbtnOp2.setTextColor(dfRbColor1);
        rbtnOp3.setTextColor(dfRbColor1);

        if (qCounter1 < totalQuestions1) {
            Timer();
            currentQuestion1 = questionlist.get(qCounter1);
            currentQuestion1 = questionlist.get(RandomQuestion[qCounter1]);
            tvQuestion1.setText(currentQuestion1.getQuestion());
            rbtnOp1.setText(currentQuestion1.getOption1());
            rbtnOp2.setText(currentQuestion1.getOption2());
            rbtnOp3.setText(currentQuestion1.getOption3());

            qCounter1++;
            btnNext1.setText("Submit");
            radioGroup1.getChildAt(0).setEnabled(true);
            radioGroup1.getChildAt(1).setEnabled(true);
            radioGroup1.getChildAt(2).setEnabled(true);

            tvQuestionNo1.setText("Question" + qCounter1 + "/" + totalQuestions1);
            answered = false;
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup1.getCheckedRadioButtonId());
        int answerNo = radioGroup1.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion1.getCorrectAnsNo()) {
            score1++;
            tvScore1.setText("Score: " + score1);
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level1.this,R.raw.correct);
            mediaPlayer.start();
        }

        if (answerNo != currentQuestion1.getCorrectAnsNo()) {
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level1.this,R.raw.wrong);
            mediaPlayer.start();


        }
        rbtnOp1.setTextColor(Color.RED);
        rbtnOp2.setTextColor(Color.RED);
        rbtnOp3.setTextColor(Color.RED);

        switch (currentQuestion1.getCorrectAnsNo()) {
            case 1:
                rbtnOp1.setTextColor(Color.GREEN);
                break;
            case 2:
                rbtnOp2.setTextColor(Color.GREEN);
                break;
            case 3:
                rbtnOp3.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter1 < totalQuestions1) {
            btnNext1.setText("Next");
            radioGroup1.getChildAt(0).setEnabled(false);
            radioGroup1.getChildAt(1).setEnabled(false);
            radioGroup1.getChildAt(2).setEnabled(false);
        } else {
            btnNext1.setText("Finish");
        }
    }


    private void Timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText("00: " + l / 1000);

            }

            @Override
            public void onFinish() {
                showNextQuestion();

            }
        }.start();
    }
}