package com.ustwo.pp;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;
import com.spotify.sdk.android.playback.ConnectionStateCallback;
import com.spotify.sdk.android.playback.PlayerNotificationCallback;
import com.spotify.sdk.android.playback.PlayerState;
import com.ustwo.pp.network.JsonParser;
import com.ustwo.pp.network.ParsedContent;
import com.ustwo.pp.network.ServerConnection;

public class LoginActivity extends Activity implements
		PlayerNotificationCallback, ConnectionStateCallback {

	public final static String CLIENT_ID = "4f4f6566906848249c34aeccb656f697";
	public final static String TAG = "LoginActivity";
	EditText emailEditText, passwordEditText;
	Button loginButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// nextScreen();
		callSpotify();
		// initUI();
	}

	private void callSpotify() {
		Log.v(TAG, "Log in");
		String callbackUrl = getString(R.string.spotify_redirect_scheme)
				+ "://" + getString(R.string.spotify_redirect_host);
		SpotifyAuthentication.openAuthWindow(CLIENT_ID, "code", callbackUrl,
				new String[] { "user-read-private" }, null, this);
	}

	private void nextScreen() {
		Intent startupIntent = new Intent(this, StartupActivity.class);
		this.startActivity(startupIntent);
		this.finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Uri uri = intent.getData();
		if (uri != null) {
			AuthenticationResponse response = SpotifyAuthentication
					.parseOauthResponse(uri);

			String[] splitResponse = uri.toString().split("[\\?&]");

			final String code = splitResponse[1];
			System.out.println("<<<<< CODE >>>>\n\n" + code
					+ "\n\n<<<<< CODE >>>>");
			new Thread(new Runnable() {
				@Override
				public void run() {
					ServerConnection connection = new ServerConnection();
					JsonParser parser = new JsonParser();
					connection
							.post("https://partypooper-staging.herokuapp.com/api/users/token",
									code, parser);
					ParsedContent parsedContent = new ParsedContent(parser
							.getRoot());
					String accessToken = null;
					if ((accessToken = parsedContent.getAccessToken()) != null) {
						final String accessBody = "{\"user\":{\"sp_access_token\":\""
								+ accessToken + "\"}}";
						/*
						 * System.out
						 * .println("<<<<<<<<<<< ACCESS_BODY >>>>>>>>>>\n" +
						 * accessBody + "\n<<<<<<<<<<< ACCESS_BODY >>>>>>>>>>");
						 */
						connection
								.jsonPost(
										"https://partypooper-staging.herokuapp.com/api/users.json",
										accessBody, parser);
						User user = new User(parser.getRoot());

						if (user.getAccessToken() != null) {
							connection
									.jsonGet(
											"https://partypooper-staging.herokuapp.com/api/channels.json",
											user.getAccessToken(), parser);
							ServerList serverList = new ServerList(parser.getArrayRoot());
							for (Server server : serverList.getServers()){
								for (Message mess : server.getMessages()){
									System.out.println("Server "+server.getName()+" has Message from user "+mess.getUser().getName()+":\n"+mess.getBody()+"\n ");
								}
							}
							
							
						}
					}
				}
			}).start();
			nextScreen();
		}
	}

	@Override
	public void onConnectionMessage(String arg0) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>> onConnectionMessage " + arg0);
	}

	@Override
	public void onLoggedIn() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>> onLoggedIn");

	}

	@Override
	public void onLoggedOut() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>> onLoggedOut");
	}

	@Override
	public void onLoginFailed(Throwable arg0) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>> onLoginFailed");
	}

	@Override
	public void onNewCredentials(String arg0) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>> onNewCredentials " + arg0);
	}

	@Override
	public void onTemporaryError() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>> onTemporaryError");
	}

	@Override
	public void onPlaybackError(ErrorType arg0, String arg1) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>> onPlaybackError" + arg0 + ", " + arg1);
	}

	@Override
	public void onPlaybackEvent(EventType arg0, PlayerState arg1) {
		// TODO Auto-generated method stub
		System.out.println(">>>>>>>>>> onPlaybackEvent");
	}

}
