package com.experientialetc.Hr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignupTabFragment extends Fragment {

    EditText email,mobile,pass,name;
    RequestQueue requestQueue;
    Button btnSignup;
    String url="https://www.experientialetc.com/hrapp/api/login.php";

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);


        name = root.findViewById(R.id.edit_text_name);
        email = root.findViewById(R.id.edit_text_email);
        mobile = root.findViewById(R.id.edit_text_mobile);
        pass = root.findViewById(R.id.edit_text_pass);
        btnSignup = root.findViewById(R.id.buttonsignup);

        requestQueue= Volley.newRequestQueue(getContext());


        email.setAlpha(0);
        pass.setAlpha(0);
        name.setAlpha(0);
        mobile.setAlpha(0);
        btnSignup.setAlpha(0);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        name.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mobile.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        btnSignup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setMessage("Please Wait");
                pd.show();

                String emp_name = name.getText().toString();
                String emp_email = email.getText().toString();
                String emp_mobile = mobile.getText().toString();
                String emp_pass = pass.getText().toString();

                if(emp_name.isEmpty() || emp_email.isEmpty() || emp_mobile.isEmpty() || emp_pass.isEmpty())
                {
                    if(emp_name.isEmpty())
                    {
                    name.setError("Please enter name");
                    }
                    if(emp_email.isEmpty())
                    {
                        email.setError("Please enter email");
                    }
                    if(emp_mobile.isEmpty())
                    {
                        mobile.setError("Please enter mobile");
                    }
                    if (emp_pass.isEmpty())
                    {
                        pass.setError("Enter password");
                    }

                }
                else {

                   /* e1.setError(null);
                    e2.setError(null);
                    e3.setError(null);
                    e4.setError(null);
*/
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                            Log.i("resonse===",response);


                            //Intent intent = new Intent(getActivity().getApplication(), LoginTabFragment.class);
                            //startActivity(intent);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<String, String>();
                            data.put("emp_name", name.getText().toString());
                            data.put("emp_emailid", email.getText().toString());
                            data.put("emp_mobile_number", mobile.getText().toString());
                            data.put("password", pass.getText().toString());
                            return data;
                        }
                    };

                    requestQueue.add(request);

                }


            }

        });

        return root;
    }
}
