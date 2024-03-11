package com.example.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dicelinkapp.databinding.FragmentGameTablesBinding;
import com.example.dicelinkapp.viewmodel.GameTablesViewModel;

public class GameTablesFragment extends Fragment {

    private FragmentGameTablesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GameTablesViewModel gameTablesViewModel =
                new ViewModelProvider(this).get(GameTablesViewModel.class);

        binding = FragmentGameTablesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGameTables;
        gameTablesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}