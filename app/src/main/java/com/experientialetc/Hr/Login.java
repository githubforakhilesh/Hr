package com.experientialetc.Hr;

import static com.experientialetc.Hr.network.BaseUrlApi.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.experientialetc.Hr.network.APIInterface;
import com.experientialetc.Hr.network.ApiResponse;
import com.experientialetc.Hr.network.BaseUrlApi;
import com.experientialetc.Hr.network.CommonApi;
import com.experientialetc.Hr.response.LoginApiResponse;
import com.experientialetc.Hr.response.TokenApiResponse;
import com.experientialetc.Hr.utils.AppPrefs;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity implements ApiResponse {

    Button btn;
    TextView textView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    EditText e1, e2;

    ProgressDialog pd;

    LoginApiResponse loginApiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.SignInLogin);
        textView = findViewById(R.id.SignUpText);
        e1 = findViewById(R.id.editTextTextPersonEmail);
        e2 = findViewById(R.id.editTextTextPersonPassword);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        preferences = getSharedPreferences("login_credential", Context.MODE_PRIVATE);
        editor = preferences.edit();


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);


        // getSupportActionBar().hide();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd = new ProgressDialog(Login.this);
                pd.setMessage("Please Wait!");
                pd.show();

                String uname = e1.getText().toString();
                String pass = e2.getText().toString();

                if (uname.isEmpty() || pass.isEmpty()) {

                    if (uname.isEmpty()) {
                        e1.setError("Please Enter Username");
                        pd.dismiss();
                    }

                    if (pass.isEmpty()) {
                        e2.setError("Please Enter Password");
                        pd.dismiss();
                    }

                } else {

                    String bearerToken = "Bearer " + AppPrefs.getStringFinalValue(getApplicationContext(), AppPrefs.KEY_USER_Token);

                    APIInterface login = BaseUrlApi.getClient().create(APIInterface.class);
                    Call<LoginApiResponse> call = login.loginApi(bearerToken, uname, pass);
                    call.enqueue(new Callback<LoginApiResponse>() {

                        @Override
                        public void onResponse(@NonNull Call<LoginApiResponse> call, @NonNull retrofit2.Response<LoginApiResponse> response) {

                            if (response.isSuccessful()) {
                                //Toast.makeText(Login.this, ""+response, Toast.LENGTH_SHORT).show();

                                LoginApiResponse loginResponse = response.body();
                                String status = loginResponse.getStatus();
                                if (loginResponse.getMessage().equals("success")) {
                                    if (status.equals("1")) {
                                        Intent intent = new Intent(Login.this, Home.class);
                                        startActivity(intent);
                                        finish();
                                        pd.dismiss();


                                    } else if (status.equals("0")) {

                                        Intent intent = new Intent(Login.this, GeneratePasswordForNewUser.class);
                                        startActivity(intent);
                                        finish();
                                        pd.dismiss();

                                    }

//                                    editor.putString("user", e1.getText().toString());
//                                    editor.putString("pass", e2.getText().toString());
//                                    editor.apply();
//                                    editor.commit();
//                                    Intent i = new Intent(Login.this, Home.class);
//                                    startActivity(i);
//                                    finish();
                                } else if (loginResponse.equals("failed")) {

                                    Toast.makeText(Login.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();

                                }


                            } else
                                Toast.makeText(Login.this, "" + response, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<LoginApiResponse> call, @NonNull Throwable t) {
                            Toast.makeText(Login.this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }

    public void getAuthTokenApi() {
        new CommonApi().getToken(Login.this, this);
    }

    @Override
    public void onSuccess(String type, Object data) {
        if (data != null) {
            TokenApiResponse result = (TokenApiResponse) data;
            String key = result.getKey();
            AppPrefs.setFinalStringValue(this, AppPrefs.KEY_USER_Token, key);
        } else {
            Toast.makeText(this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Object data) {
        Toast.makeText(this, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
    }

    private static String openssl_encrypt(String data) {
        try {
            String strKey = "bRuD5WYw5wd0rdHR";
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] ivBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(ivBytes, 0, 16);

            // Encrypt
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encryptedCipherBytes = cipher.doFinal(data.getBytes());

            String s = Base64.encodeToString(encryptedCipherBytes, 0);
            Log.e("ENCRYPT", s);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAuthTokenApi();
    }
}
