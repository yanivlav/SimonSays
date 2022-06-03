package com.example.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button next_Activity_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        next_Activity_button = (Button)findViewById(R.id.first_activity_button);
        next_Activity_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {

                    // Intents are objects of the android.content.Intent type. Your code can send them
                    // to the Android system defining the components you are targeting.
                    // Intent to start an activity called SecondActivity with the following code:

                    Intent intent = new Intent(MainActivity.this, GameActivity.class);

                    // start the activity connect to the specified class
                    startActivity(intent);
                }
            });
    }


}