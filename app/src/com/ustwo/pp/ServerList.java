package com.ustwo.pp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

public class ServerList {

	public ArrayList<Server> servers;

	public ServerList(JSONArray rootArray) {
		servers = new ArrayList<Server>();
		int len = rootArray.length();
		for (int i = 0; i < len; i++) {
			try {
				Server server = new Server(rootArray.getJSONObject(i));
				servers.add(server);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}
	public ArrayList<Server> getServers(){
		return servers;
	}

}
