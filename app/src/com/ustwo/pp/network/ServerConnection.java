package com.ustwo.pp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Message;
import android.util.Log;

public class ServerConnection {
	// TODO SERVER REQUEST
	// TODO RETURN ANSWER as STRING
	// TODO PASS PARSER as ARG
	public static final String TAG = "ServerConnection";
	HttpURLConnection connection;
	public ServerConnection() {
	}
	public void connectTo(final String url, Parser parser) {
		String newString = "";
		try {
			connection = (HttpURLConnection) new URL(url)
			.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.connect();
			int statusCode = connection.getResponseCode();
			String message = connection.getResponseMessage();
			Log.v(TAG, "StatusCode:\t" + statusCode + "\nMessage:\t"
					+ message);
			if (message == "OK") {
				InputStream input = connection.getInputStream();
				//parser.parse("");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public String readStream(InputStream input) {
		String textToReturn = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				stringBuilder.append(line + "\n");
			}
			textToReturn = stringBuilder.toString();
		} catch (Exception e) {
		} finally {
			try {
				input.close();
			} catch (Exception e) {
			}
		}
		return textToReturn;
	}
}