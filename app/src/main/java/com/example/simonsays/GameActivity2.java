package com.example.simonsays;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity2 extends AppCompatActivity implements Runnable{

    public static int s = 3;
    public static int l = 1;
    public static boolean tutorialmode = false;
    int tutorialcount = 0;
    Random generator = new Random();
    int count = 0;
    int currentlevel= l-1;
    int inputcount = 0;
    int highscore = 0;
    boolean firstdelay = true;
    int [] correctInput = new int[500];





    ImageView blueIV, redIV, greenIV, yellowIV;
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
    Button startBtn,restartBtn, homeBtn,simonbutton;
    private MediaPlayer mPlayer;
    SharedPreferences spscore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        //sound effects from Preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");

        sp = getSharedPreferences("scoreFile", MODE_PRIVATE);


        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);
        simonbutton = findViewById(R.id.start_btn);
        restartBtn = findViewById(R.id.restart_btn);
        homeBtn = findViewById(R.id.home_btn);

        if (tutorialmode){
            l = 1;
            currentlevel = 0;
            correctInput[0] = 1;//red
            correctInput[1] = 3;//blue
            correctInput[2] = 1;//red
            correctInput[3] = 4;//yellow
            Toast.makeText(getApplicationContext(), "Copy the button simon pressed.",
                    Toast.LENGTH_LONG).show();
        }


//        Button simonbutton = (Button) findViewById(R.id.Simon);
        simonbutton.performClick();
        simonbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Simonsays(view);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main_activity_simon, menu);
