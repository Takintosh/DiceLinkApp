package com.example.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dicelinkapp.databinding.FragmentExploreBinding;
import com.example.dicelinkapp.viewmodel.ExploreViewModel;

public class ExploreFragment extends Fragment {

    private FragmentExploreBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create ViewModel for ExploreFragment
        ExploreViewModel exploreViewModel = new ViewModelProvider(this).get(ExploreViewModel.class);

        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentExploreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find TextView from layout
        final TextView textView = binding.textExplore;

        // Observe LiveData from ViewModel to update TextView
        exploreViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the ViewBinding reference to avoid memory leaks
        binding = null;
    }
}