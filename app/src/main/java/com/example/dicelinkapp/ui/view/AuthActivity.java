package com.example.dicelinkapp.ui.view;

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

import com.example.dicelinkapp.R;

import java.util.HashMap;
import java.util.Map;

public class AuthActivity extends AppCompatActivity implements FragmentCallback {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    // Map to store fragment class names and their corresponding factory instances
    private final Map<String, FragmentFactory> fragmentFactoryMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Install SplashScreen for smooth transition
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

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
}
