package com.ustwo.pp;

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
        //nextScreen();
        callSpotify();
        //initUI();
    }
    private void callSpotify(){
    	 Log.v(TAG, "Log in");
         String callbackUrl = getString(R.string.spotify_redirect_scheme)
                 + "://" + getString(R.string.spotify_redirect_host);
         System.out.println(">>>>> callbackUrl is " + callbackUrl);
         SpotifyAuthentication.openAuthWindow(CLIENT_ID, "token",
                 callbackUrl,
                 new String[] { "user-read-private" }, null,
                 this);
    }
    private void nextScreen(){
    	Intent startupIntent = new Intent(this, StartupActivity.class);
    	this.startActivity(startupIntent);
    	this.finish();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        System.out.println(">>>>>>>> On new intent");
        Uri uri = intent.getData();
        if (uri != null) {
            AuthenticationResponse response = SpotifyAuthentication
                    .parseOauthResponse(uri);
            System.out.println(">>>>>>> Got response" + uri.toString()+"\tResponse: "+response);
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
