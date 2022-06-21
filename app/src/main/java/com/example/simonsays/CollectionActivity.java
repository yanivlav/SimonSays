package com.example.simonsays;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CollectionActivity extends AppCompatActivity {

    ImageView monky1, monky2, monky3, monky4, monky5, monky6, monky7, monky8, monky9, monky10;
    TextView lavel1, level2, lavel3, level4, lavel5, level6, lavel7, level8, lavel9, level10;
    EditText bestScoreET;
    SharedPreferences spscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        int bestScore=0;
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

        bestScoreET = findViewById(R.id.best_score);

        spscore = getSharedPreferences("scoreFile", 0);
        SharedPreferences.Editor editor = spscore.edit();
        if (!spscore.contains("bestScore"))
            editor.putInt("bestScore",0).commit();

        if (spscore.contains("bestScore"))
            bestScore = spscore.getInt("bestScore",0);

        bestScoreET.setText(bestScore+"");
//

        if (bestScore >=  1) monky1.setImageResource(R.drawable.monky_level_1);
        if (bestScore >=  3) monky2.setImageResource(R.drawable.monky_level_2);
        if (bestScore >=  7) monky3.setImageResource(R.drawable.monky_level_3);
        if (bestScore >=  12) monky4.setImageResource(R.drawable.monky_level_4);
        if (bestScore >=  15) monky5.setImageResource(R.drawable.monky_level_5);
        if (bestScore >=  18) monky6.setImageResource(R.drawable.monky_level_6);
        if (bestScore >=  25) monky7.setImageResource(R.drawable.monky_level_7);
        if (bestScore >=  43) monky8.setImageResource(R.drawable.monky_level_8);
        if (bestScore >=  50) monky9.setImageResource(R.drawable.monky_level_9);
        if (bestScore >=  60) monky10.setImageResource(R.drawable.monky_level_10);

    }
}