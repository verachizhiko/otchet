package com.example.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;
    TextView textObjectLevel;
    int currentScore = 0;
    int currentLevel = 1;
    int highScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textObjectPartA = (TextView) findViewById(R.id.textPartA);
        textObjectPartB = (TextView) findViewById(R.id.textPartB);
        buttonObjectChoice1 = (Button) findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = (Button) findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = (Button) findViewById(R.id.buttonChoice3);
        textObjectScore = (TextView) findViewById(R.id.textScore);
        textObjectLevel = (TextView) findViewById(R.id.textLevel);
        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {
        int answerGiven = 0;
        switch (view.getId()) {
            case R.id.buttonChoice1:
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());

                break;
            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());

                break;
            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());

                break;
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();

    }

    void setQuestion() {
        int numberRange = currentLevel * 3;
        Random randInt = new Random();
        int partA = randInt.nextInt(numberRange);
        partA++;
        int partB = randInt.nextInt(numberRange);
        partB++;
        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 2;
        int wrongAnswer2 = correctAnswer + 2;
        textObjectPartA.setText("" + partA);
        textObjectPartB.setText("" + partB);
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout) {
            case 0:
                buttonObjectChoice1.setText("" + correctAnswer);
                buttonObjectChoice2.setText("" + wrongAnswer1);
                buttonObjectChoice3.setText("" + wrongAnswer2);
                break;
            case 1:
                buttonObjectChoice2.setText("" + correctAnswer);
                buttonObjectChoice3.setText("" + wrongAnswer1);
                buttonObjectChoice1.setText("" + wrongAnswer2);
                break;
            case 2:
                buttonObjectChoice3.setText("" + correctAnswer);
                buttonObjectChoice1.setText("" + wrongAnswer1);
                buttonObjectChoice2.setText("" + wrongAnswer2);
                break;
        }
    }

    void updateScoreAndLevel(int answerGiven) {
        if (isCorrect(answerGiven)) {
            for (int i = 1; i <= currentLevel; i++) {
                currentScore = currentScore + i;
                if (highScore < currentScore) {
                    highScore = currentScore;


                }
            }
            currentLevel++;
        } else {
            currentScore = 0;
            currentLevel = 1;
        }
        textObjectScore.setText("Score: " + currentScore);
        textObjectLevel.setText("Level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven) {
        boolean correctTrueOrFalse;
        if (answerGiven == correctAnswer) {
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = true;
        } else {
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = false;
        }
        return correctTrueOrFalse;
    }

    public void onClickScore(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ScoreKey", highScore);
        startActivity(intent);
    }
}

