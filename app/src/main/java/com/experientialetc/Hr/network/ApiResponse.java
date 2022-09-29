package com.experientialetc.Hr.network;

public interface ApiResponse {

    void onSuccess(String type, Object data);

    void onFailure(Object data);
}
