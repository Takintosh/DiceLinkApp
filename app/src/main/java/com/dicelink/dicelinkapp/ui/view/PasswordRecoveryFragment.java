package com.dicelink.dicelinkapp.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.data.remote.ApiClient;
import com.dicelink.dicelinkapp.data.remote.AuthApiService;
import com.dicelink.dicelinkapp.model.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PasswordRecoveryFragment extends Fragment {

    Button btnPassRecovery;
    EditText etUsername;
    String username;
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
        View view = inflater.inflate(R.layout.fragment_passrecovery, container, false);

        // Initialize views
        etUsername = view.findViewById(R.id.etUsername);
        btnPassRecovery = view.findViewById(R.id.btnPassRecovery);

        // Set click listener for Password Recovery button
        btnPassRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etUsername.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please, enter your username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Disable the Password Recovery button
                btnPassRecovery.setEnabled(false);

                // Retrieve username from the username edit text
                username = etUsername.getText().toString();

                // Create an instance of the AuthApiService
                AuthApiService apiService = ApiClient.getClient().create(AuthApiService.class);

                // Call the passwordRecovery method of the AuthApiService
                Call<AuthResponse> call = apiService.passwordRecovery(username);

                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                        if (response.code() == 200) {
                            Log.d("PasswordRecovery", "Recovery email sent");

                            // Show message
                            Toast.makeText(getContext(), response.body().getArgs().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            assert response.body() != null;
                            Toast.makeText(getContext(), response.body().getArgs().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        // Enable sign-in buttons after the request is completed
                        btnPassRecovery.setEnabled(true);
                        return;
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {

                        // Log error message
                        Log.e("PasswordRecovery", "Error: " + t.getMessage());
                        Toast.makeText(getContext(), "Communication error. Please, check internet connection or try later.", Toast.LENGTH_SHORT).show();

                        // Enable recovery password button after the request is completed
                        btnPassRecovery.setEnabled(true);

                        return;

                    }
                });


            }
        });

        return view;
    }

}