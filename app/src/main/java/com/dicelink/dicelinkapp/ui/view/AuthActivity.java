package com.dicelink.dicelinkapp.ui.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dicelink.dicelinkapp.R;

import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity implements FragmentCallback {

    // Constants for SharedPreferences
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    // Map to store fragment class names and their corresponding factory instances
    private final Map<String, FragmentFactory> fragmentFactoryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Install SplashScreen for smooth transition
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        // Check if the user has previously logged in
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, false);

        if (isLoggedIn) {
            // If the user has logged in, redirect to DashboardActivity
            redirectToDashboard();
            return; // Exit onCreate method to avoid showing the login screen
        }

        // Enable edge-to-edge layout
        EdgeToEdge.enable(this);

        // Set the layout for the activity
        setContentView(R.layout.activity_auth);

        // Apply system insets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.auth), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Add associations between fragment class names and their factory classes
        fragmentFactoryMap.put(RegisterFragment.class.getName(), new RegisterFragmentFactory());
        fragmentFactoryMap.put(LoginFragment.class.getName(), new LoginFragmentFactory());
        fragmentFactoryMap.put(PasswordRecoveryFragment.class.getName(), new PasswordRecoveryFragmentFactory());

        // Add initial fragment to the container
        addFragment();
    }

    // Method to add initial fragment to the container
    public void addFragment() {
        LoginFragment fragment = new LoginFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    // Callback method called from fragments to replace current fragment with a new one
    @Override
    public void onFragmentReplace(String fragmentClassName) {
        // Retrieve factory for the given fragment class name
        FragmentFactory factory = getFragmentFactory(fragmentClassName);
        if (factory != null) {
            // Create new fragment using the factory
            Fragment fragment = factory.createFragment();
            // Replace current fragment with the new one
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    // Method to get fragment factory by class name
    private FragmentFactory getFragmentFactory(String fragmentClassName) {
        return fragmentFactoryMap.get(fragmentClassName);
    }

    // Method to redirect to DashboardActivity
    public void redirectToDashboard() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish(); // Finish AuthActivity to prevent user from going back by pressing the back button
    }

    // Method to save session state indicating that the user is logged in
    public void saveSessionState() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

}