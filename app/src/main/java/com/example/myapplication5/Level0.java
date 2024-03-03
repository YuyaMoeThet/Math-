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

public class Level0 extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rbtnOption1, rbtnOption2, rbtnOption3;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;

    private QuestionModel currentQuestion;

    ColorStateList dfRbColor;
    boolean answered;

    int score;

    private List<QuestionModel> questionlist;

    private int[] RandomQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level0);

        tvQuestion = findViewById(R.id.textQuestion);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvScore = findViewById(R.id.textScore);
        tvScore.setText("Score: 0");

        radioGroup = findViewById(R.id.radioGroup);
        rbtnOption1 = findViewById(R.id.rbtnOp1);
        rbtnOption2 = findViewById(R.id.rbtnOp2);
        rbtnOption3 = findViewById(R.id.rbtnOp3);

        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rbtnOption1.getTextColors();

        questionlist = new ArrayList<>();
        addQuestion();

        totalQuestions = questionlist.size();

        RandomQuestion = new int[10];
        RandomQuestion = new RandomQuestion().getRandomGenerateNoArr();
        showNextQuestion();


        btnNext.setOnClickListener(v -> {
            if (answered == false) {
                if (rbtnOption1.isChecked() || rbtnOption2.isChecked() || rbtnOption3.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(Level0.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }

            if(btnNext.getText().toString().equals("Finish")){
                Intent i = new Intent(Level0.this,Score.class);

                i.putExtra("values",score);
                startActivity(i);
            }
        });

    }

    private void addQuestion() {
        questionlist.add(new QuestionModel("4+8", "11", "12", "10", 2));
        questionlist.add(new QuestionModel("8-5", "7", "13", "3", 3));
        questionlist.add(new QuestionModel("4-2", "2", "4", "6", 1));
        questionlist.add(new QuestionModel("2+4", "7", "6", "8", 2));
        questionlist.add(new QuestionModel("1+3", "1", "2", "4", 3));
        questionlist.add(new QuestionModel("3-1", "2", "6", "8", 1));
        questionlist.add(new QuestionModel("12-6", "3", "6", "5", 2));
        questionlist.add(new QuestionModel("2+5", "6", "8", "7", 3));
        questionlist.add(new QuestionModel("9-5", "7", "4", "3", 2));
        questionlist.add(new QuestionModel("3-3", "0", "6", "12", 1));
        totalQuestions = questionlist.size();

    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rbtnOption1.setTextColor(dfRbColor);
        rbtnOption2.setTextColor(dfRbColor);
        rbtnOption3.setTextColor(dfRbColor);

        if (qCounter < totalQuestions) {
            currentQuestion = questionlist.get(qCounter);
            currentQuestion = questionlist.get(RandomQuestion[qCounter]);
            tvQuestion.setText(currentQuestion.getQuestion());
            rbtnOption1.setText(currentQuestion.getOption1());
            rbtnOption2.setText(currentQuestion.getOption2());
            rbtnOption3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Submit");
            radioGroup.getChildAt(0).setEnabled(true);
            radioGroup.getChildAt(1).setEnabled(true);
            radioGroup.getChildAt(2).setEnabled(true);

            tvQuestionNo.setText("Question" + qCounter + "/" + totalQuestions);
            answered = false;
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnsNo()) {
            score++;
            tvScore.setText("Score: " + score);
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level0.this,R.raw.correct);
            mediaPlayer.start();
        }

        if (answerNo != currentQuestion.getCorrectAnsNo()) {
            final MediaPlayer mediaPlayer= MediaPlayer.create(Level0.this,R.raw.wrong);
            mediaPlayer.start();
        }
        rbtnOption1.setTextColor(Color.RED);
        rbtnOption2.setTextColor(Color.RED);
        rbtnOption3.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnsNo()) {
            case 1:
                rbtnOption1.setTextColor(Color.GREEN);
                break;
            case 2:
                rbtnOption2.setTextColor(Color.GREEN);
                break;
            case 3:
                rbtnOption3.setTextColor(Color.GREEN);
                break;
        }

        if (qCounter < totalQuestions) {
            btnNext.setText("Next");
            radioGroup.getChildAt(0).setEnabled(false);
            radioGroup.getChildAt(1).setEnabled(false);
            radioGroup.getChildAt(2).setEnabled(false);
        } else {
            btnNext.setText("Finish");
        }
    }
}