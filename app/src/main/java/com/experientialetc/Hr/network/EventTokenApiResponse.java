package com.experientialetc.Hr.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTokenApiResponse {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expiration")
    @Expose
    private String expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}
