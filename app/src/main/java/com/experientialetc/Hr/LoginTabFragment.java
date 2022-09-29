package com.experientialetc.Hr;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LoginTabFragment extends Fragment {

    EditText email,pass;
    TextView forgetPassword;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_gragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPassword = root.findViewById(R.id.forgetPassword);
        login = root.findViewById(R.id.loginbtn);

        email.setAlpha(0);
        pass.setAlpha(0);
        forgetPassword.setAlpha(0);
        login.setAlpha(0);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        forgetPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplication(), Home.class);
                startActivity(intent);


            }
        });


        return root;
    }
}
