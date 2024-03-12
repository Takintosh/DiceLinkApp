package com.example.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dicelinkapp.databinding.FragmentAccountBinding;
import com.example.dicelinkapp.viewmodel.AccountViewModel;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create ViewModel for AccountFragment
        AccountViewModel accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find TextView from layout
        final TextView textView = binding.textAccount;

        // Observe LiveData from ViewModel to update TextView
        accountViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the ViewBinding reference to avoid memory leaks
        binding = null;
    }
}