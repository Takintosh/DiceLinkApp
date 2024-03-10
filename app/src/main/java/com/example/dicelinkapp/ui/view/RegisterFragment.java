package com.example.dicelinkapp.ui.view;

import android.content.Context;
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

public class RegisterFragment extends Fragment {

    Button btnSignUp, btnSignUpGoogle;
    EditText etUsername, etEmail, etPassword;
    String username, email, password;
    CallbackFragment callbackFragment;
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUpGoogle = view.findViewById(R.id.btnSignUpGoogle);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();
                Toast.makeText(getContext(), "User Registered", Toast.LENGTH_SHORT).show();

                // Debug - Cambiar Activity
            }
        });

        btnSignUpGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        return view;
    }

}