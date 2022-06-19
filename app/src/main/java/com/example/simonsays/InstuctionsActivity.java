package com.example.simonsays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstuctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instuctions);
        Button tutorialBtn = findViewById(R.id.tutorial_btn);
        Button okBtn = findViewById(R.id.ok_btn);

        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity2.tutorialmode = true;
                Intent intent = new Intent(InstuctionsActivity.this, GameActivity2.class);
                startActivity(intent);
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}