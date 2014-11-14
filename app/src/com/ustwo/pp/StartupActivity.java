package com.ustwo.pp;

import android.app.Activity;
import android.os.Bundle;

import com.ustwo.pp.network.ServerConnection;

public class StartupActivity extends Activity {

    public static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        final String PARTY_POOPER_URL = "";
        new Thread(new Runnable() {

            @Override
            public void run() {
                ServerConnection sc = new ServerConnection();

            }
        }).start();

    }

}