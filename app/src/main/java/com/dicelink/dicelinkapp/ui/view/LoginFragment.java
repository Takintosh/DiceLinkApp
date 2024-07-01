package com.dicelink.dicelinkapp.ui.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.data.local.AuthPreferences;
import com.dicelink.dicelinkapp.data.remote.ApiClient;
import com.dicelink.dicelinkapp.data.remote.AuthApiService;
import com.dicelink.dicelinkapp.data.remote.LoginRequest;
import com.dicelink.dicelinkapp.model.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    Button btnSignIn, btnSignInGoogle;
    EditText etUsername, etPassword;
    String username, password;
    private FragmentCallback mListener;


    // Called when the fragment is attached to the context
    public void onAttach(Context context) {
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

                // Disable sign-in buttons to prevent multiple clicks
                btnSignIn.setEnabled(false);
                btnSignInGoogle.setEnabled(false);

                // Retrieve username and password entered by the user
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                // Create an instance of the AuthApiService interface
                AuthApiService apiService = ApiClient.getClient().create(AuthApiService.class);

                // Create a new instance of the LoginRequest class
                LoginRequest loginRequest = new LoginRequest();

                // Set the username and password in the login request object
                loginRequest.setUsername(username);
                loginRequest.setPassword(password);

                // Call the loginUser() method of the AuthApiService interface
                Call<AuthResponse> call = apiService.loginUser(loginRequest);

                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                        // Verify response status code
                        if (response.code() == 200) {

                            // Create an instance of AuthPreferences
                            AuthPreferences authPrefs = new AuthPreferences(getContext());


                            // Save data to AuthPreferences
                            assert response.body() != null;
                            authPrefs.saveToken(response.body().getArgs().getToken());
                            authPrefs.saveRefreshToken(response.body().getArgs().getRefreshToken());
                            authPrefs.saveUsername(response.body().getArgs().getUsername());
                            authPrefs.saveTokenExpiration(Long.parseLong(response.body().getArgs().getTokenExpiration()));
                            authPrefs.saveRefreshTokenExpiration(Long.parseLong(response.body().getArgs().getRefreshTokenExpiration()));

                            Log.d("LoginFragment", authPrefs.getToken());

                            // If the activity implements FragmentCallback interface call saveSessionState() method and redirectToDashboard() method
                            if (getActivity() instanceof FragmentCallback) {
                                // Call saveSessionState() method of the activity to save session state
                                mListener.saveSessionState();
                                mListener.redirectToDashboard();
                            }
                        } else if (response.code() == 401) {
                            // Handle 401 status code
                            assert response.body() != null;
                            Toast.makeText(getContext(), response.body().getArgs().getMessage(), Toast.LENGTH_SHORT).show();
                            // Enable sign-in buttons after the request is completed
                            btnSignIn.setEnabled(true);
                            btnSignInGoogle.setEnabled(true);
                            return;
                        } else {
                            // Handle other status codes
                            Toast.makeText(getContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            // Enable sign-in buttons after the request is completed
                            btnSignIn.setEnabled(true);
                            btnSignInGoogle.setEnabled(true);
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        // Handle failure to connect to the server
                        Log.e("LoginFragment", "Error: " + t.getMessage());
                        Toast.makeText(getContext(), "Communication error. Please, check internet connection or try later.", Toast.LENGTH_SHORT).show();

                        // Enable sign-in buttons after the request is completed
                        btnSignIn.setEnabled(true);
                        btnSignInGoogle.setEnabled(true);

                        return;
                    }
                });


            }
        });

        // Set click listener for sign-in with Google button
        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}