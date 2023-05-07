package com.example.sportifyadmin.recylcerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportifyadmin.R;
import com.example.sportifyadmin.model.AddUser;
import com.example.sportifyadmin.model.EquipmentModel;

import java.util.ArrayList;

public class RegistryAdapter extends RecyclerView.Adapter<RegistryAdapter.RegistryViewHolder> {

    Context context;
    ArrayList<AddUser> userList;

    public RegistryAdapter(Context context, ArrayList<AddUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RegistryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_registry, parent, false);
        return new RegistryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RegistryViewHolder holder, int position) {

        // display the details
        AddUser user = userList.get(position);

        holder.userName.setText(user.getName());
        holder.userSport.setText(user.getSport());
        holder.userEquipment.setText(user.getEquipment());
        holder.timeStamp.setText(user.getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class RegistryViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName, userSport, userEquipment, timeStamp;

        public RegistryViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userSport = itemView.findViewById(R.id.userSport);
            userEquipment = itemView.findViewById(R.id.userEquipment);
            timeStamp = itemView.findViewById(R.id.userTimeStamp);
        }
    }
}
