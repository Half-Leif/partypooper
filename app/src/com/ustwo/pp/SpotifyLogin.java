package com.ustwo.pp;

import android.util.Log;

import com.spotify.sdk.android.authentication.SpotifyAuthentication;
import com.spotify.sdk.android.playback.ConnectionStateCallback;
import com.spotify.sdk.android.playback.PlayerNotificationCallback;
import com.spotify.sdk.android.playback.PlayerState;

public class SpotifyLogin implements 
PlayerNotificationCallback, ConnectionStateCallback{
	public static final String TAG = "SpotifyLogin";
	public static final String CLIENT_ID = "SpotifyLogin";
	public static final String REDIRECT_URI = "REDIRECT_URI";
	
	
	public void Login(){
		//SpotifyAuthentication.openAuthWindow(CLIENT_ID, "token", REDIRECT_URI,
	     //       new String[]{"user-read-private", "streaming"}, null, this);
	}
	@Override
    public void onLoggedIn() {
        Log.d(TAG, "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d(TAG, "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d(TAG, "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d(TAG, "Temporary error occurred");
    }

    @Override
    public void onNewCredentials(String s) {
        Log.d(TAG, "User credentials blob received");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d(TAG, "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        Log.d(TAG, "Playback event received: " + eventType.name());
    }

    @Override
    public void onPlaybackError(ErrorType errorType, String errorDetails) {
        Log.d(TAG, "Playback error received: " + errorType.name());
    }


}
