package com.ustwo.pp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {
	private static final String KEY_ID = "id";
	private static final String KEY_UPDATED_AT = "updated_at";
	private static final String KEY_SUBSCRIBER_COUNT = "subscriber_count";
	private static final String KEY_NAME = "name";
	private static final String KEY_CREATED_AT = "created_at";
	//private static final String KEY_TRACKS = "tracks";
	//private static final String KEY_SUBSCRIBERS = "subscribers";
	private static final String KEY_MESSAGES = "messages";

	private String id;
	private String updatedAt;
	private int subscriberCount;
	private String name;
	private String createdAt;
	
	/**
	 * No tracks or subscribers at servers
	 */
	/*
	private ArrayList<Track> tracks;
	private ArrayList<Subscriber> subscribers;
	*/
	private ArrayList<Message> messages;

	public Server(JSONObject root) {

		this.id = (String) getObject(root, KEY_ID);
		this.updatedAt = (String) getObject(root, KEY_UPDATED_AT);
		this.subscriberCount = (Integer) getObject(root, KEY_SUBSCRIBER_COUNT);
		this.createdAt = (String) getObject(root, KEY_CREATED_AT);
		this.name = (String) getObject(root, KEY_NAME);
		this.createdAt = (String) getObject(root, KEY_ID);
		JSONArray messageArray = getJSONArray(root, KEY_MESSAGES);
		messages = new ArrayList<Message>();
		
		for (int i = 0; i < messageArray.length(); i++) {
				try {
					Message message = new Message(messageArray.getJSONObject(i));
					System.out.println("MESSAGE: "+message);
					messages.add(message);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}
	}

	private JSONArray getJSONArray(JSONObject root, String Key) {
		try {
			return root.getJSONArray(Key);
		} catch (JSONException e) {
			// 
			e.printStackTrace();
			return null;
		}

	}

	static JSONObject getJSONObject(JSONObject root, String key) {
		try {
			return (JSONObject) root.get(key);
		} catch (JSONException e) {
			return null;
		}
	}

	static Object getObject(JSONObject root, String key) {
		try {
			return root.get(key);
		} catch (JSONException e) {
			return "";
		}
	}

	public String getId() {
		return id;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public int getSubscriberCount() {
		return subscriberCount;
	}

	public String getName() {
		return name;
	}

	public String getCreatedAt() {
		return createdAt;
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
		
	}
}

class Message {
	private static final String MESSAGE_KEY_ID = "id";
	private static final String MESSAGE_KEY_BODY = "body";
	private static final String MESSAGE_KEY_UPDATED_AT = "updated_at";
	private static final String MESSAGE_KEY_CREATED_AT = "created_at";
	private static final String MESSAGE_KEY_USER = "user";
	String id;
	String body;
	String createdAt;
	String updatedAt;
	User user;
	
	Message(JSONObject root) {
		this.id = (String) Server.getObject(root, MESSAGE_KEY_ID);
		this.body = (String) Server.getObject(root, MESSAGE_KEY_BODY);
		this.createdAt = (String) Server.getObject(root, MESSAGE_KEY_CREATED_AT);
		this.updatedAt = (String) Server.getObject(root, MESSAGE_KEY_UPDATED_AT);
		this.user = new User(Server.getJSONObject(root, MESSAGE_KEY_USER));
		
	}
	public String getId() {
		return id;
	}
	public String getBody() {
		return body;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public User getUser() {
		return user;
	}
}
class Subscriber {
	Subscriber(JSONObject root) {

	}
}

class Track {
	Track(JSONObject root) {

	}
}