package com.ustwo.pp.network;

import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser extends Parser {

    @Override
    public void parse(InputStream data) {
        try {
            System.out.println(">>>>> PARSING!!!!");
            String json = ServerConnection.readStream(data);
            JSONObject root = new JSONObject(json);
            System.out.println(root.toString(2));
            System.out.println(">>>>> Done parsing");
        } catch (JSONException e) {
            Log.e("", "Got error when trying to parse json from " + data);
        }
        System.out.println(">>>>>>>> Leaving parse");
    }
}
