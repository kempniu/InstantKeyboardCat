package com.kempniu.instantkeyboardcat;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.kempniu.instantkeyboardcat.R;

public class InstantKeyboardCatActivity extends Activity {

	private MediaPlayer mPlayer;

	private void flipPlayback() {
		if (mPlayer != null) {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();
			} else {
				AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_SHOW_UI);
				mPlayer.seekTo(0);
				mPlayer.start();
			}
		}
	}

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
        flipPlayback();

        ImageButton b = (ImageButton) findViewById(R.id.id_button_main);

        b.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		flipPlayback();
        	}
        });

    }
    
    public void onPause() {
    	super.onPause();
    	if (mPlayer != null) {
    		mPlayer.release();
    		mPlayer = null;
    	}
    }
    
    public void onStop() {
    	super.onStop();
    	if (mPlayer != null) {
    		mPlayer.release();
    		mPlayer = null;
    	}
    }
    
}