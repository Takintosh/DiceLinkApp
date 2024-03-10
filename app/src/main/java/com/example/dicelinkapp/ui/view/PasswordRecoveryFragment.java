package com.example.dicelinkapp.ui.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dicelinkapp.R;


public class PasswordRecoveryFragment extends Fragment {

    Button btnPassRecovery;
    EditText etUsername;
    String username;
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
        View view = inflater.inflate(R.layout.fragment_passrecovery, container, false);

        // Initialize views
        etUsername = view.findViewById(R.id.etUsername);
        btnPassRecovery = view.findViewById(R.id.btnPassRecovery);

        // Set click listener for Password Recovery button
        btnPassRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username from the username edit text
                username = etUsername.getText().toString();

                // Show invalid user message
                Toast.makeText(getContext(), "Recovery email sent", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}