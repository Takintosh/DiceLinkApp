package com.dicelink.dicelinkapp.data.remote;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    @SerializedName("terms")
    private boolean terms;

    @SerializedName("privacy")
    private boolean privacy;

    // Getters & setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public boolean isTerms() {
        return terms;
    }
    public void setTerms(boolean terms) {
        this.terms = terms;
    }

    public boolean isPrivacy() {
        return privacy;
    }
    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }
}
