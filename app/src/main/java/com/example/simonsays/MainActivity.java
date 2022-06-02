package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button blue_btn = findViewById(R.id.blue_btn);
        Button yellow_btn = findViewById(R.id.yellow_btn);
        Button red_btn = findViewById(R.id.red_btn);
        Button green_btn = findViewById(R.id.green_btn);
    }
}