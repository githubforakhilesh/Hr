package com.experientialetc.Hr.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.experientialetc.Hr.R;
import com.experientialetc.Hr.SecondFragment;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class LeaveBalance extends Fragment {
    private ProgressBar progressBar;
    private AppCompatTextView progressText;
    private CircularProgressBar casual,medical,privileged,maternity;
    private TextView casual_text,medical_text,privileged_text,maternity_text;
    View back_button;
    int i = 0,j=0;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_leave_balance, container, false);
        progressBar=view.findViewById(R.id.progressBar);
        progressText=view.findViewById(R.id.txtProgress);
        casual=view.findViewById(R.id.leave_bal_casual_pro_bar);
        medical=view.findViewById(R.id.leave_bal_medical_pro_bar);
        privileged=view.findViewById(R.id.leave_bal_privileged_pro_bar);
        maternity=view.findViewById(R.id.leave_bal_maternity_pro_bar);
        casual_text=view.findViewById(R.id.txtProgress_casual);
        medical_text=view.findViewById(R.id.txtProgress_medical);
        privileged_text=view.findViewById(R.id.txtProgress_privileged);
        maternity_text=view.findViewById(R.id.txtProgress_maternity);
        back_button=view.findViewById(R.id.leave_bal_back_button);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 100) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

        handler.postDelayed(new Runnable() {
         int i=0;
            @Override
            public void run() {
                if (i <= 5) {
                    j=j+20;
                    casual_text.setText("" + i);
                    casual.setProgress(j);
                    i++;
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment  fragment = new SecondFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}