//        return true;
//    }

    public void lightupred(){
        redIV.setEnabled(false);
        run();

        AnimationDrawable animationDrawable = (AnimationDrawable) redIV.getDrawable();
        animationDrawable.start();
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                animationDrawable.stop();
                animationDrawable.setVisible(true,true);
                redIV.setEnabled(true);
            }
        }, 1400/s);
    }

    public void lightupyellow(){
        yellowIV.setEnabled(false);
        run();

        AnimationDrawable animationDrawable = (AnimationDrawable) yellowIV.getDrawable();
        animationDrawable.start();
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                Toast.makeText(getApplicationContext(), "!!!!!!!!!!", Toast.LENGTH_LONG).show();
                animationDrawable.stop();
                animationDrawable.setVisible(true,true);

                yellowIV.setEnabled(true);
            }
        }, 1400/s);
    }

    public void lightupgreen(){
        greenIV.setEnabled(false);
        run();

        AnimationDrawable animationDrawable = (AnimationDrawable) greenIV.getDrawable();
        animationDrawable.start();
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                animationDrawable.stop();
                animationDrawable.setVisible(true,true);

                greenIV.setEnabled(true);
            }
        }, 1400/s);
    }

    public void lightupblue(){
        blueIV.setEnabled(false);
        run();

        AnimationDrawable animationDrawable = (AnimationDrawable) blueIV.getDrawable();
        animationDrawable.start();
        Handler h = new Handler();
        h.postDelayed(new Runnable(){
            public void run(){
                animationDrawable.stop();
                animationDrawable.setVisible(true,true);
                blueIV.setEnabled(true);
            }
        }, 1400/s);
    }

    public void allthelights(){
        new CountDownTimer( (((1400/s)+1000/s)*(currentlevel+2)), ((1400/s)+1000/s) ){
            public void onTick(long millis){
                if (firstdelay){
                    firstdelay = false;
                }
                else if(correctInput[count] == 1){
                    lightupred();
                    count++;
                }
                else if(correctInput[count] == 2) {
                    lightupgreen();
                    count++;
                }
                else if(correctInput[count] == 3) {
                    lightupblue();
                    count++;
                }
                else if(correctInput[count] == 4) {
                    lightupyellow();
                    count++;
                }
            }

            public void onFinish(){
                count = 0;
                inputcount = 0;
                firstdelay = true;
                redIV.setEnabled(true);
                blueIV.setEnabled(true);
                greenIV.setEnabled(true);
                yellowIV.setEnabled(true);
                TextView info = (TextView) findViewById(R.id.info);
                info.setText("Your Turn");
            }
        }.start();
    }

    public void levelup(){
        if (!tutorialmode){
            correctInput[currentlevel] = generator.nextInt(4) +1;
        }
        currentlevel++;
        count = 0;
        TextView info = (TextView) findViewById(R.id.info);
        info.setText("Simon's Turn");
        redIV.setEnabled(false);
        blueIV.setEnabled(false);
        greenIV.setEnabled(false);
        yellowIV.setEnabled(false);
        allthelights();
        highscore++;

        TextView scorebox = (TextView) findViewById(R.id.Scorebox);
        scorebox.setText("Current Score: " + (highscore-1));
    }

    public void gameover(){
        if (!tutorialmode){
            Toast.makeText(getApplicationContext(), "your score was: " + (highscore-1),
                    Toast.LENGTH_LONG).show();
        }
        tutorialmode = false;
        inputcount = 0;
        for (int i =0; i<currentlevel;i++){
            correctInput[i] = 0;
        }
        currentlevel = l-1;
        highscore = 0;

        redIV.setEnabled(false);
        blueIV.setEnabled(false);
        greenIV.setEnabled(false);
        yellowIV.setEnabled(false);

        findViewById(R.id.start_btn).setEnabled(true);
        TextView info = (TextView) findViewById(R.id.info);
        info.setText("Hit Simon to Begin");
        TextView scorebox = (TextView) findViewById(R.id.Scorebox);
        scorebox.setText("Current Score: ");
    }

    public void Simonsays(View view){
        findViewById(R.id.start_btn).setEnabled(false);

        for (int i=0; i< l-1; i++){
            correctInput[i] = generator.nextInt(4) +1;
        }

        levelup();


        redIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (1 == correctInput[inputcount]){
                    inputcount++;
                    if (inputcount == currentlevel){
                        if (tutorialmode && tutorialcount == 0){
                            Toast.makeText(getApplicationContext(), "Good Job! Simon will now add another button to the sequnce.",
                                    Toast.LENGTH_LONG).show();
                            tutorialcount++;
                        }
                        if (tutorialmode && tutorialcount == 2){
                            Toast.makeText(getApplicationContext(), "You're getting the hang of it!",
                                    Toast.LENGTH_LONG).show();
                        }
                        levelup();
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "you lose",
                                Toast.LENGTH_LONG).show();
                        gameover();
                    }
                }
            }
        });

        blueIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (3 == correctInput[inputcount]){
                    inputcount++;
                    if (inputcount == currentlevel){
                        if (tutorialmode){
                            Toast.makeText(getApplicationContext(), "Nice Job!",
                                    Toast.LENGTH_LONG).show();
                            tutorialcount++;
                        }
                        levelup();
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "you lose",
                                Toast.LENGTH_LONG).show();
                        gameover();
                    }
                }
            }
        });

        yellowIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (4 == correctInput[inputcount]){
                    inputcount++;
                    if (inputcount == currentlevel){
                        if (tutorialmode){
                            Toast.makeText(getApplicationContext(), "Nice! You've completed the tutorial! Hit simon to play.",
                                    Toast.LENGTH_LONG).show();
                            gameover();
                        } else {
                            levelup();
                        }
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "you lose",
                                Toast.LENGTH_LONG).show();
                        gameover();
                    }
                }
            }
        });

        greenIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (tutorialmode){
                    Toast.makeText(getApplicationContext(), "Recall what Simon did. Try Again.",
                            Toast.LENGTH_LONG).show();
                } else {

                    if (2 == correctInput[inputcount]){
                        inputcount++;
                        if (inputcount == currentlevel){
                            levelup();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "you lose",
                                Toast.LENGTH_LONG).show();
                        gameover();
                    }
                }
            }
        });

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
    public void run() {
        playBtnSound("boy_says_volcano");
    }

}