package com.example.simonsays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button next_Activity_button;
    TextView listTv,multiTv,editTextTv;
    Switch prefSwitch;
    CheckBox prefCheckBox;
    final int SETTINGS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTv = findViewById(R.id.list_pref_result);
        multiTv = findViewById(R.id.multi_select_pref_result);
        editTextTv = findViewById(R.id.edit_text_pref_result);
        prefSwitch = findViewById(R.id.switch_pref_result);
        prefCheckBox = findViewById(R.id.checkbox_pref_result);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {

            startActivityForResult(new Intent(this,SettingsActivity.class),SETTINGS_REQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_REQUEST) {

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

            listTv.setText(sp.getString("list_prefrence",""));
            prefCheckBox.setChecked(sp.getBoolean("preference_chekcbox",false));
            prefSwitch.setChecked(sp.getBoolean("switch_prefrence",false));
            editTextTv.setText(sp.getString("edit_text_prefernce",""));

            Set<String> set = sp.getStringSet("multi_select_list",null);
            ArrayList<String> list = new ArrayList<>(set);
            for(String string:list)
                multiTv.setText(multiTv.getText()+","+string);
        }
    }
}