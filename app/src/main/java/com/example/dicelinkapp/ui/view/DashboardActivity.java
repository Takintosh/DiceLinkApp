package com.example.dicelinkapp.ui.view;

import android.os.Bundle;

import com.example.dicelinkapp.R;
import com.example.dicelinkapp.util.ToolbarHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.view.MenuItem;

import com.example.dicelinkapp.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDashboardBinding binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        ToolbarHelper.setupToolbar(this, toolbar);



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_explore, R.id.navigation_game_tables, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}