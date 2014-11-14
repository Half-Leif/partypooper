package com.ustwo.pp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.ustwo.pp.network.JsonParser;
import com.ustwo.pp.network.ServerConnection;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private static final String TAG = "StartupActivity";
	public static final String EXTRA_USER = "extra_user";
	LinearLayout linearLayout;
	ChannelList channelList;

	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		Intent intent = getIntent();
		user = intent.getParcelableExtra(EXTRA_USER);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

		new Thread(new Runnable() {

			@Override
			public void run() {
				ServerConnection connection = new ServerConnection();
				JsonParser parser = new JsonParser();
				connection
						.jsonGet(
								"https://partypooper-staging.herokuapp.com/api/channels.json",
								user.getAccessToken(), parser);
				channelList = new ChannelList(parser.getArrayRoot());
				
				for (final Channel server : channelList.getServers()) {
						
						
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Button button = new Button(getApplicationContext());
								button.setId(8);
								
								button.setText(server.getName());
								
								linearLayout.addView(button);
							}
						});
					
					/*
					 * for (Message mess : server.getMessages()){
					 * System.out.println
					 * ("Server "+server.getName()+" has Message from user "
					 * +mess.getUser().getName()+":\n"+mess.getBody()+"\n ");
					 * 
					 * }
					 */
				}
			}

		}).start();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		}

	}
}