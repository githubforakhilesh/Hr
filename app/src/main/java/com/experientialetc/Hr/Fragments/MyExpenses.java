package com.experientialetc.Hr.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.experientialetc.Hr.R;
import com.experientialetc.Hr.SecondFragment;


public class MyExpenses extends Fragment {
View back_button;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_expenses, container, false);
back_button=view.findViewById(R.id.expenses_back_button);
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