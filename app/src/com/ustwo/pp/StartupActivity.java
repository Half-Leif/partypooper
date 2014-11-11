package com.ustwo.pp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.JsonReader;

import com.ustwo.pp.network.Parser;
import com.ustwo.pp.network.ServerConnection;

public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		final String PARTY_POOPER_URL = "";
		new Thread(new Runnable() {

			@Override
			public void run() {
				ServerConnection sc = new ServerConnection();
				StartupParser sp = new StartupParser();
				sc.connectTo(PARTY_POOPER_URL, sp);
				
			}
		}).start();

	}

	private class StartupParser extends Parser {

		@Override
		public void parse(InputStream data) {
			try {
				readJsonStream(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private List<PartyPooperData> readJsonStream(InputStream in) throws IOException {
			JsonReader reader = new JsonReader(new InputStreamReader(in,
					"UTF-8"));
			try {
				return readMessagesArray(reader);
			} finally {
				reader.close();
			}

		}

		private List<PartyPooperData> readMessagesArray(JsonReader reader)
				throws IOException {
			List<PartyPooperData> messages = new ArrayList<PartyPooperData>();

			reader.beginArray();
			while (reader.hasNext()) {
				messages.add(readMessage(reader));
			}
			reader.endArray();
			return messages;
		}

		private PartyPooperData readMessage(JsonReader reader) throws IOException {
			// Placeholder, use Data from PartyPooper later
			long id = -1;
			String text = null;

			reader.beginObject();
			// Placeholder, put Partypooper data here
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("id")) {
					id = reader.nextLong();
				} else if (name.equals("text")) {
					text = reader.nextString();
				} else {
					reader.skipValue();
				}
			}

			reader.endObject();

			return new PartyPooperData(id,text);
		}
		// From developer.android.com/reference/android/util/JsonReader.html
		// Read custom object

		/*
		 * public User readUser(JsonReader reader) throws IOException { String
		 * userame = null; int followersCount = -1;
		 * 
		 * reader.beginObject(); while (reader.hasNext()) { String name =
		 * reader.nextName(); if (name.equals("name")) { username =
		 * reader.nextString(); } else if (name.equals("followers_count")) {
		 * followersCount = reader.nextInt(); } else { reader.skipValue(); } }
		 * reader.endObject(); return new User(username, followersCount); }
		 */

	}
	
	private class PartyPooperData {
		
		public PartyPooperData() {
		
		}
		public PartyPooperData(long arg0, String arg1) {
		
		}
	}
}