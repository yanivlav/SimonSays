package com.example.simonsays;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener  {


    ImageView imageView;
    EditText lastscoreET;
    Button next_Activity_button, conductorBtn, collectionBtn, instructiobnBtn, share;
    String userNickname, UserSoundchise, userDiff;
    public static final int SETTINGS_REQUEST = 1;
    public static final int INSTRUCTIONS_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.home_page2);

//        Intent intent = new Intent(getApplicationContext(), Second_activity.class);
//        int score = getIntent().getIntExtra("game_score",-1);
//        lastscoreET = findViewById(R.id.lastScoreET);
//        Bundle extras = getIntent().getExtras();
//        if(extras!=null)
//            lastscoreET.setText(extras.getInt("last_score"));
//        else
//            lastscoreET.setText("0");


        instructiobnBtn = findViewById(R.id.instructions_button);
        instructiobnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InstuctionsActivity.class);
                startActivity(intent);
            }
        });

        collectionBtn = findViewById(R.id.collection_activity_button);
        collectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

        share = findViewById(R.id.share_button);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                shareImageandText(bitmap);
            }
        });

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
        if (item.getItemId() == R.id.action_settings)
            startActivityForResult(new Intent(this, SettingsActivity.class), SETTINGS_REQUEST);
        return super.onOptionsItemSelected(item);
    }

    private void shareImageandText(Bitmap bitmap) {
        Uri uri = getmageToShare(bitmap);
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
        intent.setType("image/jpg");
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    private Uri getmageToShare(Bitmap bitmap) {
        File imagefolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            File file = new File(imagefolder, "homepage2.jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this, "com.example.simonsays", file);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return uri;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Shutdown").setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes",this).setNegativeButton("No",this).show();


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            finish();
        }
        else if(which == DialogInterface.BUTTON_NEGATIVE){

        }
    }

}