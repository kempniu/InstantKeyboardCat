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

	private boolean getPref(String key) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		return sp.getBoolean(key, true);
	}

	private void flipPlayback() {

		if (mPlayer == null) {
			mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
		}

		if (mPlayer != null) {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();
			} else {
				if (getPref("maximizeVolume")) {
					AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
					mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_SHOW_UI);
				}
				mPlayer.seekTo(0);
				mPlayer.start();
			}
		}
		
	}

	private void releasePlayer() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (getPref("playOnLaunch")) {
			flipPlayback();
		}

		ImageButton b = (ImageButton) findViewById(R.id.id_button_main);

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				flipPlayback();
			}
		});

	}

	public void onPause() {
		super.onPause();
		releasePlayer();
	}

	public void onStop() {
		super.onStop();
		releasePlayer();
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
