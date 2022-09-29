package com.experientialetc.Hr.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.experientialetc.Hr.network.APIInterface;
import com.experientialetc.Hr.network.BaseUrlApi;
import com.experientialetc.Hr.response.TokenApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TokenCheckService extends Service {
    int count = 0;
    boolean apiCall = false;

    Handler handler = new Handler();
    private Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(periodicUpdate, 1000);
            count++;
            if ((15 * 60) - 5 < count && !apiCall) {
                apiCall = true;
                getToken();
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(periodicUpdate);
        return START_STICKY;
    }

    void getToken() {
        String user = "kpmg_presenter";
        String pass = "kpmg_presenter";

        APIInterface getToken = BaseUrlApi.getClient().create(APIInterface.class);
        Call<TokenApiResponse> call = getToken.jwtAuthorizeApi(user, pass);

        call.enqueue(new Callback<TokenApiResponse>() {
            @Override
            public void onResponse(Call<TokenApiResponse> call, Response<TokenApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TokenApiResponse result = response.body();
                    String key = result.getKey();
                    //AppPrefs.setFinalStringValue(getApplicationContext(), AppPrefs.KEY_USER_Token, key);
                    count = 0;
                    apiCall = false;
                } else {
                    apiCall = false;
                }
            }

            @Override
            public void onFailure(Call<TokenApiResponse> call, Throwable t) {
                apiCall = false;
            }
        });
    }

}
