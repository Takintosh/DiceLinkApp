package com.dicelink.dicelinkapp.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicelink.dicelinkapp.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private Context context;
    private List<PartyItem> partyItemList;

    public ExploreAdapter(Context context, List<PartyItem> partyItemList) {
        this.context = context;
        this.partyItemList = partyItemList;
    }

    @NonNull
    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parties_module, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
        PartyItem partyItem = partyItemList.get(position);

        // Main user
        holder.ivProfileMainUser.setImageResource(partyItem.getMainUserImage());
        holder.tvMainUserName.setText(partyItem.getMainUserName());

        // User 1
        holder.ivUserIcon1.setImageResource(partyItem.getUserIcon1());
        holder.tvUserName1.setText(partyItem.getUserName1());
        holder.tvUserRole1.setText(partyItem.getUserRole1());
        holder.tvUserRoleType1.setText(partyItem.getUserRoleType1());

        // User 2
        holder.ivUserIcon2.setImageResource(partyItem.getUserIcon2());
        holder.tvUserName2.setText(partyItem.getUserName2());
        holder.tvUserRole2.setText(partyItem.getUserRole2());
        holder.tvUserRoleType2.setText(partyItem.getUserRoleType2());

        // User 3
        holder.ivUserIcon3.setImageResource(partyItem.getUserIcon3());
        holder.tvUserName3.setText(partyItem.getUserName3());
        holder.tvUserRole3.setText(partyItem.getUserRole3());
        holder.tvUserRoleType3.setText(partyItem.getUserRoleType3());

        // Dungeon master status and distance
        holder.tvDungeonMasterStatus.setText(partyItem.getDungeonMasterStatus());
        holder.tvDistance.setText(partyItem.getDistance());

        // Additional users
        // Assuming you have a method in PartyItem to get additional users
        for (int i = 0; i < partyItem.getAdditionalUsers().size(); i++) {
            PartyItem.AdditionalUser additionalUser = partyItem.getAdditionalUsers().get(i);
            holder.additionalUserIcons.get(i).setImageResource(additionalUser.getIcon());
            holder.additionalUserNames.get(i).setText(additionalUser.getName());
            holder.additionalUserRoles.get(i).setText(additionalUser.getRole());
            holder.additionalUserRoleTypes.get(i).setText(additionalUser.getRoleType());
        }

        // Party image
        holder.ivPartyImage.setImageResource(partyItem.getPartyImage());

        // Button
        holder.btnJoin.setOnClickListener(v -> {
            // Handle join button click
        });
    }

    @Override
    public int getItemCount() {
        return partyItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView ivProfileMainUser;
        TextView tvMainUserName;
        ShapeableImageView ivUserIcon1;
        TextView tvUserName1, tvUserRole1, tvUserRoleType1;
        ShapeableImageView ivUserIcon2;
        TextView tvUserName2, tvUserRole2, tvUserRoleType2;
        ShapeableImageView ivUserIcon3;
        TextView tvUserName3, tvUserRole3, tvUserRoleType3;
        TextView tvDungeonMasterStatus;
        TextView tvDistance;
        ImageView ivPartyImage;
        Button btnJoin;

        // Additional users
        List<ShapeableImageView> additionalUserIcons = new ArrayList<>();
        List<TextView> additionalUserNames = new ArrayList<>();
        List<TextView> additionalUserRoles = new ArrayList<>();
        List<TextView> additionalUserRoleTypes = new ArrayList<>();

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileMainUser = itemView.findViewById(R.id.iv_profile_mainUser_id);
            tvMainUserName = itemView.findViewById(R.id.tv_main_user_name_id);
            ivUserIcon1 = itemView.findViewById(R.id.tabletop_user_icon_id_1);
            tvUserName1 = itemView.findViewById(R.id.tabletop_user_explore_id_1);
            tvUserRoleType1 = itemView.findViewById(R.id.tabletop_roletype_explore_id_1);
            ivUserIcon2 = itemView.findViewById(R.id.tabletop_user_icon_id_2);
            tvUserName2 = itemView.findViewById(R.id.tabletop_user_explore_username_2);
            tvUserRoleType2 = itemView.findViewById(R.id.tabletop_roletype_explore_id_2);
            ivUserIcon3 = itemView.findViewById(R.id.tabletop_user_icon_id_3);
            tvUserName3 = itemView.findViewById(R.id.tabletop_user_explore_username_3);
            tvUserRoleType3 = itemView.findViewById(R.id.tabletop_roletype_explore_id_3);
            tvDungeonMasterStatus = itemView.findViewById(R.id.tv_dungeon_master_status_id);
            tvDistance = itemView.findViewById(R.id.tv_distance_id);
            ivPartyImage = itemView.findViewById(R.id.iv_party_image_id);
            btnJoin = itemView.findViewById(R.id.btn_join_id);

            // Initialize additional users
//            additionalUserIcons.add(itemView.findViewById(R.id.tabletop_user_icon_id_4));
//            additionalUserNames.add(itemView.findViewById(R.id.tabletop_user_explore_username_4));
//            additionalUserRoleTypes.add(itemView.findViewById(R.id.tabletop_roletype_explore_id_4));
//
//            additionalUserIcons.add(itemView.findViewById(R.id.tabletop_user_icon_id_5));
//            additionalUserNames.add(itemView.findViewById(R.id.tabletop_user_explore_username_5));
//            additionalUserRoleTypes.add(itemView.findViewById(R.id.tabletop_roletype_explore_id_5));
        }
    }
}

