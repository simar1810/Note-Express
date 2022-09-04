package com.updevelop.noteexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView introVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        introVideo = findViewById(R.id.introVideo);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.newexpress);
        introVideo.setVideoURI(uri);
        introVideo.start();
        introVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity(new Intent(MainActivity.this,loginPage.class));
            }
        });


    }
    @Override
    protected void onPostResume(){
        introVideo.resume();
        super.onPostResume();

    }
    @Override
    protected void onRestart(){
        introVideo.start();
        super.onRestart();
    }
    @Override
    protected void onPause(){
        introVideo.suspend();
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        introVideo.stopPlayback();
        super.onDestroy();
    }


}
