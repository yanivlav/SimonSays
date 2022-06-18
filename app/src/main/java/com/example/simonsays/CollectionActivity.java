package com.example.simonsays;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class CollectionActivity extends AppCompatActivity {

    ImageView monky1, monky2, monky3, monky4, monky5, monky6, monky7, monky8, monky9, monky10;
    TextView lavel1, level2, lavel3, level4, lavel5, level6, lavel7, level8, lavel9, level10, score;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        int score = getIntent().getIntExtra("game_score",-1);

        monky1 = findViewById(R.id.monky_level_1);
        monky2 = findViewById(R.id.monky_level_2);
        monky3 = findViewById(R.id.monky_level_3);
        monky4 = findViewById(R.id.monky_level_4);
        monky5 = findViewById(R.id.monky_level_5);
        monky6 = findViewById(R.id.monky_level_6);
        monky7 = findViewById(R.id.monky_level_7);
        monky8 = findViewById(R.id.monky_level_8);
        monky9 = findViewById(R.id.monky_level_9);
        monky10 = findViewById(R.id.monky_level_10);

        lavel1 = findViewById(R.id.level1TV);
        level2 = findViewById(R.id.level2TV);
        lavel3 = findViewById(R.id.level3TV);
        level4 = findViewById(R.id.level4TV);
        lavel5 = findViewById(R.id.level5TV);
        level6 = findViewById(R.id.level6TV);
        lavel7 = findViewById(R.id.level7TV);
        level8 = findViewById(R.id.level8TV);
        lavel9 = findViewById(R.id.level9TV);
        level10 = findViewById(R.id.level10TV);

        SharedPreferences spscore = getSharedPreferences("scoreFile", MODE_PRIVATE);
        int bestScore = spscore.getInt("bestScore",-1);

        if (bestScore >  4) monky1.setImageResource(R.drawable.monky_level_1);
        if (bestScore >  9) monky1.setImageResource(R.drawable.monky_level_2);
        if (bestScore >  19) monky1.setImageResource(R.drawable.monky_level_3);
        if (bestScore >  49) monky1.setImageResource(R.drawable.monky_level_4);
        if (bestScore >  74) monky1.setImageResource(R.drawable.monky_level_5);
        if (bestScore >  99) monky1.setImageResource(R.drawable.monky_level_6);
        if (bestScore >  119) monky1.setImageResource(R.drawable.monky_level_7);
        if (bestScore >  149) monky1.setImageResource(R.drawable.monky_level_8);
        if (bestScore >  175) monky1.setImageResource(R.drawable.monky_level_9);
        if (bestScore >  199) monky1.setImageResource(R.drawable.monky_level_10);








    }
}