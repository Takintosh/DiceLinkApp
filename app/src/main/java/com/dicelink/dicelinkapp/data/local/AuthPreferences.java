package com.dicelink.dicelinkapp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthPreferences {

    // Name of Authentication shared preferences
    private static final String PREFS_NAME = "AuthPreferences";

    // Key for the values to be stored
    private static final String KEY_TOKEN = "token";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_USERNAME = "username";
    private static final long KEY_TOKEN_EXPIRATION = 0;
    private static final long KEY_REFRESH_TOKEN_EXPIRATION = 0;

    // SharedPreferences object
    private final SharedPreferences preferences;

    // Constructor
    public AuthPreferences (Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Save and retrieve values
    public void saveToken(String token) {
        preferences.edit().putString(KEY_TOKEN, token).apply();
    }
    public String getToken() {
        return preferences.getString(KEY_TOKEN, null);
    }

    public void saveRefreshToken(String refreshToken) {
        preferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply();
    }
    public String getRefreshToken() {
        return preferences.getString(KEY_REFRESH_TOKEN, null);
    }

    public void saveUsername(String username) {
        preferences.edit().putString(KEY_USERNAME, username).apply();
    }
    public String getUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    public void saveTokenExpiration(long tokenExpiration) {
        preferences.edit().putLong(String.valueOf(KEY_TOKEN_EXPIRATION), tokenExpiration).apply();
    }
    public long getTokenExpiration() {
        return preferences.getLong(String.valueOf(KEY_TOKEN_EXPIRATION), 0);
    }

    public void saveRefreshTokenExpiration(long refreshTokenExpiration) {
        preferences.edit().putLong(String.valueOf(KEY_REFRESH_TOKEN_EXPIRATION), refreshTokenExpiration).apply();
    }
    public long getRefreshTokenExpiration() {
        return preferences.getLong(String.valueOf(KEY_REFRESH_TOKEN_EXPIRATION), 0);
    }
}