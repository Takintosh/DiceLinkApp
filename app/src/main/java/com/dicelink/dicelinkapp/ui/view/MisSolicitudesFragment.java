package com.dicelink.dicelinkapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicelink.dicelinkapp.R;

import java.util.ArrayList;
import java.util.List;

public class MisSolicitudesFragment extends Fragment {

    private RecyclerView recyclerViewSolicitudes;
    private SolicitudesAdapter solicitudesAdapter;
    private List<SolicitudOBJ> solicitudList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_solicitudes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerViewSolicitudes = view.findViewById(R.id.recycler_view_solicitudes);
        recyclerViewSolicitudes.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize list and adapter
        solicitudList = new ArrayList<>();
        solicitudesAdapter = new SolicitudesAdapter(getContext(), solicitudList);

        // Set adapter to RecyclerView
        recyclerViewSolicitudes.setAdapter(solicitudesAdapter);

        // Load data (for example purposes, we add some mock data)
        loadSolicitudes();
    }

    private void loadSolicitudes() {
        // Add mock data to the list (replace with actual data retrieval logic)
        solicitudList.add(new SolicitudOBJ("Usuario1", "Texto de solicitud 1"));
        solicitudList.add(new SolicitudOBJ("Usuario2", "Texto de solicitud 2"));
        solicitudList.add(new SolicitudOBJ("Usuario3", "Texto de solicitud 3"));

        // Notify adapter of data changes
        solicitudesAdapter.notifyDataSetChanged();
    }
}
