package com.dicelink.dicelinkapp.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicelink.dicelinkapp.R;
import java.util.List;

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.SolicitudViewHolder> {

    private List<SolicitudOBJ> solicitudes;
    private Context context;

    public SolicitudesAdapter(Context context, List<SolicitudOBJ> solicitudes) {
        this.context = context;
        this.solicitudes = solicitudes;
    }

    @NonNull
    @Override
    public SolicitudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_solicitudes, parent, false);
        return new SolicitudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudViewHolder holder, int position) {
        SolicitudOBJ solicitud = solicitudes.get(position);
        holder.bind(solicitud);
    }

    @Override
    public int getItemCount() {
        return solicitudes.size();
    }

    public class SolicitudViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        TextView tvTexto;

        public SolicitudViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username_user1);
            tvTexto = itemView.findViewById(R.id.tv_text_user1);
        }

        public void bind(SolicitudOBJ solicitud) {
            tvUsername.setText(solicitud.getUsername());
            tvTexto.setText(solicitud.getTexto());
        }
    }
}
