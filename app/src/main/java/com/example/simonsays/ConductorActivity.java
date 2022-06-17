package com.example.simonsays;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConductorActivity extends AppCompatActivity {

    String userRedBtnSE, userBlueBtnSE,userGreenBtnSE, userYellowBtnSE;
    ImageView blueIV, redIV, greenIV, yellowIV, recStatus;


    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);
        Toast.makeText(getApplicationContext(),"IM BACK", Toast.LENGTH_SHORT).show();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");
//
        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);


        blueIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userBlueBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userBlueBtnSE);
                buttonAnimation(blueIV);

            }
        });


        redIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userRedBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userRedBtnSE);
                buttonAnimation(redIV);
            }
        });


        yellowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userYellowBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userYellowBtnSE);
                buttonAnimation(yellowIV);
            }
        });

        greenIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userGreenBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userGreenBtnSE);
                buttonAnimation(greenIV);
            }
        });

    }

    private void buttonAnimation(ImageView color){
        AnimationDrawable animationDrawable = (AnimationDrawable) color.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 1000);
    }



    private void playBtnSound(String fileName){
        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(fileName, "raw", getPackageName()));
        mPlayer.start();
    }

}
