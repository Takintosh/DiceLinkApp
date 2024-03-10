package com.example.dicelinkapp.ui.view;

import android.content.Context;
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
        TextView tvSignUp = view.findViewById(R.id.tvSignUpHint);
        TextView tvForgotPassword = view.findViewById(R.id.tvForgotPassword);

        // Create spannable string for sign-up hint text
        String tvSignUpComplete = getString(R.string.signUpHint, getString(R.string.signUpLink));
        SpannableString ssSignUp = new SpannableString(tvSignUpComplete);
        // Set up clickable span for sign-up link
        int startIndex = tvSignUpComplete.indexOf(getString(R.string.signUpLink));
        int endIndex = startIndex + getString(R.string.signUpLink).length();
        ClickableSpan csSignUp = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Call onFragmentReplace() method of the activity to replace fragment
                if (mListener != null) {
                    mListener.onFragmentReplace(RegisterFragment.class.getName());
                }
            }
        };
        ssSignUp.setSpan(csSignUp, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSignUp.setText(ssSignUp);
        tvSignUp.setMovementMethod(LinkMovementMethod.getInstance());

        // Create a spannable string with the entire text as a link
        String stringForgotPassword = getString(R.string.forgotPassword);
        SpannableString ssForgotPassword = new SpannableString(stringForgotPassword);
        ClickableSpan csForgotPassword = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Call onFragmentReplace() method of the activity to replace fragment
                if (mListener != null) {
                    mListener.onFragmentReplace(PasswordRecoveryFragment.class.getName());
                }
            }
        };
        ssForgotPassword.setSpan(csForgotPassword, 0, ssForgotPassword.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvForgotPassword.setText(ssForgotPassword);
        tvForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());

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
                    if (getActivity() instanceof FragmentCallback) {
                        // Call saveSessionState() method of the activity to save session state
                        mListener.saveSessionState();
                        mListener.redirectToDashboard();
                    }
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