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
    TextView t1,t2;
    String url="https://www.experientialetc.com/hrapp/api/GeneratePassword.php";
    RequestQueue requestQueue;


    EditText e1,e2,e3,e4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_password_for_new_user);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        buttonSignUp=findViewById(R.id.Done);

        String email = getIntent().getStringExtra("email");
        String oldPassword = getIntent().getStringExtra("pass");

     e1=findViewById(R.id.changeName);
     e2=findViewById(R.id.changeEmail);
     e3=findViewById(R.id.changePassword);
     e4=findViewById(R.id.changePasswordAgain);

     t1=findViewById(R.id.emailTv);
     t2=findViewById(R.id.oldpassTv);

     t1.setText(email);
     t2.setText(oldPassword);

        requestQueue= Volley.newRequestQueue(this);


        //Toast.makeText(this, ""+email+oldPassword, Toast.LENGTH_SHORT).show();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               checkPassword();


                ProgressDialog progressDialog = new ProgressDialog(GeneratePasswordForNewUser.this);
                progressDialog.setMessage("Please Wait !!");
                progressDialog.show();

                //String password = e4.getText().toString();
                String passwordInput = e3.getText().toString().trim();
                String ConfitmpasswordInput = e4.getText().toString().trim();
                if ( passwordInput.isEmpty())
                {
                    if(passwordInput.isEmpty())
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
//
//                            data.put("emp_name", e1.getText().toString());
//                            data.put("emp_emailid", e2.getText().toString());
//                            data.put("emp_mobile_number", e3.getText().toString());
//                            data.put("password", e4.getText().toString());
//
                            data.put("newpassword", e3.getText().toString());
                           data.put("confirmpassword", e4.getText().toString());
                           data.put("oldpassword", t2.getText().toString());
                            data.put("email", t1.getText().toString());
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

    private void checkPassword() {
        String passwordInput = e3.getText().toString().trim();
        String ConfitmpasswordInput = e4.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            e3.setError("Field can't be empty");
        }  if (passwordInput.length()<5) {
            e3.setError("Password must be at least 5 characters");
        }
        if (!passwordInput.equals(ConfitmpasswordInput)) {
            Toast.makeText(this, "Password are not same", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Password matched", Toast.LENGTH_SHORT).show();

        }
    }
}
