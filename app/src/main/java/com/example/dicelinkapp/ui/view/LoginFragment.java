package com.example.dicelinkapp.ui.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dicelinkapp.R;


public class LoginFragment extends Fragment {

    Button btnSignIn, btnSignInGoogle;
    EditText etUsername, etPassword;
    CallbackFragment callbackFragment;
    String username, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void onAttach(Context context) {
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignInGoogle = view.findViewById(R.id.btnSignInGoogle);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                String uUsername, uPassword;
                uUsername = preferences.getString("username", null);
                uPassword = preferences.getString("password", null);
                if (username.equals(uUsername) && password.equals(uPassword)) {
                    Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Debug - Cambiar Fragment
                if(callbackFragment != null) {
                    callbackFragment.replaceFragment();
                }
            }
        });

        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }

}