package com.dicelink.dicelinkapp.model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("args")
    private Args args;

    public Args getArgs() {
        return args;
    }
    public void setArgs(Args args) {
        this.args = args;
    }

    public static class Args {
        @SerializedName("token")
        private String token;

        @SerializedName("refresh_token")
        private String refreshToken;

        @SerializedName("token_expiration")
        private String tokenExpiration;

        @SerializedName("refresh_token_expiration")
        private String refreshTokenExpiration;

        @SerializedName("username")
        private String username;

        @SerializedName("status")
        private String status;

        @SerializedName("msg")
        private String message;

        // Getters y setters
        public String getToken() {
            return token;
        }
        public void setToken(String token) {
            this.token = token;
        }

        public String getRefreshToken() {
            return refreshToken;
        }
        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getTokenExpiration() {
            return tokenExpiration;
        }
        public void setTokenExpiration(String tokenExpiration) {
            this.tokenExpiration = tokenExpiration;
        }

        public String getRefreshTokenExpiration() {
            return refreshTokenExpiration;
        }
        public void setRefreshTokenExpiration(String refreshTokenExpiration) {
            this.refreshTokenExpiration = refreshTokenExpiration;
        }

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }

}