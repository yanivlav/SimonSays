package com.example.simonsays;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    Button blueBtn, yellowBtn, redBtn, greenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        blueBtn = (Button) findViewById(R.id.blue_btn);
        yellowBtn = (Button)findViewById(R.id.yellow_btn);
        redBtn = (Button)findViewById(R.id.red_btn);
        greenBtn = (Button)findViewById(R.id.green_btn);
        final MediaPlayer mpb = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        final MediaPlayer mpr = MediaPlayer.create(this,R.raw.cool_guitar_riff);
        final MediaPlayer mpy = MediaPlayer.create(this,R.raw.electric_fence_buzzing);
        final MediaPlayer mpg = MediaPlayer.create(this,R.raw.scifi_artificial_intelligence_speaks);

        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpb.start();
            }
        });

        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpr.start();
            }
        });

        greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpg.start();
            }
        });

        yellowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpy.start();
            }
        });
    }

}