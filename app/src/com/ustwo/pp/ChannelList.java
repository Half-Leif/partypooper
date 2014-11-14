package com.ustwo.pp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class ChannelList {

	public ArrayList<Channel> servers;

	public ChannelList(JSONArray rootArray) {
		servers = new ArrayList<Channel>();
		int len = rootArray.length();
		for (int i = 0; i < len; i++) {
			try {
				Channel server = new Channel(rootArray.getJSONObject(i));
				servers.add(server);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}
	public ArrayList<Channel> getServers(){
		return servers;
	}

}
