package com.ustwo.pp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class ServerConnection {
	// TODO SERVER REQUEST
	// TODO RETURN ANSWER as STRING
	// TODO PASS PARSER as ARG

	public static final String TAG = "ServerConnection";
	HttpURLConnection connection;

	public ServerConnection() {

	}

	public String connectTo(final String url) {
		String newString = "";
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
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
						//TODO Get the string from URL //newString = readStream(input);
						
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

			private String readStream(InputStream input) {
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

		});
		thread.start();
		return url;
	}
}
