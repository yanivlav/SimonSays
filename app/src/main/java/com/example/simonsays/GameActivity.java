package com.example.simonsays;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Button blueBtn, yellowBtn, redBtn, greenBtn,startBtn;
    ImageView blueAnim;
    private int roundNumber;
    private int AIe;
    private int highScore;
    private int chain;
    private ArrayList<Integer> AI;
    private ArrayList<Integer> sequence;
    private ArrayList<Integer> playerAnswers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        blueBtn = (Button) findViewById(R.id.blue_btn);
        yellowBtn = (Button)findViewById(R.id.yellow_btn);
        redBtn = (Button)findViewById(R.id.red_btn);
        greenBtn = (Button)findViewById(R.id.green_btn);
        startBtn = (Button)findViewById(R.id.start_btn);
        blueAnim = findViewById(R.id.blue_btn);
        AnimationDrawable animationDrawable = (AnimationDrawable) blueAnim.getDrawable();
//        animationDrawable.start();


        final MediaPlayer mpb = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        final MediaPlayer mpr = MediaPlayer.create(this,R.raw.cool_guitar_riff);
        final MediaPlayer mpy = MediaPlayer.create(this,R.raw.electric_fence_buzzing);
        final MediaPlayer mpg = MediaPlayer.create(this,R.raw.scifi_artificial_intelligence_speaks);

//        Timer timer = new Timer();

        AIe = 0;
        highScore = 20;
        chain = 0;
        roundNumber = 1;
//        int[] AI,sequence,playerAnswers = new int[100];
        AI = new ArrayList<>();
        sequence = new ArrayList<>();
        playerAnswers = new ArrayList<>();
//        sequence.add(0);
        // green = 0, red = 1, yellow = 2, blue = 3

        flashSequence();



//        timedLoop(10, function() {
//            setText("chain", "Score: " + chain);
//            if (AIe == 1) {
//                setText("cpuFac", "Cpu Play");
//                wait(500);
//                Draw();
//            } else {
//                setText("cpuFac", "");
//            }
//        });



        blueAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpb.start();
                animationDrawable.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animationDrawable.stop();
                    }
                }, 1000);
            }
        });

//        blueBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mpb.start();
//                flashBlue();
//            }
//        });

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpr.start();
                flashRed();
            }
        });

        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpg.start();
                flashGreen();
            }
        });

        yellowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpy.start();
                flashYellow();
            }
        });
    }

    private void flashSequence() {
        playerAnswers = new ArrayList<>();
        Random rand = new Random();
//        sequence.add(rand.nextInt(3));
        sequence.add(3);
        sequence.add(0);
        for (int i = 0; i < roundNumber; i++) {
            int currentLight = sequence.get(i);
            AI = sequence;
            //should activate a flash for button by numbers finish the functions
            if(currentLight == 0) flashGreen();
//            if(currentLight === 1) flashRed();
//            if(currentLight === 2) flashYellow();
            if(currentLight == 3) flashBlue();

        }
    }

    private void checkAnswer(){
            // sequence [1, 2, 3, 0]
            // playerAns [1, 2, 3, 0]
            int currentAnswerIndex = playerAnswers.size() - 1;
            int currentAnswer = playerAnswers.get(currentAnswerIndex);
            if (currentAnswer != sequence.get(currentAnswerIndex)) {
                gameOver();
            } else if ((currentAnswerIndex == sequence.size() - 1)) { //last answer
                roundNumber++;
                wait(1000);
                flashSequence();
                chain = chain + 1;
            }
        }

    private void Draw() {
        if (sequence == AI) {
            flashSequence();
            roundNumber++;
            wait(1000);
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
        }


    private void flashGreen(){
        //
        final MediaPlayer mpg = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        mpg.start();
        AnimationDrawable animationDrawable = (AnimationDrawable) blueAnim.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 1000);
        playerAnswers.add(0);
    }

    private void flashRed(){
        final MediaPlayer mpr = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        mpr.start();
        playerAnswers.add(1);
    }

    private void flashYellow(){
        final MediaPlayer mpy = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        mpy.start();
        playerAnswers.add(2);
    }

    private void flashBlue(){
        final MediaPlayer mpb = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        mpb.start();
        AnimationDrawable animationDrawable = (AnimationDrawable) blueAnim.getDrawable();
        animationDrawable.start();
        blueAnim.setEnabled(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 1000);
        blueAnim.setEnabled(true);
        playerAnswers.add(3);
    }

    private void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}