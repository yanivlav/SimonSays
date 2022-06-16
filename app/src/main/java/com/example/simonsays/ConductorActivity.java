package com.example.simonsays;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class ConductorActivity extends AppCompatActivity {

    String userRedBtnSE, userBlueBtnSE,userGreenBtnSE, userYellowBtnSE;
    Button recBtn, stopBtn;
    ImageView blueIV, redIV, greenIV, yellowIV, recStatus;

    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        userRedBtnSE = sp.getString("red_listPreference", "");
        userBlueBtnSE = sp.getString("blue_listPreference", "");
        userGreenBtnSE = sp.getString("green_listPreference", "");
        userYellowBtnSE = sp.getString("yellow_listPreference", "");
//
        blueIV = findViewById(R.id.blue_IV);
        redIV = findViewById(R.id.red_IV);
        yellowIV = findViewById(R.id.yellow_IV);
        greenIV = findViewById(R.id.green_IV);

        recBtn.findViewById(R.id.recordBtn);
        stopBtn = findViewById(R.id.stopBtn);

        recBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording2();
            }
        });

//        stopBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopRecording();
//            }
//        });


        blueIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userBlueBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userBlueBtnSE);
                buttonAnimation(blueIV);
            }
        });


        redIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userRedBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userRedBtnSE);
                buttonAnimation(redIV);
            }
        });


        yellowIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userYellowBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userYellowBtnSE);
                buttonAnimation(yellowIV);
            }
        });

        greenIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userGreenBtnSE.isEmpty()) {
                    playBtnSound("boy_says_volcano");
                    Toast.makeText(getApplicationContext(),"defualt Sound chosen", Toast.LENGTH_SHORT).show();
                }
                else
                    playBtnSound(userGreenBtnSE);
                buttonAnimation(greenIV);
            }
        });

    }

    private void buttonAnimation(ImageView color){
        AnimationDrawable animationDrawable = (AnimationDrawable) color.getDrawable();
        animationDrawable.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();
            }
        }, 1000);
    }



    private void playBtnSound(String fileName){
        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(fileName, "raw", getPackageName()));
        mPlayer.start();
    }

    private void startRecording2() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (CheckPermissions()) {
                //start recording
            } else {
                RequestPermissions();
            }
        } else {
            //start recording
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                        //start recording
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    private void recording() {
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/AudioRecording.3gp";
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mFileName);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            mRecorder.start();
            Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
    }



//    private void startRecording() {
//            if (CheckPermissions()) {
//                mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//                mFileName += "/AudioRecording.3gp";
//                mRecorder = new MediaRecorder();
//                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                mRecorder.setOutputFile(mFileName);
//                try {
//                    mRecorder.prepare();
//                } catch (IOException e) {
//                    Log.e("TAG", "prepare() failed");
//                }
//                mRecorder.start();
////                statusTV.setText("Recording Started");
//            } else {
//                RequestPermissions();
//            }
//        }

//        @Override
//        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//            // this method is called when user will
//            // grant the permission for audio recording.
//            switch (requestCode) {
//                case REQUEST_AUDIO_PERMISSION_CODE:
//                    if (grantResults.length > 0) {
//                        boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                        boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                        if (permissionToRecord && permissionToStore) {
//                            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    break;
//            }
//        }

        public boolean CheckPermissions() {
            // this method is used to check permission
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }

        private void RequestPermissions() {
            // this method is used to request the
            // permission for audio recording and storage.
            ActivityCompat.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
        }


        public void pauseRecording() {
            stopTV.setBackgroundColor(getResources().getColor(R.color.gray));
            startTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
            playTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
            stopplayTV.setBackgroundColor(getResources().getColor(R.color.purple_200));

            // below method will stop
            // the audio recording.
            mRecorder.stop();

            // below method will release
            // the media recorder class.
            mRecorder.release();
            mRecorder = null;
            statusTV.setText("Recording Stopped");
        }
    }
}
