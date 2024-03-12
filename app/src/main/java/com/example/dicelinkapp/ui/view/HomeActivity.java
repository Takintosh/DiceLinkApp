package com.example.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.dicelinkapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private NotificationsFragment notificationsFragment;
    private boolean isNotificationsFragmentVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupNavigation();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notificationsFragment = new NotificationsFragment();

    }

    private void setupNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        assert navHostFragment != null;

        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.getNavController());

        NavController navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // Verificar si el destino actual es diferente al destino de notificaciones
            if (destination.getId() != R.id.fragment_container) {
                // Ocultar el fragmento de notificaciones
                hideNotificationsFragment();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifications) {
            toggleNotificationsFragment();
            return true;
        }
        return true;
    }

    private void toggleNotificationsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isNotificationsFragmentVisible) {
            // Oculta el fragmento de notificaciones
            transaction.hide(notificationsFragment);
            Toast.makeText(this, "Fragmento ocultado", Toast.LENGTH_SHORT).show();
        } else {
            // Muestra el fragmento de notificaciones
            if (!notificationsFragment.isAdded()) {
                transaction.add(R.id.notifications_container, notificationsFragment);
                Toast.makeText(this, "Fragmento agregado", Toast.LENGTH_SHORT).show();
            } else {
                transaction.show(notificationsFragment);
                Toast.makeText(this, "Fragmento Mostrado", Toast.LENGTH_SHORT).show();
            }
        }
        transaction.commit();
        isNotificationsFragmentVisible = !isNotificationsFragmentVisible;
    }

    private void hideNotificationsFragment() {
        if (isNotificationsFragmentVisible) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(notificationsFragment);
            transaction.commit();
            isNotificationsFragmentVisible = false;
        }
    }

}