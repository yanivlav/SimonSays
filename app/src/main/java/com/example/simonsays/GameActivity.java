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

import java.util.Random;

public class GameActivity extends AppCompatActivity{

    public static int s = 3, l = 1;
    public static boolean tutorialmode = false;
    int lastScore = 0, count = 0, currentlevel= l-1, inputcount = 0, highscore = 0, tutorialcount = 0;
    int [] correctInput = new int[500];
    boolean firstdelay = true;
    Random generator = new Random();
    ImageView blueIV, redIV, greenIV, yellowIV;
    String userRedBtnSE, userBlueBtnSE,userGreenBtnSE, userYellowBtnSE,userNickname, UserSoundchise, userDiff;
    Button homeBtn,simonbutton;
    SharedPreferences spscore;
    TextView info;
    TextView scorebox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //sound effects from Preferences
        spscore = getSharedPreferences("scoreFile",MODE_PRIVATE);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(GameActivity.this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");
        userDiff = sp.getString("difficulty_listPreference", "3");
        s = Integer.parseInt(userDiff);

        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);
        info = findViewById(R.id.info);
        scorebox = findViewById(R.id.Scorebox);

        homeBtn = findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(GameActivity.this,MainActivity.class);
//                intent.putExtra("last_score", lastScore);
//                startActivity(intent);
                finish();
            }
        });

        if (tutorialmode){
            l = 1;
            currentlevel = 0;
            correctInput[0] = 1;//red
            correctInput[1] = 3;//blue
            correctInput[2] = 1;//red
            correctInput[3] = 4;//yellow
            Toast.makeText(getApplicationContext(), R.string.Copy_the_button_simon_pressed,
                    Toast.LENGTH_LONG).show();
        }

        simonbutton = findViewById(R.id.start_btn);
        simonbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Simonsays(view);
            }
        });
    }

    public void lightupred(){
        redIV.setEnabled(false);
        ImpRunn impR = new ImpRunn(userRedBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(redIV);
    }

    public void lightupyellow(){
        yellowIV.setEnabled(false);
        ImpRunn impR = new ImpRunn(userYellowBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(yellowIV);
    }

    public void lightupgreen(){
        greenIV.setEnabled(false);
        ImpRunn impR = new ImpRunn(userGreenBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(greenIV);
    }

    public void lightupblue(){
        blueIV.setEnabled(false);
        ImpRunn impR = new ImpRunn(userBlueBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(blueIV);
    }

    public void allthelights(){
        new CountDownTimer( (((800/s)+800/s)*(currentlevel+2)), ((800/s)+800/s) ){
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
                info.setText(R.string.your_turn);
                redIV.setEnabled(true);
                blueIV.setEnabled(true);
                greenIV.setEnabled(true);
                yellowIV.setEnabled(true);
            }
        }.start();
    }

    public void levelup(){
        if (!tutorialmode){
            correctInput[currentlevel] = generator.nextInt(4) +1;
        }
        currentlevel++;
        count = 0;
        info.setText(R.string.simons_turn);
        redIV.setEnabled(false);
        blueIV.setEnabled(false);
        greenIV.setEnabled(false);
        yellowIV.setEnabled(false);
        allthelights();
        highscore++;
        scorebox.setText(getString(R.string.current_score) +": "+ (highscore-1));
    }

    public void gameover(){

        if (!tutorialmode){
            SharedPreferences.Editor editor = spscore.edit();
            if (!spscore.contains("bestScore")) {
                Toast.makeText(getApplicationContext(), "score updated: " + (highscore-1), Toast.LENGTH_SHORT).show();
                editor.putInt("bestScore", (highscore - 1));
            }
            else if(spscore.getInt("bestScore",-1) < highscore-1){
                editor.putInt("bestScore", (highscore - 1));
            }
            editor.commit();

            Toast.makeText(getApplicationContext(), "your score was: " + (highscore-1), Toast.LENGTH_SHORT).show();
            lastScore = highscore -1;
        }
        tutorialmode = false;
        inputcount = 0;
        for (int i = 0; i<currentlevel;i++){
            correctInput[i] = 0;
        }
        currentlevel = l-1;
        highscore = 0;

        redIV.setEnabled(false);
        blueIV.setEnabled(false);
        greenIV.setEnabled(false);
        yellowIV.setEnabled(false);

        simonbutton.setEnabled(true);
//        findViewById(R.id.start_btn).setEnabled(true); ----------------------------------- chack it out
        info.setText(R.string.hit_start_to_begin);
        scorebox.setText(R.string.current_score);
    }

    public void Simonsays(View view){
        simonbutton.setEnabled(false);
//        findViewById(R.id.start_btn).setEnabled(false); ----------------------------------- chack it out

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
                            Toast.makeText(getApplicationContext(), R.string.Good_Job_Simon_will_now_add_another_button, Toast.LENGTH_SHORT).show();
                            tutorialcount++;
                        }
                        if (tutorialmode && tutorialcount == 2){
                            Toast.makeText(getApplicationContext(), R.string.you_are_getting_the_point,
                                    Toast.LENGTH_SHORT).show();
                        }
                        levelup();
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), R.string.that_not_what_simon_pressed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.you_lose,
                                Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), R.string.nice_job,
                                    Toast.LENGTH_SHORT).show();
                            tutorialcount++;
                        }
                        levelup();
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), R.string.that_not_what_simon_pressed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.you_lose,
                                Toast.LENGTH_SHORT).show();
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
                        if (tutorialmode){ //completed toturial succsefully
                            Toast.makeText(getApplicationContext(), R.string.completed_toturial,
                                    Toast.LENGTH_SHORT).show();
                            gameover();
                        } else {
                            levelup();
                        }
                    }
                }
                else {
                    if (tutorialmode){
                        Toast.makeText(getApplicationContext(), R.string.that_not_what_simon_pressed,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.you_lose,
                                Toast.LENGTH_SHORT).show();
                        gameover();
                    }
                }
            }
        });

        greenIV.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (tutorialmode){
                    Toast.makeText(getApplicationContext(), R.string.Recall_what_Simon_did,
                            Toast.LENGTH_SHORT).show();
                } else {

                    if (2 == correctInput[inputcount]){
                        inputcount++;
                        if (inputcount == currentlevel){
                            levelup();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "you lose",
                                Toast.LENGTH_SHORT).show();
                        gameover();
                    }
                }
            }
        });
    }

    private void buttonAnimation(ImageView color) {
        AnimationDrawable animationDrawable = (AnimationDrawable) color.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
                animationDrawable.setVisible(true,true);
                color.setEnabled(true);
            }
        }, 350);
    }

    private void playBtnSound(String fileName){
        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(fileName, "raw", getPackageName()));
        mPlayer.start();
    }

    class ImpRunn implements Runnable {
        String userBtnSE;

        public ImpRunn(String ColorSE){
            userBtnSE=ColorSE;

        }
        @Override
        public void run() {
            if (userBtnSE.isEmpty())
                playBtnSound("boy_says_volcano");
            else
                playBtnSound(userBtnSE);
        }
    }
}