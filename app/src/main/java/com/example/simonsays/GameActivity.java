package com.example.simonsays;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Button blueBtn, yellowBtn, redBtn, greenBtn,startBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        blueBtn = (Button) findViewById(R.id.blue_btn);
        yellowBtn = (Button)findViewById(R.id.yellow_btn);
        redBtn = (Button)findViewById(R.id.red_btn);
        greenBtn = (Button)findViewById(R.id.green_btn);
        startBtn = (Button)findViewById(R.id.start_btn);
        final MediaPlayer mpb = MediaPlayer.create(this,R.raw.alien_blast_in_the_earth);
        final MediaPlayer mpr = MediaPlayer.create(this,R.raw.cool_guitar_riff);
        final MediaPlayer mpy = MediaPlayer.create(this,R.raw.electric_fence_buzzing);
        final MediaPlayer mpg = MediaPlayer.create(this,R.raw.scifi_artificial_intelligence_speaks);


        int level = 1;//counter for level

        final String[] colors = {"Blue","Yellow","Red","Green"};
//        Button[] buttons = {blueBtn,yellowBtn,redBtn,greenBtn};

        Random rand = new Random();
//        int randNum = rand.nextInt(3);

        ArrayList<String> first = new ArrayList<>();

//        first.add(colors[randNum]);

//        //initialize 100 first colors
//        for (int i = 0; i < 100 ; i++){
//            first.add(colors[rand.nextInt(3)]);
//
//        }
//        for (int i = 0 ; i < first.size(); i++){
//                switch(first.get(i)) {
//                    case "Blue":
//                        mpb.start();
//                        break;
//                    case "Red":
//                        mpr.start();
//                        break;
//                    case "Green":
//                        mpg.start();
//                        break;
//                    case "Yellow":
//                        mpy.start();
//                        break;
//                    default:
//                        // code block
//                }
//        }

//        while (level < 100){
//            for (int i = 0 ; i < level; i++){
//                switch(first.get(i)) {
//                    case "Blue":
//                        mpb.start();
//                        break;
//                    case "Red":
//                        mpr.start();
//                        break;
//                    case "Green":
//                        mpg.start();
//                        break;
//                    case "Yellow":
//                        mpy.start();
//                        break;
//                    default:
//                        // code block
//                }
////        private int checkCurrentSteam(ArrayList<String> first, ArrayList<String> clickStream){
////            for (int i = 0 ; i < first.toArray().length; i++){
////                if (first[i] != clickStream[i]){
////                    return 0;
////                }
////                return 1;
////            }
//
//            }//for
//            level++;
//        }//while


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