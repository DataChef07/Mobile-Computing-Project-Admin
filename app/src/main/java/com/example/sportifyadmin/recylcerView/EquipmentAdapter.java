package com.example.sportifyadmin.recylcerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sportifyadmin.R;
import com.example.sportifyadmin.model.EquipmentModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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
        holder.c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(v.getContext());
                dialog.setContentView(R.layout.equipment_dialogue);

                TextView c_value,e_value,sport_name;
                ImageView c_plus,c_minus,e_plus,e_minus;
                Button submit;
                submit=dialog.findViewById(R.id.submit);
                sport_name=dialog.findViewById(R.id.sport_name1);
                c_plus=dialog.findViewById(R.id.C_plus);
                c_minus=dialog.findViewById(R.id.C_minus);
                e_plus=dialog.findViewById(R.id.E_plus);
                e_minus=dialog.findViewById(R.id.E_minus);
                final int[] c1 = new int[1];
                final int[] e1 = new int[1];
                c1[0] =Integer.parseInt(model.getCourts());
                e1[0] =Integer.parseInt(model.getEquipments());
                c_value=dialog.findViewById(R.id.court_value1);
                e_value=dialog.findViewById(R.id.equipment_value);
                sport_name.setText(model.getName());
//                Toast.makeText(context, String.valueOf(model.getCourts()), Toast.LENGTH_SHORT).show();
                c_value.setText(String.valueOf(c1[0]));
                e_value.setText(String.valueOf(e1[0]));

                HashMap<String, String> mp = new HashMap<>();
                mp.put("Air Hockey", "airhockey");
                mp.put("Badminton", "badminton");
                mp.put("Football", "football");
                mp.put("Squash", "squash");
                mp.put("Table Tennis", "tabletennis");
                mp.put("Tennis", "tennis");
                mp.put("swimming", "swimming");

                DatabaseReference obj = FirebaseDatabase.getInstance().getReference().child("test");
                c_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c1[0] = c1[0] +1;
                        c_value.setText(String.valueOf(c1[0]));
                    }
                });
                c_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c1[0] = c1[0] -1;
                        c_value.setText(String.valueOf(c1[0]));
                    }
                });
                e_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e1[0] = e1[0] +1;
                        e_value.setText(String.valueOf(e1[0]));
                    }
                });
                e_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        e1[0] = e1[0] -1;
                        e_value.setText(String.valueOf(e1[0]));
                    }
                });
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sport = mp.get(model.getName());
                        obj.child(sport).child("courts").setValue(String.valueOf(c1[0]));
                        obj.child(sport).child("equipments").setValue(String.valueOf(e1[0]));
                        c_value.setText(String.valueOf(c1[0]));
                        e_value.setText(String.valueOf(e1[0]));
                        dialog.cancel();

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
            }
        });

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
        CardView c1;

        public EquipmentViewHolder(@NonNull View itemView) {
            super(itemView);
            c1=itemView.findViewById(R.id.equipment1);
            sport = itemView.findViewById(R.id.sport_name);
            courts = itemView.findViewById(R.id.court_count);
            equipments = itemView.findViewById(R.id.equipment_count);


        }
    }
}
