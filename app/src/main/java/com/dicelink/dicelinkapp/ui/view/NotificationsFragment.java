package com.dicelink.dicelinkapp.ui.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicelink.dicelinkapp.databinding.FragmentNotificationsBinding;
import com.dicelink.dicelinkapp.viewmodel.NotificationsViewModel;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create ViewModel for NotificationsFragment
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Find TextView from layout
        final TextView textView = binding.textNotifications;

        // Observe LiveData from ViewModel to update TextView
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the ViewBinding reference to avoid memory leaks
        binding = null;
    }
}