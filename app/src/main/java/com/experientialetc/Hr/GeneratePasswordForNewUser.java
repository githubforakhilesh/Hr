package com.experientialetc.Hr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
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

import java.util.HashMap;
import java.util.Map;

public class GeneratePasswordForNewUser extends AppCompatActivity {

    Button buttonSignUp;

    String url="https://www.experientialetc.com/hrapp/api/signup.php";
    RequestQueue requestQueue;


    EditText e1,e2,e3,e4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_password_for_new_user);

        buttonSignUp=findViewById(R.id.Done);

     e1=findViewById(R.id.changeName);
     e2=findViewById(R.id.changeEmail);
     e3=findViewById(R.id.changePhone);
     e4=findViewById(R.id.changePassword);

        requestQueue= Volley.newRequestQueue(this);


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(GeneratePasswordForNewUser.this);
                progressDialog.setMessage("Please Wait !!");
                progressDialog.show();

                String password = e4.getText().toString();
                if ( password.isEmpty())
                {
                    if(password.isEmpty())
                    {
                        e4.setError("Please enter password");
                    }


                }
                else {


                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Intent i = new Intent(GeneratePasswordForNewUser.this,Login.class);
                            startActivity(i);
                            finish();
                            //Toast.makeText(getApplicationContext(), ""+response, Toast.LENGTH_SHORT).show();


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<String, String>();

                            data.put("emp_name", e1.getText().toString());
                            data.put("emp_emailid", e2.getText().toString());
                            data.put("emp_mobile_number", e3.getText().toString());
                            data.put("password", e4.getText().toString());
                            return data;
                        }
                    };

                    requestQueue.add(request);

                }


            }
        });




        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // getSupportActionBar().hide();


    }
}
