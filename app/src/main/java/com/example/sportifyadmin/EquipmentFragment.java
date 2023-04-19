package com.example.sportifyadmin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportifyadmin.model.EquipmentModel;
import com.example.sportifyadmin.recylcerView.EquipmentAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EquipmentFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference equipmentsDb;
    EquipmentAdapter equipmentAdapter;
    ArrayList<EquipmentModel> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.equipment_recyclerView);
        equipmentsDb = FirebaseDatabase.getInstance().getReference("test");
        list = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        equipmentAdapter = new EquipmentAdapter(getContext(), list);
        recyclerView.setAdapter(equipmentAdapter);

        equipmentsDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // fetching the data from "Equipment" key from firebase
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    EquipmentModel equipment = dataSnapshot.getValue(EquipmentModel.class);
                    list.add(equipment);
                }
                equipmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}