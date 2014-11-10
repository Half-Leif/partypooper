package com.ustwo.pp;

import com.spotify.sdk.android.authentication.SpotifyAuthentication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity implements OnClickListener{
	public final static String CLIENT_ID = "some_id";
	public final static String TAG = "LoginActivity";
	EditText emailEditText, passwordEditText;
	Button loginButton;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }
    private void initUI(){
    	
    	//Login TextFields
    	emailEditText = (EditText) findViewById(R.id.login_email);
    	passwordEditText = (EditText) findViewById(R.id.login_password);
    	
    	//Login button
    	loginButton = (Button) findViewById(R.id.login_button);
    	loginButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		
		case R.id.login_button:
			Log.v(TAG, "Log in");
			//SPOTIFY AUTHENTICATION
			//SpotifyAuthentication.openAuthWindow(CLIENT_ID,"Authtoken", arg2, arg3, arg4, arg5)
			break;
		
		default:
			break;
			
		}		
	}
}
