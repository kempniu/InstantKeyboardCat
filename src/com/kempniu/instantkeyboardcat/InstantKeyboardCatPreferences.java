package com.kempniu.instantkeyboardcat;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class InstantKeyboardCatPreferences extends PreferenceActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
