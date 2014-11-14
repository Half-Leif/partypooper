package com.ustwo.pp.network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


import android.util.Log;

public class ServerConnection {
	// TODO SERVER REQUEST
	// TODO RETURN ANSWER as STRING
	// TODO PASS PARSER as ARG
	public static final String TAG = "ServerConnection";
	HttpURLConnection connection;

	public ServerConnection() {
	}

	public void post(final String url, final String body, Parser parser) {
		OutputStream out = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			if (body != null) {
				out = new BufferedOutputStream(connection.getOutputStream());
				out.write(body.getBytes("UTF-8"));
				out.flush();
			}

			int statusCode = connection.getResponseCode();
			String message = connection.getResponseMessage();
			Log.v(TAG, "StatusCode:\t" + statusCode + "\nMessage:\t" + message);
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream input = connection.getInputStream();
				

				parser.parse(input);
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
	
	public void jsonPost(final String url, final String body, Parser parser) {
		OutputStream out = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();

			connection.addRequestProperty("Content-Type", "application/json;charset=utf-8");

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			if (body != null) {
				out = new BufferedOutputStream(connection.getOutputStream());
				out.write(body.getBytes("UTF-8"));
				out.flush();
			}

			int statusCode = connection.getResponseCode();
			String message = connection.getResponseMessage();
			Log.v(TAG, "StatusCode:\t" + statusCode + "\nMessage:\t" + message);
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream input = connection.getInputStream();
				
				parser.parse(input);
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
	public void jsonGet(final String url, final String token, Parser parser) {
		OutputStream out = null;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
			final String mToken = "Token token="+token;
			connection.addRequestProperty("Authorization",mToken);
			
			connection.setRequestMethod("GET");
		    //connection.setDoInput(true);
			//connection.setDoOutput(false);
			connection.connect();

			int statusCode = connection.getResponseCode();
			String message = connection.getResponseMessage();
			Log.v(TAG, "StatusCode:\t" + statusCode + "\nMessage:\t" + message);
			if (statusCode == HttpURLConnection.HTTP_OK) {
				InputStream input = connection.getInputStream();
				//String json = ServerConnection.readStream(input);
				//JSONArray roots;
				
	            //root = new JSONObject(json);
				parser.parseArray(input);
				
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
	public static String readStream(InputStream input) {
		String textToReturn = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
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