package com.nest.scratch;


import android.net.Uri;

/**
 * Coded with love.
 * Created by black on 1/30/17.
 */

public class User {
    private String username;
    private String domain;
    private final String userID;
    private Uri profile_picture;

    public User(String userID){
        this.userID=userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUserID() {
        return userID;
    }

    public Uri getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(Uri profile_picture) {
        this.profile_picture = profile_picture;
    }
}
