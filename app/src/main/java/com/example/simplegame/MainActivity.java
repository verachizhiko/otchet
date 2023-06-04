package com.example.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    int highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();
        if (arguments!=null){
            highScore = arguments.getInt("ScoreKey");

        }

    }

    @Override
    public void onClick(View view) {
        Intent i;
        i = new Intent(this, GameActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }


    public void HighScoreClick(View view){

        Intent intent = new Intent(this, HighScoreActivity.class);
        intent.putExtra("ScoreKey", highScore);
        startActivity(intent);
    }

}