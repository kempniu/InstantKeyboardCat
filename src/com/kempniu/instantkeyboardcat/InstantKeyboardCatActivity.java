package com.kempniu.instantkeyboardcat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.kempniu.instantkeyboardcat.R;

public class InstantKeyboardCatActivity extends Activity {

	private MediaPlayer mPlayer;

	private void flipPlayback() {
		if (mPlayer == null) {
			mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
		}
		if (mPlayer != null) {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();
			} else {
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				if (sp.getBoolean("maximizeVolume", true)) {
					AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_SHOW_UI);
				}
				mPlayer.seekTo(0);
				mPlayer.start();
			}
		}
	}

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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

    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {

    	switch(item.getItemId()) {

    	case R.id.mb_settings:
    		Intent settingsActivity = new Intent(getBaseContext(), InstantKeyboardCatPreferences.class);
    		startActivity(settingsActivity);
    		return true;
    	
   		default:
   			return super.onOptionsItemSelected(item);
   	    	   			
    	}
    	
    }

}