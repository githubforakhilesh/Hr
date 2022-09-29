package com.experientialetc.Hr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    EditText et_code, etPassword, etRepeatPassword;
    final int MIN_PASSWORD_LENGTH = 1;
    RequestQueue requestQueue;
    String url="https://www.experientialetc.com/hrapp/api/signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            viewInitializations();
    }


}
    void viewInitializations() {
        //et_code = findViewById(R.id.et_code);
        etPassword = findViewById(R.id.et_password);
        etRepeatPassword = findViewById(R.id.et_repeat_password);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {

        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }
        if (etRepeatPassword.getText().toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password");
            return false;
        }

        // checking minimum password Length
        if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        // Checking if repeat password is same
        if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
            etRepeatPassword.setError("Password does not match");
            return false;
        }
        return true;
    }


    // Hook Click Event

    public void performResetPassword (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //pd.dismiss();
                    Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();
                    Log.i("resonse===",response);


                    //Intent intent = new Intent(getActivity().getApplication(), LoginTabFragment.class);
                    //startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<String, String>();

                    data.put("password", etPassword.getText().toString());
                    return data;
                }
            };

            requestQueue.add(request);

            // Here you can call you API

        }
    }}
