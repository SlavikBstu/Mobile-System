package com.example.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

    Button play;
    VideoView video;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.play);
        video = (VideoView)findViewById(R.id.video);
        mc = new MediaController(this);
    }

    public void videoplay(View v){
        String videopath = "android.resource://" + getPackageName() + "/" +  R.raw.videoplayback;
        Uri uri = Uri.parse(videopath);
        video.setVideoURI(uri);
        video.setMediaController(mc);
        mc.setAnchorView(video);
        video.start();
    }
}