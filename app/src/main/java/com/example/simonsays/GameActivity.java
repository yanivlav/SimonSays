package com.example.simonsays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameActivity extends AppCompatActivity {
    private int roundNumber;
    private int AIe;
    private int highScore;
    private int chain;
    private int score, best_score;
    ArrayList<Integer> AI;
    ArrayList<Integer> sequence;
    ArrayList<Integer> playerAnswers;
    String userRedBtnSE, userBlueBtnSE,userGreenBtnSE, userYellowBtnSE,userNickname, UserSoundchise, userDiff;


    //    Button recBtn, stopBtn;
    ImageView blueIV, redIV, greenIV, yellowIV;
    Button startBtn,restartBtn, homeBtn;

    private MediaPlayer mPlayer;
    SharedPreferences spscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        sound effects from Preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");

        spscore= getSharedPreferences("scoreFile", MODE_PRIVATE);


        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);
        startBtn = findViewById(R.id.start_btn);
        restartBtn = findViewById(R.id.restart_btn);
        homeBtn = findViewById(R.id.home_btn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                intent.putExtra("game_score", score);
                startActivity(intent);
            }
        });



        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AIe = 0;
                highScore = 20;
                chain = 0;
                roundNumber = 1;
                AI = new ArrayList<>();
                sequence = new ArrayList<>();
                playerAnswers = new ArrayList<>();
                // green = 0, red = 1, yellow = 2, blue = 3
//                Toast.makeText(getApplicationContext(),"Game Started", Toast.LENGTH_SHORT).show();
                flashSequence();

            }
        });


        blueIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashBlue(0);
                playerAnswers.add(3);
                checkAnswer();

            }
        });

        redIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashRed(0);
                playerAnswers.add(1);
                checkAnswer();
            }
        });

        greenIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashGreen(0);
                playerAnswers.add(0);
                checkAnswer();
            }
        });

        yellowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashYellow(0);
                playerAnswers.add(2);
                checkAnswer();
            }
        });
    }


    private void flashSequence() {
        List<Integer> playerAnswers = new ArrayList<>();
        Random rand = new Random();
        sequence.add(rand.nextInt(3));
        for (int i = 0; i < roundNumber; i++) {
            int currentLight = sequence.get(i);
            AI = sequence;

            //should activate a flash for button by numbers finish the functions
            if(currentLight == 0) {
//                wait1(500);
                flashGreen(1);
//                Toast.makeText(getApplicationContext(),"simon says green", Toast.LENGTH_SHORT).show();
            }
            if(currentLight == 1) {
//                wait1(500);
                flashRed(1);
//                Toast.makeText(getApplicationContext(),"simon says red", Toast.LENGTH_SHORT).show();
            }
            if(currentLight == 2) {
//                wait1(500);
                flashYellow(1);
//                Toast.makeText(getApplicationContext(),"simon says yellow", Toast.LENGTH_SHORT).show();
            }
            if(currentLight == 3) {
//                wait1(500);
                flashBlue(1);
//                Toast.makeText(getApplicationContext(),"simon says green", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void checkAnswer(){
        // sequence [1, 2, 3, 0]
        // playerAns [1, 2, 3, 0]
        int currentAnswerIndex = playerAnswers.size() - 1;
        int currentAnswer = playerAnswers.get(currentAnswerIndex);
        if (currentAnswer != sequence.get(currentAnswerIndex)) {
            gameOver();
            score = 0;
        } else if ((currentAnswerIndex == sequence.size() - 1)) //last answer
        {
            playerAnswers.clear();
            roundNumber++;
//            wait1(1500);
            flashSequence();
            chain = chain + 1;
            score++;
        }
    }

    private void Draw() {//run again
        if (sequence == AI) {
            flashSequence();
            roundNumber++;
//            wait(1000);
            flashSequence();
            chain = chain + 1;
        }
    }

    private void gameOver(){
//        setText("newHs", "");
//        setScreen("gameOver");
//        setText("score", chain);
//        if (chain > highScore) {
//            highScore = chain;
//            setText("newHs", "New");
//        }
//        wait(50);
//        setText("highScore", highScore);
//        wait(2000);
        Toast.makeText(getApplicationContext(),"Game Over", Toast.LENGTH_SHORT).show();


    }


    private void flashGreen(int sender){

        if (sender == 1)
            greenIV.setEnabled(false);
        buttonAnimation(greenIV);
        if (userGreenBtnSE.isEmpty()) {
            playBtnSound("boy_says_volcano");
//            Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
        }
        else
            playBtnSound(userGreenBtnSE);
            greenIV.setEnabled(true);

//        if (sender == 1) wait1(1000);
//            greenIV.setEnabled(true);

    }

    private  void flashRed(int sender){

        if (sender == 1)
            redIV.setEnabled(false);
        buttonAnimation(redIV);
        if (userRedBtnSE.isEmpty()) {
            playBtnSound("boy_says_volcano");
//            Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
        }
        else
            playBtnSound(userRedBtnSE);
            redIV.setEnabled(true);
//        if (sender == 1) wait1(1000);
//            redIV.setEnabled(true);



    }

    private void flashYellow(int sender){
        if (sender == 1)
            yellowIV.setEnabled(false);
        buttonAnimation(yellowIV);
        if (userYellowBtnSE.isEmpty()) {
            playBtnSound("boy_says_volcano");
//            Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
        }
        else
            playBtnSound(userYellowBtnSE);
            yellowIV.setEnabled(true);


//        if (sender == 1) wait1(1000);
//            yellowIV.setEnabled(true);
    }

    private void flashBlue(int sender){
        if (sender == 1)
            blueIV.setEnabled(false);
        buttonAnimation(blueIV);
        if (userBlueBtnSE.isEmpty()) {
            playBtnSound("boy_says_volcano");
//            Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
        }
        else
            playBtnSound(userBlueBtnSE);
            blueIV.setEnabled(true);


//        if (sender == 1) wait1(1000);

//            blueIV.setEnabled(true);
    }

//    private void wait(int ms){
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                flashSequence();
//            }
//        }, 1000);//
//
//    }
//
    private void wait1(int ms){

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playBtnSound(String fileName){
        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(fileName, "raw", getPackageName()));
        mPlayer.start();
    }

    private void buttonAnimation(ImageView color) {
        AnimationDrawable animationDrawable = (AnimationDrawable) color.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = spscore.edit();
        if (spscore.getInt("bestScore",-1)>spscore.getInt("lastScore",-1))
            editor.putInt("bestScore", spscore.getInt("lastScore",-1));
        editor.putInt("lastScore", score);
        editor.commit();

    }

}