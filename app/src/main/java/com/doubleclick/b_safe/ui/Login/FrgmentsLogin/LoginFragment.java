package com.doubleclick.b_safe.ui.Login.FrgmentsLogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubleclick.b_safe.R;

public class LoginFragment extends Fragment {

    private TextView signup, forgetpassword;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        signup = view.findViewById(R.id.signup);
        forgetpassword = view.findViewById(R.id.forgetpassword);
        signup.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());

        });

        forgetpassword.setOnClickListener(v -> {

            Navigation.findNavController(v).navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment());

        });

        return view;
    }
}