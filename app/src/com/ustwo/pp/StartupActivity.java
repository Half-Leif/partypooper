package com.ustwo.pp;

import android.app.Activity;
import android.os.Bundle;


public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		ServerConnection sc = new ServerConnection();
		sc.connectTo("http://www.google.com");
	}

}
