package com.experientialetc.Hr.network;

import android.app.Activity;


import com.experientialetc.Hr.response.TokenApiResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonApi {

    public void getToken(Activity context, ApiResponse apiResponse) {
        String user = "hr_admin";
        String pass = "hr_admin";

        APIInterface getToken = BaseUrlApi.getClient().create(APIInterface.class);
        Call<TokenApiResponse> call = getToken.jwtAuthorizeApi(user, pass);

        call.enqueue(new Callback<TokenApiResponse>() {
            @Override
            public void onResponse(Call<TokenApiResponse> call, Response<TokenApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.onSuccess("success", response.body());
                } else {
                    apiResponse.onSuccess("fail", response.body());
                }
            }

            @Override
            public void onFailure(Call<TokenApiResponse> call, Throwable t) {
                apiResponse.onFailure(t);
            }
        });
    }

    public void getTokenForEvent(ApiResponse apiResponse) {
//        String user = "admin";
//        String pass = "Pass@4321";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Username", "admin");
        jsonObject.addProperty("Password", "Pass@4321");

        APIInterface getToken = BaseUrlApi.getClient().create(APIInterface.class);
        Call<EventTokenApiResponse> call = getToken.jwtAuthorizeApiForEvent(jsonObject);

        call.enqueue(new Callback<EventTokenApiResponse>() {
            @Override
            public void onResponse(Call<EventTokenApiResponse> call, Response<EventTokenApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.onSuccess("success", response.body());
                } else {
                    apiResponse.onSuccess("fail", response.body());
                }
            }

            @Override
            public void onFailure(Call<EventTokenApiResponse> call, Throwable t) {
                apiResponse.onFailure(t);
            }
        });
    }
}
