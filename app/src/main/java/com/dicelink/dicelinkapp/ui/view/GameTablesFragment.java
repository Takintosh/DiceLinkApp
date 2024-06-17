package com.dicelink.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.databinding.FragmentGameTablesBinding;
import com.dicelink.dicelinkapp.viewmodel.GameTablesViewModel;

public class GameTablesFragment extends Fragment {

    private FragmentGameTablesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create ViewModel for GameTablesFragment
        GameTablesViewModel gameTablesViewModel = new ViewModelProvider(this).get(GameTablesViewModel.class);

        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentGameTablesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find TextView from layout
        final TextView textView = binding.textGameTables;

        // Observe LiveData from ViewModel to update TextView
        gameTablesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button buttonMyTables = root.findViewById(R.id.button_my_tables);
        Button buttonMyRequests = root.findViewById(R.id.button_my_requests);

        // Set click listeners to replace fragments
        buttonMyTables.setOnClickListener(v -> loadFragment(new MisMesasFragment()));
        buttonMyRequests.setOnClickListener(v -> loadFragment(new MisSolicitudesFragment()));

        // Load default fragment
        loadFragment(new MisMesasFragment());

        return root;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the ViewBinding reference to avoid memory leaks
        binding = null;
    }
}
