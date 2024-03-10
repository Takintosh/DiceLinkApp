package com.example.dicelinkapp.ui.view;
import androidx.fragment.app.Fragment;

public class PasswordRecoveryFragmentFactory implements FragmentFactory {
    // Implementation of the createFragment() method from the FragmentFactory interface
    @Override
    public Fragment createFragment() {
        // Create and return an instance of PasswordRecoveryFragment
        return new PasswordRecoveryFragment();
    }
}