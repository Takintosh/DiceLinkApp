package com.dicelink.dicelinkapp.ui.view;

import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.dicelink.dicelinkapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private NotificationsFragment notificationsFragment;
    private boolean isNotificationsFragmentVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Apply window insets to adjust padding based on system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup navigation components
        setupNavigation();

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize notifications fragment
        notificationsFragment = new NotificationsFragment();

        //change color navigationBar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_grey1)));
    }

    // Set up the bottom navigation bar with NavController.
    private void setupNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        assert navHostFragment != null;

        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.getNavController());

//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            // Change the icon of the selected item to match color visually
//            switch (item.getItemId()) {
//                case R.id.navigation_explore:
//                    item.setIcon(R.drawable.home_icon_selected);
//                    break;
//                case R.id.navigation_game_tables:
//                    item.setIcon(R.drawable.tables_homedice_selected);
//                    break;
//                case R.id.navigation_account:
//                    item.setIcon(R.drawable.user_icon_selected);
//                    break;
//            }
//            return true;
//        });

        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // Check if the current destination is different from the notifications destination
            if (destination.getId() != R.id.fragment_container) {
                // Hide the notifications fragment
                hideNotificationsFragment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifications) {
            // Toggle visibility of notifications fragment
            toggleNotificationsFragment();
            return true;
        }
        return true;
    }

    // Toggle the visibility of the notifications fragment.
    private void toggleNotificationsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isNotificationsFragmentVisible) {
            // Hide the notifications fragment
            transaction.hide(notificationsFragment);
        } else {
            // Show the notifications fragment
            if (!notificationsFragment.isAdded()) {
                // Add the notifications fragment if not added before
                transaction.add(R.id.notifications_container, notificationsFragment);
            } else {
                // Show the notifications fragment if already added
                transaction.show(notificationsFragment);
            }
        }
        // Commit the transaction
        transaction.commit();
        // Update the flag for fragment visibility
        isNotificationsFragmentVisible = !isNotificationsFragmentVisible;
    }

    // Hide the notifications fragment.
    private void hideNotificationsFragment() {
        if (isNotificationsFragmentVisible) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Hide the notifications fragment
            transaction.hide(notificationsFragment);
            // Commit the transaction
            transaction.commit();
            // Update the flag for fragment visibility
            isNotificationsFragmentVisible = false;
        }
    }

}