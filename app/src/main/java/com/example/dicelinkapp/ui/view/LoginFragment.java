package com.example.dicelinkapp.ui.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dicelinkapp.R;


public class LoginFragment extends Fragment {

    Button btnSignIn, btnSignInGoogle;
    EditText etUsername, etPassword;
    String username, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private FragmentCallback mListener;

    // Called when the fragment is attached to the context
    public void onAttach(Context context) {
        // Get reference to shared preferences
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        super.onAttach(context);

        // Check if the context implements FragmentCallback interface
        if (context instanceof FragmentCallback) {
            mListener = (FragmentCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCallback");
        }
    }

    // Called to create the view hierarchy associated with the fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize views
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignInGoogle = view.findViewById(R.id.btnSignInGoogle);
        TextView textView = view.findViewById(R.id.tvSignUpHint);

        // Create spannable string for sign-up hint text
        String tvComplete = getString(R.string.signUpHint, getString(R.string.signUpLink));
        SpannableString spannableString = new SpannableString(tvComplete);

        // Set up clickable span for sign-up link
        int startIndex = tvComplete.indexOf(getString(R.string.signUpLink));
        int endIndex = startIndex + getString(R.string.signUpLink).length();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Call onFragmentReplace() method of the activity to replace fragment
                if (mListener != null) {
                    mListener.onFragmentReplace(RegisterFragment.class.getName());
                }
            }
        };
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Set click listener for sign-in button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username and password entered by the user
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                // Retrieve stored username and password from shared preferences
                String uUsername, uPassword;
                uUsername = preferences.getString("username", null);
                uPassword = preferences.getString("password", null);

                // Check if the entered credentials match the stored credentials
                if (username.equals(uUsername) && password.equals(uPassword)) {
                    // Show login success message and start DashboardActivity
                    Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    // Show invalid user message
                    Toast.makeText(getContext(), "Invalid User", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Set click listener for sign-in with Google button
        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No functionality added for sign up with Google button yet
            }
        });

        return view;
    }

}