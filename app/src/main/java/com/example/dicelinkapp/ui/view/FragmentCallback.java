package com.example.dicelinkapp.ui.view;

public interface FragmentCallback {

    // Method signature for replacing the current fragment with another one
    void onFragmentReplace(String fragmentClassName);

    // Method signature for redirecting to the dashboard
    void redirectToDashboard();

    // Method signature for saving the session state
    void saveSessionState();

}