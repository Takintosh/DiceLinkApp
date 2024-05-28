package com.dicelink.dicelinkapp.ui.view;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.data.local.AuthPreferences;
import com.dicelink.dicelinkapp.data.remote.ApiClient;
import com.dicelink.dicelinkapp.data.remote.AuthApiService;
import com.dicelink.dicelinkapp.data.remote.RegistrationRequest;
import com.dicelink.dicelinkapp.model.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    Button btnSignUp, btnSignUpGoogle;
    EditText etUsername, etEmail, etPassword, etPasswordConfirmation;
    String username, email, password, confirmPassword;
    CheckBox termsAndConditions, privacyPolicy;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private FragmentCallback mListener;

    // Method called when the fragment is attached to the activity
    public void onAttach(Context context) {
        // Initialize SharedPreferences
        preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = preferences.edit();
        super.onAttach(context);

        // Check if the activity implements FragmentCallback interface
        if (context instanceof FragmentCallback) {
            mListener = (FragmentCallback) context;
        } else {
            // Throw an exception if the activity doesn't implement the required interface
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // Initialize EditText fields and buttons
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etPasswordConfirmation = view.findViewById(R.id.etPasswordConfirmation);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnSignUpGoogle = view.findViewById(R.id.btnSignUpGoogle);
        termsAndConditions = (CheckBox) view.findViewById(R.id.cbAgreeTermsAndConditions);
        privacyPolicy = (CheckBox) view.findViewById(R.id.cbAgreePrivacyPolicy);

        // Initialize TextView for sign in hint with clickable link
        TextView textView = view.findViewById(R.id.tvSignInHint);
        String tvComplete = getString(R.string.signInHint, getString(R.string.signInLink));
        SpannableString spannableString = new SpannableString(tvComplete);

        // Set clickable span for sign in link
        int startIndex = tvComplete.indexOf(getString(R.string.signInLink));
        int endIndex = startIndex + getString(R.string.signInLink).length();
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                // Replace current fragment with login fragment when sign in link is clicked
                if (mListener != null) {
                    mListener.onFragmentReplace(LoginFragment.class.getName());
                }
            }
        };
        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Set click listener for sign-up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input for username, email, and password
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etPasswordConfirmation.getText().toString();

                // Validate user input
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    // Show toast message if any field is empty
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    // Show toast message if password and confirm password do not match
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 8) {
                    // Show toast message if password is less than 8 characters
                    Toast.makeText(getContext(), "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.contains("@")) {
                    // Show toast message if email does not contain '@' character
                    Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!termsAndConditions.isChecked() || !privacyPolicy.isChecked()) {
                    // Show toast message if terms and conditions and privacy policy are not agreed
                    Toast.makeText(getContext(), "Please agree to terms and conditions and privacy policy", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Disable sign-up buttons to prevent multiple clicks
                btnSignUp.setEnabled(false);
                btnSignUpGoogle.setEnabled(false);

                // Create an instance of the AuthApiService interface
                AuthApiService apiService = ApiClient.getClient().create(AuthApiService.class);

                // Create a new instance of the RegistrationRequest class
                RegistrationRequest registrationRequest = new RegistrationRequest();

                // Set user input values to the RegistrationRequest object
                registrationRequest.setUsername(username);
                registrationRequest.setEmail(email);
                registrationRequest.setPassword(password);
                registrationRequest.setPasswordConfirmation(confirmPassword);
                registrationRequest.setTerms(true);
                registrationRequest.setPrivacy(true);

                // Call the registerUser method of the AuthApiService interface
                Call<AuthResponse> call = apiService.registerUser(registrationRequest);

                // Enqueue the call to send the request to the server
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

                            Log.d("RegisterFragment", authPrefs.getToken());
                            Toast.makeText(getContext(), "User Registered", Toast.LENGTH_SHORT).show();

                            if (getActivity() instanceof FragmentCallback) {
                                // Call saveSessionState() method of the activity to save session state
                                mListener.saveSessionState();
                                mListener.redirectToDashboard();
                            }

                        } else if (response.code() == 406) {
                            // Handle 406 status code
                            assert response.body() != null;
                            Toast.makeText(getContext(),  response.body().getArgs().getMessage(), Toast.LENGTH_SHORT).show();
                            // Enable sign-up buttons after the request is completed
                            btnSignUp.setEnabled(true);
                            btnSignUpGoogle.setEnabled(true);
                            return;
                        } else {
                            // Handle other status codes
                            assert response.body() != null;
                            Toast.makeText(getContext(), response.body().getArgs().getMessage(), Toast.LENGTH_SHORT).show();
                            // Enable sign-up buttons after the request is completed
                            btnSignUp.setEnabled(true);
                            btnSignUpGoogle.setEnabled(true);
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        // Handle failure to connect to the server
                        Log.e("LoginFragment", "Error: " + t.getMessage());
                        Toast.makeText(getContext(), "Communication error. Please, check internet connection or try later.", Toast.LENGTH_SHORT).show();

                        // Enable sign-up buttons after the request is completed
                        btnSignUp.setEnabled(true);
                        btnSignUpGoogle.setEnabled(true);

                        return;

                    }
                });


            }
        });

        // Set click listener for sign-up with Google button
        btnSignUpGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No functionality added for sign up with Google button yet
                Toast.makeText(getContext(), "TODO", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}