package com.example.simonsays;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button blueBtn = findViewById(R.id.blue_btn);
        Button yellowBtn = findViewById(R.id.yellow_btn);
        Button redBtn = findViewById(R.id.red_btn);
        Button greenBtn = findViewById(R.id.green_btn);
    }
}