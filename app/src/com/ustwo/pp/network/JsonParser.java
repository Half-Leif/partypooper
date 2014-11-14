package com.ustwo.pp.network;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser extends Parser {
	
	JSONObject root = null;
	JSONArray arrayRoot = null;
	String json;
    @Override
    public void parse(InputStream data) {
    	
        try {
            System.out.println(">>>>> PARSING!!!!");
            json = ServerConnection.readStream(data);
            root = new JSONObject(json);
           // System.out.println(root.toString(2));
            System.out.println(">>>>> Done parsing");
            
        } catch (JSONException e) {
            Log.e("", "Got error when trying to parse json from " + data);
            System.out.println("json =\n"+json);
        }
        System.out.println(">>>>>>>> Leaving parse");
    }
   
    public void parseArray(InputStream data) {
    	
        try {
            System.out.println(">>>>> ARRAY PARSING!!!!");
            json = ServerConnection.readStream(data);
            arrayRoot = new JSONArray(json);
            System.out.println(arrayRoot.getJSONObject(0).toString(2));
            System.out.println("ARRAYROOT! "+arrayRoot.length());
            System.out.println(">>>>> Done parsing");
            
        } catch (JSONException e) {
            Log.e("", "Got error when trying to parse json from " + data);
            System.out.println("json =\n"+json);
        }
        System.out.println(">>>>>>>> Leaving parse");
    }
    
    public String getParsedContent(){
    	return this.root.toString();
    }
    public JSONObject getRoot(){
    	return this.root;
    }
    public JSONArray getArrayRoot(){
    	return this.arrayRoot;
    }
  

}
