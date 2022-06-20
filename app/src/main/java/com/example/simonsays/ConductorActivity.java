package com.example.simonsays;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ConductorActivity extends AppCompatActivity {

    String userRedBtnSE, userBlueBtnSE,userGreenBtnSE, userYellowBtnSE;
    ImageView blueIV, redIV, greenIV, yellowIV;
    Button okBtn;


    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");

        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);
        okBtn = findViewById(R.id.ok_btn);

        blueIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lightupblue();

            }
        });
        redIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lightupred();
            }
        });
        yellowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lightupyellow();
            }
        });
        greenIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lightupgreen();
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

    public void lightupred(){
        redIV.setEnabled(false);
        ConductorActivity.ImpRunn impR = new ConductorActivity.ImpRunn(userRedBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(redIV);
    }

    public void lightupyellow(){
        yellowIV.setEnabled(false);
        ConductorActivity.ImpRunn impR = new ConductorActivity.ImpRunn(userYellowBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(yellowIV);
    }

    public void lightupgreen(){
        greenIV.setEnabled(false);
        ConductorActivity.ImpRunn impR = new ConductorActivity.ImpRunn(userGreenBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(greenIV);
    }

    public void lightupblue(){
        blueIV.setEnabled(false);
        ConductorActivity.ImpRunn impR = new ConductorActivity.ImpRunn(userBlueBtnSE);
        Thread th=new Thread(impR);
        th.start();
        buttonAnimation(blueIV);
    }

}
