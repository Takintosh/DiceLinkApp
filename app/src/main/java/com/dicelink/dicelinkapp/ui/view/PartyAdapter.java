package com.dicelink.dicelinkapp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicelink.dicelinkapp.R;
import com.dicelink.dicelinkapp.ui.view.Party;

import java.util.List;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.PartyViewHolder> {

    private Context context;
    private List<Party> partyList;

    public PartyAdapter(Context context, List<Party> partyList) {
        this.context = context;
        this.partyList = partyList;
    }

    @NonNull
    @Override
    public PartyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parties_module, parent, false);
        return new PartyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyViewHolder holder, int position) {
        Party party = partyList.get(position);
        // Aquí debes enlazar los datos del objeto party con las vistas en parties_module.xml
        // Ejemplo:
        holder.userNameTextView.setText(party.getUserName());
        holder.roleTextView.setText(party.getRole());
        // Añade más enlaces según sea necesario
    }

    @Override
    public int getItemCount() {
        return partyList.size();
    }

    public static class PartyViewHolder extends RecyclerView.ViewHolder {

        TextView userNameTextView;
        TextView roleTextView;
        // Añade más vistas según sea necesario

        public PartyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.tv_main_user_name_id);
            roleTextView = itemView.findViewById(R.id.tabletop_roletype_explore_id_1);
            // Inicializa más vistas según sea necesario
        }
    }
}