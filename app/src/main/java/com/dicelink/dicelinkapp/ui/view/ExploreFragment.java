package com.dicelink.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.databinding.FragmentExploreBinding;
import com.dicelink.dicelinkapp.viewmodel.ExploreViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    private PartyAdapter partyAdapter;
    private List<Party> partyList;

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

        // Set up the RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        partyList = new ArrayList<>();
        // Add items to the partyList
        partyList.add(new Party("Zekken", "Fighter"));
        partyList.add(new Party("Diagon", "Mage"));
        partyList.add(new Party("Solaris", "Archer"));
        partyAdapter = new PartyAdapter(getContext(), partyList);
        binding.recyclerView.setAdapter(partyAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear the ViewBinding reference to avoid memory leaks
        binding = null;
    }
}
