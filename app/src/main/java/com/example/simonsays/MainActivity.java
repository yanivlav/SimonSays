package com.example.simonsays;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button next_Activity_button, conductorBtn;
    String userRedBtnSE, userBlueBtnSE, userGreenBtnSE, userYellowBtnSE;
    String userNickname, UserSoundchise, userDiff;
    public static final int SETTINGS_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        listTv = findViewById(R.id.list_pref_result);
//        multiTv = findViewById(R.id.multi_select_pref_result);
//        editTextTv = findViewById(R.id.edit_text_pref_result);
//        prefSwitch = findViewById(R.id.switch_pref_result);
//        prefCheckBox = findViewById(R.id.checkbox_pref_result);

        conductorBtn = findViewById(R.id.conductor_button);
        conductorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConductorActivity.class);
                startActivity(intent);
            }
        });

        next_Activity_button = (Button) findViewById(R.id.first_activity_button);
        next_Activity_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {

            startActivityForResult(new Intent(this, SettingsActivity.class), SETTINGS_REQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == SETTINGS_REQUEST) {
//
//            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
//
//            userRedBtnSE = sp.getString("red_listPreference", "");
//            Toast.makeText(getApplicationContext(),sp.getString("red_listPreference", "").toString(), Toast.LENGTH_SHORT).show();
////            userBlueBtnSE = sp.getString("blue_listPreference", "");
////            userGreenBtnSE = sp.getString("green_listPreference", "");
////            userYellowBtnSE = sp.getString("yellow_listPreference", "");
////            userNickname = sp.getString("nickname_edit_text_prefernce", "");
////            UserSoundchise = sp.getString("sound_checkbox", "");
////            userDiff = sp.getString("difficulty_listPreference", "");
//
//        }
//    }

}