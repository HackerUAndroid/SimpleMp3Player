package com.hackeru.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer player;

    TextView length;

    ProgressBar pb;

    Handler timerHandler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this, R.raw.abc);
        player.start();
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.fwd).setOnClickListener(this);
//        player.start();
//        player.setLooping(true);
//
        length = findViewById(R.id.length);
        pb = findViewById(R.id.pb);
//
//        try {
//            player.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        length.setText("Gvulot higayon:  "+player.getDuration());
//
        timerHandler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if(player.isPlaying()){
                    int progress = (player.getCurrentPosition()*100)/player.getDuration();
                    Log.d("PROGRESS", progress+"");
                    pb.setProgress(progress);
                    timerHandler.postDelayed(runnable, 1000);
                }
            }
        };

        timerHandler.postDelayed(runnable, 1000);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.play:
                player.start();
                break;
                case R.id.pause:
                    if (player.isPlaying())
                        player.pause();
                    else {
                        player.start();
                    }
                break;
                case R.id.stop:
                    player.stop();
                break;
                case R.id.fwd:
                    int y = player.getCurrentPosition()+2000;
                    player.seekTo(y);
                break;
        }
    }
}