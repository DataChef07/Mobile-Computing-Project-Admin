package com.example.sportifyadmin.recylcerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportifyadmin.R;
import com.example.sportifyadmin.model.EquipmentModel;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {
    Context context;
    ArrayList<EquipmentModel> equipmentList;

    public EquipmentAdapter(Context context, ArrayList<EquipmentModel> equipmentList) {
        this.context = context;
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_equipment, parent, false);
        return new EquipmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {

        EquipmentModel model = equipmentList.get(position);
        holder.sport.setText(model.getName());
        holder.courts.setText(model.getCourts());
        holder.equipments.setText(model.getEquipments());

    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public static class EquipmentViewHolder extends RecyclerView.ViewHolder
    {
        TextView courts, equipments, sport;

        public EquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            sport = itemView.findViewById(R.id.sport_name);
            courts = itemView.findViewById(R.id.court_count);
            equipments = itemView.findViewById(R.id.equipment_count);
        }
    }
}
