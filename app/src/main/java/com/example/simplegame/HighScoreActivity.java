package com.example.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        TextView textObjectScore = (TextView) findViewById(R.id.HighScore);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            highScore = arguments.getInt("ScoreKey");

            textObjectScore.setText("High score: " + highScore);
        }
    }
}


