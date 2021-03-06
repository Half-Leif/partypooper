package com.ustwo.pp;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";
    private static final String KEY_SP_USER_ID = "sp_user_id";
    private static final String KEY_PROFILE_IMAGE_URL = "profile_image_url";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_NAME = "name";

    private String id;
    private String createdAt;
    private String updatedAt;
    private String spUserID;
    private String profileImageURL;
    private String accessToken;
    private String name;

    public User(JSONObject root){
        this.id = getString(root,KEY_ID);
        this.createdAt = getString(root,KEY_CREATED_AT);
        this.updatedAt = getString(root, KEY_UPDATED_AT);
        this.spUserID = getString(root, KEY_SP_USER_ID);
        this.profileImageURL = getString(root, KEY_PROFILE_IMAGE_URL);
        this.accessToken = getString(root, KEY_ACCESS_TOKEN);
        this.name = getString(root, KEY_NAME);

    }

    public String getId() {
        return id;
    }


    private String getString(JSONObject root, String key) {
        try {
            return root.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    public String getSpUserID() {
        return spUserID;
    }


    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /*
     * Implementation of parcelable
     */

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(spUserID);
        dest.writeString(profileImageURL);
        dest.writeString(accessToken);
        dest.writeString(name);
    }

    private User(Parcel in) {
        id = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        spUserID = in.readString();
        profileImageURL = in.readString();
        accessToken = in.readString();
        name = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }

    };

}
